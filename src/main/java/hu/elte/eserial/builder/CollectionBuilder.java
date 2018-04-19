package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import hu.elte.eserial.exception.EserialInputTypeMismatchException;
import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import hu.elte.eserial.model.EserialContext;
import hu.elte.eserial.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Builds Collection objects (e.g List).
 */
public class CollectionBuilder extends AbstractBuilder {

    /**
     * Constructs an {@link CollectionBuilder} and sets the {@code collectionType} in it.
     *
     * @param collectionType the {@link Collection} to be used in the {@link AbstractBuilder#build} method
     */
    CollectionBuilder(Type collectionType) {
        super(collectionType);
    }

    /**
     * @param initializationObject {@inheritDoc}
     * @param context {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return a {@link Collection} of the given {@link Type} initialized from {@code initializationObject}
     * , which is a {@link LinkedList}
     */
    @Override
    public <T> T build(Object initializationObject, EserialContext context) {
        if (initializationObject == null) {
            return null;
        }

        Class typeClass = TypeUtils.convertTypeToClass(type);
        Type argumentType = TypeUtils.getTypeArgument(type, 0);

        if (!TypeUtils.isAssignableFrom(typeClass, Collection.class)) {
            throw new EserialBuilderMismatchException(Collection.class.getSimpleName(), typeClass.getName());
        }

        if (!TypeUtils.isAssignableFrom(initializationObject.getClass(), List.class)) {
            throw new EserialInputTypeMismatchException(List.class.getSimpleName(),
                    initializationObject.getClass().getName());
        }

        try {
            List<Object> initializationList = (List) initializationObject;

            Collection builtCollection = CollectionFactory.create(typeClass);

            if (argumentType == null) {
                builtCollection.addAll(initializationList);
            } else {
                AbstractBuilder abstractBuilder = BuilderFactory.create(argumentType);

                for (Object object : initializationList) {
                    Object builtElement = abstractBuilder.build(object, context);
                    builtCollection.add(builtElement);
                }
            }

            return (T) builtCollection;
        } catch (IllegalAccessException e) {
            throw new EserialInvalidMethodException("Could not initialize collection", e);
        } catch (InstantiationException e) {
            throw new EserialInstantiationException("Could not initialize collection", e);
        }
    }
}
