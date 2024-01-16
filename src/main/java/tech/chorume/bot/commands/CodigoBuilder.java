package tech.chorume.bot.commands;

import java.util.logging.Logger;

import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import tech.chorume.bot.core.annotations.GuildCommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandHandler;

@GuildCommandBuilder
public class CodigoBuilder implements SlashCommandBuilder {
	
	private static final Logger log = Logger.getLogger(CodigoBuilder.class.getName());

	
    @Override
    public SlashCommandData buildCommand() {
    	log.info("Comando /codigo ativado");
        return Commands.slash("codigo-pedro", "Este é o código do Bot em Java");
    }
    
    @Override
    public SlashCommandHandler buildHandler() {
        return event -> event.reply("Aí manolo o código do bot tá aqui ó, não palpite, commit! \r\n https://github.com/brunofunnie/chorumebot-java").queue();
    }
}
