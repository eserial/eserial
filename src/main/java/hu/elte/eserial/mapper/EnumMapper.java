package hu.elte.eserial.mapper;

import hu.elte.eserial.annotation.Enumerated;
import hu.elte.eserial.annotation.enumeration.EnumeratedFormat;
import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.model.EserialContext;
import hu.elte.eserial.util.AnnotationUtils;
import hu.elte.eserial.util.TypeUtils;

/**
 * Maps Enum-like objects.
 */
public class EnumMapper extends AbstractMapper {

    /**
     * Constructs an {@link EnumMapper} and sets the {@code object} in it.
     *
     * @param object the {@link Enum} to be used in the {@link AbstractMapper#map} method
     */
    EnumMapper(Object object) {
        super(object);
    }

    /**
     * @param context {@inheritDoc}
     * @return mapped representation of the contained {@link Enum}
     */
    @Override
    public Object map(EserialContext context) {
        if (!TypeUtils.isEnum(this.object.getClass())) {
            throw new EserialMapperMismatchException(Enum.class.getSimpleName(),
                    this.object.getClass().getSimpleName());
        }

        Enum enumValue = (Enum) this.object;
        Enumerated enumerated = AnnotationUtils.getAnnotation(context, Enumerated.class);
        if (enumerated != null && enumerated.value().equals(EnumeratedFormat.NAME)) {
            return enumValue.name();
        }
        else {
            return enumValue.ordinal();
        }
    }
}
