package tech.chorume.bot.core.containers.strategys;

import tech.chorume.bot.core.annotations.CommandBuilder;
import tech.chorume.bot.core.interfaces.SlashCommandBuilder;

import java.util.Set;
import java.util.stream.Collectors;

public class CommandBuilderStrategy extends BaseStrategy<SlashCommandBuilder> {
    public CommandBuilderStrategy(Class mainClass) {
        super(CommandBuilder.class, mainClass.getPackage(), SlashCommandBuilder.class);
    }
}
