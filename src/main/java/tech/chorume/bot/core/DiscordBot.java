package tech.chorume.bot.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import tech.chorume.bot.core.commands.CommandBuilder;
import tech.chorume.bot.core.commands.CommandHandler;
import tech.chorume.bot.core.services.DotEnvService;
import java.util.*;

public class DiscordBot {
    private DotEnvService dotEnvService;
    private Map<String, CommandHandler> handlers = new HashMap<>();
    public DiscordBot(DotEnvService dotEnvService) {
        this.dotEnvService = dotEnvService;
    }
    public Collection<GatewayIntent> collectNeededIntents() {
        //https://docs.jda.wiki/net/dv8tion/jda/api/requests/GatewayIntent.html
        return Set.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
    }
    private Collection<CommandBuilder> getSlashCommands() {
        return Set.of(
                new CommandBuilder("coins", "Saiba quantas coins você tem") {}
        );
    }
    public void start() {
        String token = dotEnvService.get("bot.token");

        if (token == null) {
            throw new RuntimeException("Token cannot be null");
        }

        Collection<CommandBuilder> builders = getSlashCommands();
        Set<SlashCommandData> commands = new HashSet<>();

        builders.forEach(builder -> {
            var command = builder.buildCommand();
            var handler = builder.buildHandler();
            handlers.put(command.getName(), handler);
            commands.add(command);
        });

        try {
            // Low memory-consume profile JDA - https://docs.jda.wiki/net/dv8tion/jda/api/JDABuilder.html#createLight(java.lang.String)
            JDA jda = JDABuilder.createLight(token)
                    .enableIntents(collectNeededIntents())
                    .addEventListeners(new DispatcherListener(handlers))
                    .build();
            jda.updateCommands()
                            .addCommands(commands)
                                    .queue();
            jda.awaitReady();
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
    }
}
