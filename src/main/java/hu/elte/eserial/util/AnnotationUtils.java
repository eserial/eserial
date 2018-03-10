package hu.elte.eserial.util;

import hu.elte.eserial.annotation.EserialAnnotation;
import hu.elte.eserial.annotation.enumeration.EserialAnnotationType;
import hu.elte.eserial.exception.EserialNotEserialAnnotationException;
import hu.elte.eserial.model.EserialContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for {@link EserialAnnotation}s.
 */
public class AnnotationUtils {

    /**
     * @param accessibleObject a field or a method
     * @param type the type of {@link EserialAnnotation}
     * @return a list of {@link EserialAnnotation}s on the {@code accessibleObject}
     *
     * @see AnnotationUtils#getEserialAnnotations(List, EserialAnnotationType)
     */
    public static List<Annotation> getEserialAnnotations(AccessibleObject accessibleObject, EserialAnnotationType type) {
        return getEserialAnnotations(Arrays.asList(accessibleObject.getAnnotations()), type);
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
     * @param context the context of an element in which annotations are searched
     * @param annotationClass the class of annotation to find
     * @param <T> the type of the annotation
     * @return {@code true} if there is an annotation with the given type for the element
     */
    public static <T extends Annotation> boolean hasAnnotation(EserialContext context, Class<T> annotationClass) {
        return getAnnotation(context, annotationClass) != null;
    }

    /**
     * @param context the context of an element in which annotations are searched
     * @param annotationClass the class of annotation to find
     * @param <T> the type of the annotation for convenient casting of return value
     * @return the annotation on the element with the given type or {@code null} if none was found
     */
    public static <T extends Annotation> T getAnnotation(EserialContext context, Class<T> annotationClass) {
        Annotation annotation = null;
        if (context.getField() != null) {
            annotation = context.getField().getDeclaredAnnotation(annotationClass);
        }
        if (annotation == null && context.getGetter() != null) {
            annotation = context.getGetter().getMethod().getDeclaredAnnotation(annotationClass);
        }
        if (annotation == null && context.getContainingClass() != null) {
            annotation = context.getContainingClass().getDeclaredAnnotation(annotationClass);
        }
        return (T) annotation;
    }
}
