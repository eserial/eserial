package hu.elte.eserial;

import hu.elte.eserial.builder.BuilderFactory;
import hu.elte.eserial.mapper.MapperFactory;
import hu.elte.eserial.model.EserialContext;
import hu.elte.eserial.parser.ParserFactory;
import hu.elte.eserial.serializer.SerializerFactory;

public class Eserial {

    public static String toJson(Object object) {
        Object mappedObject = MapperFactory.create(object).map(EserialContext.forRoot(object));
        return SerializerFactory.create(mappedObject).serialize();
    }

    public static <T> T fromJson(Class<T> clazz, String json) {
        Object parsedJson = ParserFactory.create(json).parse();
        return BuilderFactory.create(clazz).build(parsedJson, null);
    }
}
