package tech.chorume.bot.core.services;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvService {
    private Dotenv dotenv;
    public DotEnvService() {
        dotenv = Dotenv.configure().filename(".env")
                .load();
    }
    public String get(String key) {
        return dotenv.get(key);
    }
}
