package tech.chorume.bot.core.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class CommandHandler {
    public abstract void handle(SlashCommandInteractionEvent event);
}
