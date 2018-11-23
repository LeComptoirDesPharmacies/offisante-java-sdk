package fr.lecomptoirdespharmacies.core.manager;

import fr.lecomptoirdespharmacies.core.json.deserializer.PropertyRegister;
import lombok.NonNull;
import org.reflections.Reflections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Actually used to get only Property Register keys
 */
public class ReflectionManager {

    private final Reflections reflections;

    public ReflectionManager(String packageName) {
        this.reflections = new Reflections(packageName);
    }

    /**
     *          Find all class who has been annotated with PropertyRegister
     * @return  Set of class
     */
    private Set<Class<?>> findClassAnnotate(){
        return reflections.getTypesAnnotatedWith(PropertyRegister.class);
    }

    /**
     *          Check if annotation is present
     * @return  Set of Class when annotation is present
     */
    private Set<Class<?>> getOnlyAnnotatedClass(){
        return findClassAnnotate()
                .stream()
                .filter(c -> c.isAnnotationPresent(PropertyRegister.class))
                .collect(Collectors.toSet());

    }

    /**
     *                  Return Class annotated an key value
     * @param cls       Sub class
     * @param filter    Filter list of classes
     * @param <T>       Parent class
     * @return          Map of key and class
     */
    public <T> Map<String, Class<? extends T>> getPropertyRegisterKey(@NonNull Class<T> cls, @NonNull Class<? extends T> filter){
        Set<Class<?>> classes = getOnlyAnnotatedClass();
        return classes.stream()
                .filter(c -> c.isAssignableFrom(filter))
                .collect(Collectors.toMap(
                        c -> c.getAnnotation(PropertyRegister.class).key(),
                        c -> c.asSubclass(cls))
                );
    }
}
