package tech.chorume.bot.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import tech.chorume.bot.core.interfaces.DiscordBotConfiguration;
import tech.chorume.bot.core.interfaces.SlashCommandHandler;

public class DiscordBotConfigurationDefault implements DiscordBotConfiguration {

    public DiscordBotConfigurationDefault() {
    }
    @Override
    public String getDiscordToken() {

    	ResourceBundle bundle = ResourceBundle.getBundle("bot");
    	var token = bundle.getString("bot.token");
        return token;
    }
    /**
     * Default gateways
     * @return {@link Collection<GatewayIntent>}
     */
    @Override
    public Collection<GatewayIntent> getGatewayIntents() {
        //https://docs.jda.wiki/net/dv8tion/jda/api/requests/GatewayIntent.html
        return Set.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
    }

    /**
     * Define only the DispatcherListener, which will be responsible for handling all Slash Command Events and delegating them to their respective handlers.
     * @param handlersMap
     * @return {@link Collection<ListenerAdapter>}
     */
    @Override
    public Collection<EventListener> getListeners(Map<String, SlashCommandHandler> handlersMap) {
        return List.of(new DispatcherListener(handlersMap));
    }  

}
