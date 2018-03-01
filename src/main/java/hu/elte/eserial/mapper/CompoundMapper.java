package hu.elte.eserial.mapper;

import hu.elte.eserial.annotation.processor.EserialAnnotationProcessor;
import hu.elte.eserial.model.Getter;
import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.recursion.RecursionChecker;
import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.util.MethodUtils;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Maps compound objects (e.g Map.Entry).
 */
class CompoundMapper extends AbstractMapper {

    /**
     * Constructs a {@link CompoundMapper} and sets the {@code object} in it.
     *
     * @param object the compound object to be used in the {@link AbstractMapper#map} method
     */
    CompoundMapper(Object object) {
        super(object);
    }

    /**
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of the contained compound object
     */
    @Override
    public Object map(RecursionChecker recursionChecker) {
        if (!TypeUtils.isCompound(this.object.getClass())) {
            throw new EserialMapperMismatchException("Compound", this.object.getClass().getSimpleName());
        }

        EserialAnnotationProcessor annotationProcessor = new EserialAnnotationProcessor();
        if (recursionChecker == null) {
            recursionChecker = new RecursionChecker(this.object);
        }

        Map<String, Object> values = new HashMap<>();

        for (Method method : this.object.getClass().getMethods()) {
            if (MethodUtils.isIgnored(method) || !MethodUtils.isGetter(method)) {
                continue;
            }

            Getter getter = new Getter(this.object, method);
            EserialElement element = new EserialElement(method, getter.evaluate());

            if (annotationProcessor.shouldIncludeElement(element) && recursionChecker.canVisit(element)) {
                recursionChecker.beforeVisit(element);
                values.put(getter.getElementName(),
                    MapperFactory.create(getter.evaluate()).map(recursionChecker));
                recursionChecker.afterVisit(element);
            }
        }

        return values;
    }
}
