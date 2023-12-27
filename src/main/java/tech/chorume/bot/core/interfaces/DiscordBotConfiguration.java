package tech.chorume.bot.core.interfaces;

import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Collection;
import java.util.Map;

public interface DiscordBotConfiguration {
    /**
     * Recupera o token do Discord BOT.
     * @return {@link String}
     */
    String getDiscordToken();
    /**
     * Determina quais intents o bot precisa.
     * @return {@link Collection<GatewayIntent>}
     */
    Collection<GatewayIntent> getGatewayIntents();

    /**
     * Determina quais listeners o bot irá iniciar e oferece, como opção, os handlers definidos na aplicação.
     * @return {@link Collection<ListenerAdapter>}
     */
    Collection<EventListener> getListeners(Map<String, SlashCommandHandler> handlersMap);
}
