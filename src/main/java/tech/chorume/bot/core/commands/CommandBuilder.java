package tech.chorume.bot.core.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public abstract class CommandBuilder {
    private String name;
    private String description;
    public CommandBuilder(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public SlashCommandData buildCommand() {
        return Commands.slash(name, description);
    }
    public CommandHandler buildHandler() {
        return new CommandHandler() {
            @Override
            public void handle(SlashCommandInteractionEvent event) {
                System.out.println("Exemplo de interação!");
                event.getInteraction().reply("Interação respondida!")
                        .queue();
            }
        };
    }
}
