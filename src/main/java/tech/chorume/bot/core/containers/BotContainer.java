package tech.chorume.bot.core.containers;

import tech.chorume.bot.core.containers.strategys.BaseStrategy;

import java.util.HashSet;
import java.util.Set;

public class BotContainer {
    private final Package mainPackage;
    public BotContainer(Class<?> applicationClass) {
        mainPackage = applicationClass.getPackage();
    }
    private String targetToFQN(Package target) {
        return target.getName().replace(".", System.getProperty("file.separator")).replace(".class", "");
    }
    public Set<Object> extractComponents(BaseStrategy strategy) {
        var loader = Thread.currentThread().getContextClassLoader();
        Set<Object> components = new HashSet<>();
        try {
            var resources = loader.getResources(targetToFQN(mainPackage));
            while(resources.hasMoreElements()) {
                var resource = resources.nextElement();
                var data = strategy.extract(resource);
                components.addAll(data);
            }
            return components;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
