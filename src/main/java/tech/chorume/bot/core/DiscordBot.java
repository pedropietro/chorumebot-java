package tech.chorume.bot.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import tech.chorume.bot.core.containers.BotContainer;
import tech.chorume.bot.core.containers.strategys.BotConfigurationStrategy;
import tech.chorume.bot.core.containers.strategys.CommandBuilderStrategy;
import tech.chorume.bot.core.interfaces.DiscordBotConfiguration;
import tech.chorume.bot.core.interfaces.SlashCommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandHandler;
import java.util.*;

public class DiscordBot {
    private final Class<?> applicationClass;
    private BotContainer botContainer;
    public DiscordBot(Class<?> applicationClass) {
        this.applicationClass = applicationClass;
        this.botContainer = new BotContainer(applicationClass);
    }
    private Set<SlashCommandBuilder> getSlashCommands() {
        var strategy = new CommandBuilderStrategy(applicationClass);
        return strategy.cast(botContainer.extractComponents(strategy));
    }
    private DiscordBotConfiguration getConfiguration() {
        var strategy = new BotConfigurationStrategy(applicationClass);
        var configurationComponents = strategy.cast(botContainer.extractComponents(strategy));
        var first = configurationComponents.stream().findFirst();
        return first.orElseGet(DiscordBotConfigurationDefault::new);
    }
    public void start() {
        var configuration = getConfiguration();
        String token = configuration.getDiscordToken();
        if (token == null) {
            throw new RuntimeException("Token cannot be null");
        }
        Collection<SlashCommandBuilder> builders = getSlashCommands();
        Set<SlashCommandData> commands = new HashSet<>();
        Map<String, SlashCommandHandler> handlers = new HashMap<>();
        builders.forEach(builder -> {
            var command = builder.buildCommand();
            var handler = builder.buildHandler();
            handlers.put(command.getName(), handler);
            commands.add(command);
        });
        try {
            // Low memory-consume profile JDA - https://docs.jda.wiki/net/dv8tion/jda/api/JDABuilder.html#createLight(java.lang.String)
            JDA jda = JDABuilder.createLight(token)
                    .enableIntents(configuration.getGatewayIntents())
                    .addEventListeners(configuration.getListeners(handlers).toArray())
                    .build();
            jda.updateCommands()
                            .addCommands(commands)
                                    .queue();
            jda.awaitReady();
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
        cleanStartUP();
    }
    private void cleanStartUP() {
        this.botContainer = null;
        System.gc();
    }
}
