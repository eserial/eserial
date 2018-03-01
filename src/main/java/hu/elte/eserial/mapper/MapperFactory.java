package hu.elte.eserial.mapper;

import hu.elte.eserial.util.TypeUtils;

/**
 * Provides an adequate mapper implementation for a given type.
 */
public class MapperFactory {

    /**
     * Prevents the accidental instantiation of this factory class.
     */
    private MapperFactory() {}

    /**
     * Creates a type-specific object mapper instance.
     *
     * @param {@code type} an arbitrary class
     * @return an object mapper for {@code type}
     */
    public static AbstractMapper create(Object object) {
        if (object == null || !TypeUtils.isCompound(object.getClass())) {
            return new SimpleMapper(object);
        }
        else {
            Class type = object.getClass();
            if (TypeUtils.isCollection(type)) {
                return new CollectionMapper(object);
            } else if (TypeUtils.isMap(type)) {
                return new MapMapper(object);
            } else if (TypeUtils.isEnum(type)) {
                return new EnumMapper(object);
            } else if (TypeUtils.isArray(type)) {
                return new ArrayMapper(object);
            } else if (TypeUtils.isDate(type)) {
                return new DateMapper(object);
            } else {
                return new CompoundMapper(object);
            }
        }
    }
}
