package hu.elte.eserial.builder;

abstract class AbstractBuilder {

    protected Class type;

    AbstractBuilder(Class type) {
        this.type = type;
    }

    abstract <T> T build(Object value);
}
