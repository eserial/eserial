package hu.elte.eserial.util;

import hu.elte.eserial.annotation.EserialAnnotation;
import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;
import hu.elte.eserial.annotation.inclusionrule.AbstractInclusionRule;
import hu.elte.eserial.annotation.inclusionrule.InclusionRuleFactory;
import hu.elte.eserial.exception.EserialNotEserialAnnotationException;
import hu.elte.eserial.model.Accessor;
import hu.elte.eserial.model.EserialElement;
import hu.elte.eserial.model.Getter;
import hu.elte.eserial.model.Setter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static hu.elte.eserial.annotation.enumeration.EserialAnnotationType.INCLUSION;

/**
 * Utility class for {@link EserialAnnotation}s.
 */
public class AnnotationUtils {

    private AnnotationUtils() {}

    /**
     * @param field an arbitrary field
     * @param type the type of {@link EserialAnnotation}
     * @return a list of {@link EserialAnnotation}s on the {@code field}
     *
     * @see AnnotationUtils#getEserialAnnotations(List, EserialAnnotationType)
     */
    public static List<Annotation> getEserialAnnotations(Field field, EserialAnnotationType type) {
        return getEserialAnnotations(Arrays.asList(field.getAnnotations()), type);
    }

    /**
     * @param accessor a {@link Getter} or {@link Setter}
     * @param type the type of {@link EserialAnnotation}
     * @return a list of {@link EserialAnnotation}s on the {@code accessor} method
     */
    public static List<Annotation> getEserialAnnotations(Accessor accessor, EserialAnnotationType type) {
        return getEserialAnnotations(Arrays.asList(accessor.getMethod().getAnnotations()), type);
    }

    /**
     * @param clazz a class
     * @param type the type of {@link EserialAnnotation}
     * @return a list of {@link EserialAnnotation}s on the {@code clazz}
     *
     * @see AnnotationUtils#getEserialAnnotations(List,EserialAnnotationType)
     */
    public static List<Annotation> getEserialAnnotations(Class clazz, EserialAnnotationType type) {
        return getEserialAnnotations(Arrays.asList(clazz.getDeclaredAnnotations()), type);
    }

    /**
     * @param annotations a list of annotations
     * @param type the type of {@link EserialAnnotation}
     * @return the {@code annotations} list's annotations which
     * have an {@link EserialAnnotation} on them
     *
     * @see AnnotationUtils#isMatchingEserialAnnotation
     */
    public static List<Annotation> getEserialAnnotations(List<Annotation> annotations, EserialAnnotationType type) {
        return annotations.stream()
                .filter(annotation -> isMatchingEserialAnnotation(annotation, type))
                .collect(Collectors.toList());
    }

    /**
     * @param annotation an annotation
     * @return {@code true} if the {@code} has an {@link EserialAnnotation} on it
     */
    public static boolean isEserialAnnotation(Annotation annotation) {
        return annotation.annotationType().getDeclaredAnnotation(EserialAnnotation.class) != null;
    }

    /**
     * @param annotation an annotation
     * @param type the type of {@link EserialAnnotation}
     * @return {@code true} if the {@code} has an {@link EserialAnnotation} with the given type on it
     */
    public static boolean isMatchingEserialAnnotation(Annotation annotation, EserialAnnotationType type) {
        Annotation eserialAnnotation = annotation.annotationType().getDeclaredAnnotation(EserialAnnotation.class);
        if (eserialAnnotation == null) {
            return false;
        }
        else {
            return ((EserialAnnotation)eserialAnnotation).type().equals(type);
        }
    }

    /**
     * Compares to {@link EserialAnnotation}s according to their priorities.
     * @param lhs an {@link EserialAnnotation}
     * @param rhs an {@link EserialAnnotation}
     * @return the value {@code 0} if {@code lhs.priority() == rhs.priority()},<br>
     *     a value less than {@code 0} if {@code lhs.priority() > rhs.priority()} and<br>
     *     a value greater than {@code 0} if {@code lhs.priority() < rhs.priority()}
     *
     * @exception EserialNotEserialAnnotationException if {@code lhs} or {@code rhs} is not an {@link EserialAnnotation}
     * @see Integer#compare
     */
    public static int compare(Annotation lhs, Annotation rhs) {
        if (!isEserialAnnotation(lhs)) {
            throw new EserialNotEserialAnnotationException(lhs.annotationType().getSimpleName());
        }
        if (!isEserialAnnotation(rhs)) {
            throw new EserialNotEserialAnnotationException(rhs.annotationType().getSimpleName());
        }

        int lhsPriority = lhs.annotationType().getDeclaredAnnotation(EserialAnnotation.class).priority();
        int rhsPriority = rhs.annotationType().getDeclaredAnnotation(EserialAnnotation.class).priority();

        return -Integer.compare(lhsPriority, rhsPriority);
    }

    /**
     * @param element an element on which annotations are searched
     * @param annotationClass the class of annotation to find
     * @param <T> the type of the annotation
     * @return {@code true} if there is an annotation with the given type for the element
     */
    public static <T extends Annotation> boolean hasAnnotation(EserialElement element, Class<T> annotationClass) {
        return getAnnotation(element, annotationClass) != null;
    }

    /**
     * @param element an element on which annotations are searched
     * @param annotationClass the class of annotation to find
     * @param <T> the type of the annotation for convenient casting of return value
     * @return the annotation on the element with the given type or {@code null} if none was found
     */
    public static <T extends Annotation> T getAnnotation(EserialElement element, Class<T> annotationClass) {
        Annotation annotation = null;
        if (element.getField() != null) {
            annotation = element.getField().getDeclaredAnnotation(annotationClass);
        }
        if (annotation == null && element.getAccessor() != null) {
            annotation = element.getAccessor().getMethod().getDeclaredAnnotation(annotationClass);
        }
        if (annotation == null && element.getContainingClass() != null) {
            annotation = element.getContainingClass().getDeclaredAnnotation(annotationClass);
        }
        return (T) annotation;
    }

    /**
     * Decides whether an {@code element} should be included depending on the {@link EserialAnnotation}s on its
     * field (if exists), accessor and containing class.<br>
     * The annotations are ordered by their priorities.
     * @param element an element containing an accessor and a field
     * @return {@code true} if the {@code element} should be included
     *
     * @see EserialAnnotation#priority()
     */
    public static boolean shouldIncludeElement(EserialElement element) {

        Class clazz = element.getContainingClass();

        List<Annotation> annotations = new ArrayList<>();

        annotations.addAll(AnnotationUtils.getEserialAnnotations(clazz, INCLUSION));
        annotations.addAll(AnnotationUtils.getEserialAnnotations(element.getAccessor(), INCLUSION));
        String fieldName = element.getAccessor().getElementName();
        Field field = FieldUtils.getField(clazz, fieldName);
        if (field != null) {
            annotations.addAll(AnnotationUtils.getEserialAnnotations(field, INCLUSION));
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
