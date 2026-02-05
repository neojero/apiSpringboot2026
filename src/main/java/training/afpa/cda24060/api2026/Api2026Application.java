package training.afpa.cda24060.api2026;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Api2026Application {

    static {
        String dbUrl = System.getenv("DB_URL");
        if (dbUrl == null) {
            // 2. Si non, charge le .env (développement local)
            Dotenv dotenv = Dotenv.configure()
                    .directory("./")
                    .load();
            System.setProperty("DB_URL", dotenv.get("DB_URL"));
            System.setProperty("DB_USER", dotenv.get("DB_USER"));
            System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
            System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
        } else {
            // 3. Sinon, utilise les variables d'environnement (GitHub Actions)
            System.setProperty("DB_URL", dbUrl);
            System.setProperty("DB_USER", System.getenv("DB_USER"));
            System.setProperty("DB_PASSWORD", System.getenv("DB_PASSWORD"));
            System.setProperty("DB_DRIVER", System.getenv("DB_DRIVER"));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Api2026Application.class, args);
    }

}
