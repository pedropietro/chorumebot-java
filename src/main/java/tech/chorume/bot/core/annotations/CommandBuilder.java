package tech.chorume.bot.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Essa anotação determina que esse Componente é um Builder de Comando e, por conta disso,
 * será carregado pela aplicação e usado para definir os comandos do bot.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandBuilder {
}
