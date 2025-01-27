package tech.chorume.bot.commands;

import java.util.logging.Logger;

import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import tech.chorume.bot.core.annotations.CommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandHandler;

@CommandBuilder
public class CoinsBuilder implements SlashCommandBuilder {
	
	private static final Logger log = Logger.getLogger(CoinsBuilder.class.getName());
	
    @Override
    public SlashCommandData buildCommand() {
    	log.info("Comando /coins ativado");
        return Commands.slash("coins", "Quero mudar a descrição do comando.");
    }
    @Override
    public SlashCommandHandler buildHandler() {
        return event -> event.reply("Teste").queue();
    }
}
