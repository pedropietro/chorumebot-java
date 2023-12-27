package tech.chorume.bot.core.containers.strategys;

import tech.chorume.bot.core.DiscordBotConfigurationDefault;
import tech.chorume.bot.core.annotations.BotConfiguration;
import tech.chorume.bot.core.interfaces.DiscordBotConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Set;

public class BotConfigurationStrategy extends BaseStrategy<DiscordBotConfiguration> {
    public BotConfigurationStrategy(Class<?> application) {
        super(BotConfiguration.class, application.getPackage(), DiscordBotConfiguration.class);
    }
}
