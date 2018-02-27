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
     * @param type an arbitrary class
     * @return an object mapper for {@code type}
     */
    public static ObjectMapper create(Class<?> type) {
        if (TypeUtils.isCompound(type)) {
            if (TypeUtils.isCollection(type)) {
                return new CollectionMapper();
            } else if (TypeUtils.isMap(type)) {
                return new MapMapper();
            } else if (TypeUtils.isEnum(type)) {
                return new EnumMapper();
            } else if (TypeUtils.isArray(type)) {
                return new ArrayMapper();
            } else {
                return new CompoundMapper();
            }
        } else {
            return new SerializableMapper();
        }
    }
}
