package tech.chorume.bot.core;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import tech.chorume.bot.core.interfaces.DiscordBotConfiguration;
import tech.chorume.bot.core.interfaces.SlashCommandHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DiscordBotConfigurationDefault implements DiscordBotConfiguration {
    private Dotenv dotenv;
    public DiscordBotConfigurationDefault() {
        dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .filename(".env")
                .load();
    }
    @Override
    public String getDiscordToken() {
        var token = dotenv.get("bot.token");
        return token;
    }
    /**
     * Define os gateways padrões do Bot Discord. Por padrão, ele usa os seguintes Gateways:
     * - GUILD_MESSAGES
     * - DIRECT_MESSAGES
     * @return {@link Collection<GatewayIntent>}
     */
    @Override
    public Collection<GatewayIntent> getGatewayIntents() {
        //https://docs.jda.wiki/net/dv8tion/jda/api/requests/GatewayIntent.html
        return Set.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
    }

    /**
     * Define os listeners padrões do Bot Discord. Por padrão, ele registra o dispatcher default do bot, que tem um mapa
     * que lida com todos os comandos previamente determinados.
     * @param handlersMap
     * @return {@link Collection<ListenerAdapter>}
     */
    @Override
    public Collection<EventListener> getListeners(Map<String, SlashCommandHandler> handlersMap) {
        return List.of(new DispatcherListener(handlersMap));
    }
}
