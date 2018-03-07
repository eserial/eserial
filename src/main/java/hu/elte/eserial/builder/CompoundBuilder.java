package hu.elte.eserial.builder;

import hu.elte.eserial.model.Setter;
import hu.elte.eserial.util.MethodUtils;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Method;
import java.util.Map;

public class ObjectBuilder {

    public Object build(Map<String, Object> map, Object that) {
        try {
            for (Method method: that.getClass().getMethods()) {
                if (MethodUtils.isIgnored(method) || !MethodUtils.isSetter(method)) {
                    continue;
                }

                Setter setter = new Setter(that, method);

                String fieldName = setter.getElementName();
                Object fieldValue = map.get(fieldName);
                Class type = setter.getParameterType();
                System.out.println(type);

                if (TypeUtils.isCompound(type)) {
                    Object compoundObject = type.newInstance();
                    Map<String, Object> compoundMap = (Map<String, Object>) fieldValue;
                    build(compoundMap, compoundObject);

                    setter.evaluate(compoundObject);
                } else {
                    setter.evaluate(fieldValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return that;
    }
}
