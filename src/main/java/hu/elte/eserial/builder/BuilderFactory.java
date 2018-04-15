package hu.elte.eserial.builder;

import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Provides an adequate builder implementation for a given type.
 */
public class BuilderFactory {

    /**
     * Prevents the accidental instantiation of this factory class.
     */
    private BuilderFactory() {}

    /**
     * Creates a type-specific object builder instance.
     *
     * @param type an arbitrary class
     * @return an object builder for {@code type}
     */
    public static AbstractBuilder create(Type type) {
        Class classOfType = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isCompound(classOfType)) {
            return new SimpleBuilder(type);
        } else {
            if (TypeUtils.isAssignableFrom(classOfType, Collection.class)) {
                return new CollectionBuilder(type);
            } else if (TypeUtils.isAssignableFrom(classOfType, Map.class)) {
                return new MapBuilder(type);
            } else if (TypeUtils.isAssignableFrom(classOfType, Enum.class)) {
                return new EnumBuilder(type);
            } else if (TypeUtils.isAssignableFrom(classOfType, Date.class)) {
                return new DateBuilder(type);
            } else {
                return new CompoundBuilder(type);
            }
        }
    }
}
