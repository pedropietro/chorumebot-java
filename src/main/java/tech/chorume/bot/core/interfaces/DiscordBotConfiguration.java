package tech.chorume.bot.core.interfaces;

import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Collection;
import java.util.Map;

public interface DiscordBotConfiguration {
    /**
     * Retrieves the Discord token used for authentication.
     *
     * @return The Discord token as a {@link String}.
     */
    String getDiscordToken();
    /**
     * Retrieves the collection of gateway intents required by the application.
     *
     * @return A collection of {@link GatewayIntent}.
     */
    Collection<GatewayIntent> getGatewayIntents();

    /**
     * Retrieves the collection of event listeners that will be registered
     * and run when the application starts.
     *
     * @param handlersMap A map of slash command names to their corresponding handlers.
     * @return A collection of {@link EventListener}.
     */
    Collection<EventListener> getListeners(Map<String, SlashCommandHandler> handlersMap);
}
