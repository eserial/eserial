package hu.elte.eserial.serializer;

/**
 * Common superclass for the different serializers.
 */
abstract class AbstractSerializer {

    protected Object object;

    /**
     * Stores an object reference for serialization.
     * @param object object to be serialized
     */
    AbstractSerializer(Object object) {
        this.object = object;
    }

    /**
     * Returns the string representation of {@code object} depending on its type.
     *
     * @return string representation of {@code object}
     */
    abstract String serialize();
}
