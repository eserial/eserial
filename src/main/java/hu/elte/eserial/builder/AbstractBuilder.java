package hu.elte.eserial.builder;

abstract class AbstractBuilder {

    abstract Object build(Object value, Class type);
}
