package hu.elte.eserial.builder;

import hu.elte.eserial.util.TypeUtils;

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
    public static AbstractBuilder create(Class type) {
        if (!TypeUtils.isCompound(type)) {
            return new SimpleBuilder(type);
        } else {
            if (TypeUtils.isCollection(type)) {
                return new CollectionBuilder(type);
            } else if (TypeUtils.isMap(type)) {
                return new MapBuilder(type);
            } else if (TypeUtils.isEnum(type)) {
                return new EnumBuilder(type);
            } else if (TypeUtils.isDate(type)) {
                return new DateBuilder(type);
            } else {
                return new CompoundBuilder(type);
            }
        }
    }
}
