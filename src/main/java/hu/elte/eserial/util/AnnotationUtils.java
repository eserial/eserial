package hu.elte.eserial.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hu.elte.eserial.annotation.EserialAnnotation;

/**
 * Utility class for {@link EserialAnnotation}s.
 */
public class AnnotationUtils {

    /**
     * @param accessibleObject a field or a method
     * @return a list of {@link EserialAnnotation}s on the {@code accessibleObject}
     *
     * @see AnnotationUtils#getEserialAnnotations(List)
     */
    public static List<Annotation> getEserialAnnotations(AccessibleObject accessibleObject) {
        return getEserialAnnotations(Arrays.asList(accessibleObject.getAnnotations()));
    }

    /**
     * @param clazz a class
     * @return a list of {@link EserialAnnotation}s on the {@code clazz}
     *
     * @see AnnotationUtils#getEserialAnnotations(List)
     */
    public static List<Annotation> getEserialAnnotations(Class clazz) {
        return getEserialAnnotations(Arrays.asList(clazz.getDeclaredAnnotations()));
    }

    /**
     * @param annotations a list of annotations
     * @return the {@code annotations} list's annotations which
     * have an {@link EserialAnnotation} on them
     *
     * @see AnnotationUtils#isEserialAnnotation
     */
    public static List<Annotation> getEserialAnnotations(List<Annotation> annotations) {
        return annotations.stream()
                .filter(AnnotationUtils::isEserialAnnotation)
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
     * Compares to {@link EserialAnnotation}s according to their priorities.
     * @param lhs an {@link EserialAnnotation}
     * @param rhs an {@link EserialAnnotation}
     * @return the value {@code 0} if {@code lhs.priority() == rhs.priority()},<br>
     *     a value less than {@code 0} if {@code lhs.priority() > rhs.priority()} and<br>
     *     a value greater than {@code 0} if {@code lhs.priority() < rhs.priority()}
     *
     * @exception IllegalArgumentException if {@code lhs} or {@code rhs} is not an {@link EserialAnnotation}
     * @see Integer#compare
     */
    public static int compare(Annotation lhs, Annotation rhs) {
        if (!isEserialAnnotation(lhs)) {
            throw new IllegalArgumentException("Not an EserialAnnotation: " + lhs.annotationType().getName());
        }
        if (!isEserialAnnotation(rhs)) {
            throw new IllegalArgumentException("Not an EserialAnnotation: " + rhs.annotationType().getName());
        }

        int lhsPriority = lhs.annotationType().getDeclaredAnnotation(EserialAnnotation.class).priority();
        int rhsPriority = rhs.annotationType().getDeclaredAnnotation(EserialAnnotation.class).priority();

        return -Integer.compare(lhsPriority, rhsPriority);
    }
}
