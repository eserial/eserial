package hu.elte.eserial.builder;

import hu.elte.eserial.util.TypeUtils;

public class BuilderFactory {

    private BuilderFactory() {}

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
