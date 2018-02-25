package hu.elte.eserial.annotation.processor;

import hu.elte.eserial.annotation.EserialAnnotation;
import hu.elte.eserial.annotation.inclusionrule.AbstractInclusionRule;
import hu.elte.eserial.annotation.inclusionrule.InclusionRuleFactory;
import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.util.AnnotationUtils;
import hu.elte.eserial.util.FieldUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Processes {@link EserialAnnotation}s on getters, setters and classes.
 */
public class EserialAnnotationProcessor {

    private boolean doneEvaluating = false;

    /**
     * Decides whether an {@code element} should be included depending on the {@link EserialAnnotation}s on its
     * field (if exists), getter and containing class.<br/>
     * The annotations are grouped by their location (field, getter or class) and ordered by their priorities.
     * Annotation locations are checked in the following order:
     * <ol>
     *     <li>class</li>
     *     <li>getter</li>
     *     <li>field</li>
     * </ol>
     * @param element an element containing the getter or setter to be checked and the value of the field
     * @param objectMap the built map of the object until now
     * @return {@code true} if the {@code element} should be included
     *
     * @see EserialAnnotation#priority()
     */
    public boolean shouldIncludeElement(EserialElement element, Map<String, Object> objectMap) {
        this.doneEvaluating = false;

        Class clazz = element.getAccessor().getDeclaringClass();

        List<Annotation> classEserialAnnotations = AnnotationUtils.getEserialAnnotations(clazz);
        boolean result = evaluateAnnotations(classEserialAnnotations, element, objectMap);
        if (doneEvaluating) {
            return result;
        }

        List<Annotation> getterEserialAnnotations = AnnotationUtils.getEserialAnnotations(element.getAccessor());
        result = evaluateAnnotations(getterEserialAnnotations, element, objectMap);
        if (doneEvaluating) {
            return result;
        }

        String fieldName = FieldUtils.getFieldName(element.getAccessor());
        Field field = FieldUtils.getField(element.getAccessor().getDeclaringClass(), fieldName);
        if (field != null) {
            List<Annotation> fieldEserialAnnotations = AnnotationUtils.getEserialAnnotations(field);
            result = evaluateAnnotations(fieldEserialAnnotations, element, objectMap);
            if (doneEvaluating) {
                return result;
            }
        }

        return true;
    }

    private boolean evaluateAnnotations(List<Annotation> annotations, EserialElement element,
                                        Map<String, Object> objectMap) {
        annotations.sort(AnnotationUtils::compare);
        for (Annotation annotation : annotations) {

            AbstractInclusionRule inclusionRule = InclusionRuleFactory.get(annotation);
            boolean result = inclusionRule.evaluate(element, objectMap);
            if (result && inclusionRule.isInclusionRule()) {
                this.doneEvaluating = true;
                return true;
            }
            else if (!result && !inclusionRule.isInclusionRule()) {
                this.doneEvaluating = true;
                return false;
            }
        }
        return true;
    }
}
