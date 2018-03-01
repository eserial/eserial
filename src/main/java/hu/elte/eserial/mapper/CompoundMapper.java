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
class CompoundMapper implements ObjectMapper {

    /**
     * Returns the mapped representation of the compound type {@code that}.
     *
     * @param {@code that} a compound object
     * @param recursionChecker {@inheritDoc}
     * @return mapped representation of {@code that}
     */
    @Override
    public Object map(Object that, RecursionChecker recursionChecker) {
        if (!TypeUtils.isCompound(that.getClass())) {
            throw new EserialMapperMismatchException("Compound", that.getClass().getSimpleName());
        }

        EserialAnnotationProcessor annotationProcessor = new EserialAnnotationProcessor();
        if (recursionChecker == null) {
            recursionChecker = new RecursionChecker(that);
        }

        Map<String, Object> values = new HashMap<>();

        for (Method method : that.getClass().getMethods()) {
            if (MethodUtils.isIgnored(method) || !MethodUtils.isGetter(method)) {
                continue;
            }

            Getter getter = new Getter(that, method);
            EserialElement element = new EserialElement(method, getter.evaluate());

            if (annotationProcessor.shouldIncludeElement(element) && recursionChecker.canVisit(element)) {
                recursionChecker.beforeVisit(element);
                values.put(getter.getElementName(),
                    MapperFactory.create(method.getReturnType()).map(getter.evaluate(), recursionChecker));
                recursionChecker.afterVisit(element);
            }
        }

        return values;
    }
}
