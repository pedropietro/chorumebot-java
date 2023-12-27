package tech.chorume.bot.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Essa anotação determina a classe de configuração do Bot.
 * Ela será responsável por criar a instância do framework JDA e configurá-la.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BotConfiguration {
}
