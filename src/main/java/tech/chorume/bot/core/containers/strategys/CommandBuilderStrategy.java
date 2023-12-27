package tech.chorume.bot.core.containers.strategys;

import tech.chorume.bot.core.annotations.CommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandBuilder;

public class CommandBuilderStrategy extends BaseStrategy<SlashCommandBuilder> {
    public CommandBuilderStrategy(Class mainClass) {
        super(CommandBuilder.class, mainClass.getPackage(), SlashCommandBuilder.class);
    }
}
