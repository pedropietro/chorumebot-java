package tech.chorume.bot.core.interfaces;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommandHandler {
    void handle(SlashCommandInteractionEvent event);
}
