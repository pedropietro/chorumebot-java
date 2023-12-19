package tech.chorume.bot;


import tech.chorume.bot.core.DiscordBot;
import tech.chorume.bot.core.services.DotEnvService;

public class App {
    public static void main(String[] args) {
       DiscordBot discordBot = new DiscordBot(
               new DotEnvService()
       );
       discordBot.start();
    }
}
