package training.afpa.cda24060.api2026;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Api2026Application {

    static {
        // Charge le .env dès que la classe est chargée (avant le contexte Spring)
        Dotenv dotenv = Dotenv.configure()
                .directory("./")
                .load();
        // Injecte les variables dans les propriétés système
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Api2026Application.class, args);
    }

}
