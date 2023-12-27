package tech.chorume.bot.core.interfaces;

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;


public interface SlashCommandBuilder {
    SlashCommandData buildCommand();
    SlashCommandHandler buildHandler();
}
