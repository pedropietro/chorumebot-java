package tech.chorume.bot;


import tech.chorume.bot.core.DiscordBot;
import tech.chorume.bot.core.annotations.CommandBuilder;
import tech.chorume.bot.core.containers.BotContainer;
import tech.chorume.bot.core.containers.strategys.BaseStrategy;
import tech.chorume.bot.core.containers.strategys.CommandBuilderStrategy;
import tech.chorume.bot.core.interfaces.SlashCommandBuilder;

public class App {
    public static void main(String[] args) {
       var discordBot = new DiscordBot(App.class);
       discordBot.start();
    }
}
