package hu.elte.eserial;

import hu.elte.eserial.annotation.EserialAnnotation;
import hu.elte.eserial.builder.BuilderFactory;
import hu.elte.eserial.exception.EserialException;
import hu.elte.eserial.mapper.MapperFactory;
import hu.elte.eserial.model.EserialContext;
import hu.elte.eserial.parser.ParserFactory;
import hu.elte.eserial.serializer.SerializerFactory;

/**
 * The main interface for the library. Provides methods to serialize an object to JSON and
 * to parse one from JSON format.
 * Using inner classes of the library is highly discouraged as those only cover parts of the
 * serialization/deserialization process.
 */
public class Eserial {

    private Eserial() {}

    /**
     * Converts an object to JSON format. Handles recursion to not produce infinitely long Strings,
     * processes various {@link EserialAnnotation}s to customize the output.
     * @param object an arbitrary object
     * @return a JSON formatted String representing the object.
     *
     * @throws EserialException if any issues occur during the mapping or serialization process
     */
    public static String toJson(Object object) {
        Object mappedObject = MapperFactory.create(object).map(EserialContext.forRoot(object));
        return SerializerFactory.create(mappedObject).serialize();
    }

    /**
     * Builds an object of the given type from the given JSON formatted String.
     * Processes various {@link EserialAnnotation}s to customize the interpretation of the input.
     * @param clazz the type class of the object to build
     * @param json a JSON formatted String
     * @param <T> the type of the object to build
     * @return an object of the given type, initialized from the given {@code json}
     *
     * @throws EserialException if any issues occur during the parsing or building process
     */
    public static <T> T fromJson(Class<T> clazz, String json) {
        Object parsedJson = ParserFactory.create(json).parse();
        return BuilderFactory.create(clazz).build(parsedJson, null);
    }
}
