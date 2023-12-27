package tech.chorume.bot.commands;

import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import tech.chorume.bot.core.annotations.CommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandHandler;

@CommandBuilder
public class CoinsBuilder implements SlashCommandBuilder {
    @Override
    public SlashCommandData buildCommand() {
        return Commands.slash("coins", "Quero mudar a descrição do comando.");
    }
    @Override
    public SlashCommandHandler buildHandler() {
        return event -> event.reply("teste! Mudado boladão").queue();
    }
}
