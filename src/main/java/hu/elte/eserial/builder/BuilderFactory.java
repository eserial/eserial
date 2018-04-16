package hu.elte.eserial.builder;

import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Provides an adequate builder implementation for a given {@link Type}.
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
        Class typeClass = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isCompound(typeClass)) {
            return new SimpleBuilder(type);
        } else {
            if (TypeUtils.isAssignableFrom(typeClass, Collection.class)) {
                return new CollectionBuilder(type);
            } else if (TypeUtils.isAssignableFrom(typeClass, Map.class)) {
                return new MapBuilder(type);
            } else if (TypeUtils.isAssignableFrom(typeClass, Enum.class)) {
                return new EnumBuilder(type);
            } else if (TypeUtils.isAssignableFrom(typeClass, Date.class)) {
                return new DateBuilder(type);
            } else {
                return new CompoundBuilder(type);
            }
        }
    }
}
