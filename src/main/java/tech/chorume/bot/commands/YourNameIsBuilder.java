package tech.chorume.bot.commands;

import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import tech.chorume.bot.core.annotations.CommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandHandler;

@CommandBuilder
public class YourNameIsBuilder implements SlashCommandBuilder {
    @Override
    public SlashCommandData buildCommand() {
        return Commands.slash("yournameis", "Qual o seu nome?");
    }
    @Override
    public SlashCommandHandler buildHandler() {
        return event -> {
            var member = event.getInteraction().getMember();
            if (member == null) {
                event.reply("Não sei o seu nome :D").queue();
                return;
            }
            var nickname = member.getEffectiveName();
            event.reply("O seu nome é " + nickname).queue();
        };
    }
}
