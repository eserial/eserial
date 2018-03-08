package hu.elte.eserial.builder;

import hu.elte.eserial.model.Setter;
import hu.elte.eserial.util.MethodUtils;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public class CompoundBuilder {

    public Object build(Map<String, Object> map, Object that) {
        try {
            for (Method method: that.getClass().getMethods()) {
                if (MethodUtils.isIgnored(method) || !MethodUtils.isSetter(method)) {
                    continue;
                }

                Setter setter = new Setter(that, method);

                String fieldName = setter.getElementName();
                Object fieldValue = map.get(fieldName);
                Class<?> type = setter.getParameterType();
               // System.out.println(type);

                if (TypeUtils.isCompound(type)) {
                    if (TypeUtils.isCollection(type)) {
                        Collection collectionObject = CollectionBuilder.build(fieldValue, type);
                        setter.evaluate(collectionObject);
                    } else if (TypeUtils.isMap(type)) {
                        Map<Object, Object> mapObject = MapBuilder.build(fieldValue, type);
                        setter.evaluate(mapObject);
                    } else if (TypeUtils.isEnum(type)) {
                            Object enumValue = EnumBuilder.build(fieldValue, type);
                            setter.evaluate(enumValue);
                    } else {
                        Object compoundObject = type.newInstance();
                        Map<String, Object> compoundMap = (Map<String, Object>) fieldValue;
                        build(compoundMap, compoundObject);

                        setter.evaluate(type.cast(compoundObject));
                    }
                } else if (TypeUtils.isPrimitive(type) || TypeUtils.isWrapper(type)){
                    Object primitiveValue = PrimitiveBuilder.build(fieldValue, type);
                    setter.evaluate(primitiveValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return that;
    }
}
