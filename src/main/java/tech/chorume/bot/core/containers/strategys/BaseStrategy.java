package tech.chorume.bot.core.containers.strategys;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseStrategy<T>{
    protected final Class<? extends  Annotation> annotation;
    private final Package mainPackage;
    private final Class<?> mainInterface;
    public BaseStrategy(Class<? extends Annotation> annotationTarget, Package mainPackage, Class<?> mainInterface) {
        annotation = annotationTarget;
        this.mainPackage = mainPackage;
        this.mainInterface = mainInterface;
    }
    protected String targetToFQN(Package target) {
        return target.getName().replace(".class", "").replace(".", System.getProperty("file.separator"));
    }
    protected String targetToFQN(File target) {
        var packagePath = targetToFQN(mainPackage);
        var path = target.getPath();
        var fqn = path.substring(path.indexOf(packagePath));
        return fqn.replace(".class", "").replace(System.getProperty("file.separator"), ".");
    }
    protected boolean classApplyAnnotationTarget(Class<?> target) {
        return target.isAnnotationPresent(annotation);
    }
    protected boolean classApplyInterfaceTarget(Class<?> target) {
        return mainInterface.isAssignableFrom(target);
    }
    protected Object createInstance(String fqn) {
        try {
            var classTarget = Class.forName(fqn);
            if (classTarget.isAnonymousClass()) {
                return null;
            }
            if(!classApplyAnnotationTarget(classTarget)) {
                return null;
            }
            if (!classApplyInterfaceTarget(classTarget)) {
                throw new RuntimeException("Class " + fqn + " annotated with " + annotation + " should apply " + mainInterface);
            }
            var defaultConstructor = classTarget.getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            return defaultConstructor.newInstance();
        } catch (NoSuchMethodException noSuchMethodException) {
            throw new RuntimeException("Default constructor must be informed to automatically load it -> " + fqn);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Collection<?> extract(URL resourceURL) throws MalformedURLException {
        var resourceFile = new File(resourceURL.getFile());
        var objects = new HashSet<>();
        if (resourceFile.isFile()) {
            var fqn = targetToFQN(resourceFile);
            objects.add(createInstance(fqn));
            return objects;
        }
        for (File f: Objects.requireNonNull(resourceFile.listFiles())) {
            Collection<?> objectSet = extract(new URL("file://"+f.getAbsolutePath()));
            objects.addAll(objectSet);
        }
        return objects.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }
    public Set<T> cast(Set<Object> data) {
        return data.stream().map(v -> (T) v).collect(Collectors.toSet());
    }
}
