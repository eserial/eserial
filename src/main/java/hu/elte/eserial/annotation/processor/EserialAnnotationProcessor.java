package hu.elte.eserial.annotation.processor;

import static hu.elte.eserial.annotation.enumeration.EserialAnnotationType.Inclusion;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import hu.elte.eserial.annotation.EserialAnnotation;
import hu.elte.eserial.annotation.inclusionrule.AbstractInclusionRule;
import hu.elte.eserial.annotation.inclusionrule.InclusionRuleFactory;
import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.util.AnnotationUtils;
import hu.elte.eserial.util.FieldUtils;

/**
 * Processes {@link EserialAnnotation}s on getters, setters and classes.
 */
public class EserialAnnotationProcessor {

    private boolean doneEvaluating = false;

    /**
     * Decides whether an {@code element} should be included depending on the {@link EserialAnnotation}s on its
     * field (if exists), getter and containing class.<br>
     * The annotations are ordered by their priorities.
     * @param element an element containing the getter or setter to be checked and the value of the field
     * @return {@code true} if the {@code element} should be included
     *
     * @see EserialAnnotation#priority()
     */
    public boolean shouldIncludeElement(EserialElement element) {

        Class clazz = element.getAccessor().getDeclaringClass();

        List<Annotation> annotations = new ArrayList<>();

        annotations.addAll(AnnotationUtils.getEserialAnnotations(clazz, Inclusion));
        annotations.addAll(AnnotationUtils.getEserialAnnotations(element.getAccessor(), Inclusion));
        String fieldName = FieldUtils.getFieldName(element.getAccessor());
        Field field = FieldUtils.getField(clazz, fieldName);
        if (field != null) {
            annotations.addAll(AnnotationUtils.getEserialAnnotations(field, Inclusion));
        }

        annotations.sort(AnnotationUtils::compare);
        for (Annotation annotation : annotations) {

            AbstractInclusionRule inclusionRule = InclusionRuleFactory.get(annotation);
            boolean result = inclusionRule.evaluate(element);
            if (result && inclusionRule.isInclusionRule()) {
                return true;
            }
            else if (!result && !inclusionRule.isInclusionRule()) {
                return false;
            }
        }
        return true;
    }
}
