package hu.elte.eserial.builder;

import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
        Class clazz = TypeUtils.convertTypeToClass(type);

        if (!TypeUtils.isCompound(clazz)) {
            return new SimpleBuilder(type);
        } else {
            if (TypeUtils.isCollection(clazz)) {
                return new CollectionBuilder(type);
            } else if (TypeUtils.isMap(clazz)) {
                return new MapBuilder(type);
            } else if (TypeUtils.isEnum(clazz)) {
                return new EnumBuilder(type);
            } else if (TypeUtils.isDate(clazz)) {
                return new DateBuilder(type);
            } else {
                return new CompoundBuilder(type);
            }
        }
    }
}
