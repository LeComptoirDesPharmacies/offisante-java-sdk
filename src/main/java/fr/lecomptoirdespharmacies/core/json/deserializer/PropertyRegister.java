package fr.lecomptoirdespharmacies.core.json.deserializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This interface is here to work with polymorphic deserializer
 * Its prevent to have to register class and json key
 * Just Annotate the class set the key and it will be register in the deserialize
 *
 * @see http://www.robinhowlett.com/blog/2015/03/19/custom-jackson-polymorphic-deserialization-without-type-metadata/
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PropertyRegister {
    String key();
}
