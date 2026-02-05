package training.afpa.cda24060.api2026.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {
    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("./")  // Chemin absolu ou relatif vers le .env
                .load();
    }
}
