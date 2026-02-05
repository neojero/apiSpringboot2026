package training.afpa.cda24060.api2026.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
/**
 * EnvConfig est une classe de configuration Spring qui s’exécute avant la configuration de la datasource.
 * Les propriétés sont injectées directement dans l’environnement Spring via env.getPropertySources().addFirst().
 */
public class EnvConfig {
    public EnvConfig(ConfigurableEnvironment env) {
        String isCi = System.getenv("CI");
        if ("true".equals(isCi)) {
            // En CI, injecte les variables d'environnement dans Spring
            Map<String, Object> properties = new HashMap<>();
            properties.put("spring.datasource.url", System.getenv("DB_URL"));
            properties.put("spring.datasource.username", System.getenv("DB_USER"));
            properties.put("spring.datasource.password", System.getenv("DB_PASSWORD"));
            properties.put("spring.datasource.driver-class-name", System.getenv("DB_DRIVER"));
            System.out.println("DB_URL: " + System.getenv("DB_URL"));
            env.getPropertySources().addFirst(new MapPropertySource("ciEnv", properties));
        }
    }
}
