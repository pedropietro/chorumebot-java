package tech.chorume.bot.core.containers.strategys;

import tech.chorume.bot.core.annotations.BotConfiguration;
import tech.chorume.bot.core.interfaces.DiscordBotConfiguration;

public class BotConfigurationStrategy extends BaseStrategy<DiscordBotConfiguration> {
    public BotConfigurationStrategy(Class<?> application) {
        super(BotConfiguration.class, application.getPackage(), DiscordBotConfiguration.class);
    }
}
