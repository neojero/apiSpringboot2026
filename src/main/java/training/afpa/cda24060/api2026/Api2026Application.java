package training.afpa.cda24060.api2026;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Api2026Application {

    static {
        // 1. Vérifie si on est en environnement CI (GitHub Actions)
        String isCi = System.getenv("CI");
        if ("true".equals(isCi)) {
            // En CI, injecte directement les variables d'environnement dans les propriétés système
            System.setProperty("spring.datasource.url", System.getenv("DB_URL"));
            System.setProperty("spring.datasource.username", System.getenv("DB_USER"));
            System.setProperty("spring.datasource.password", System.getenv("DB_PASSWORD"));
            System.setProperty("spring.datasource.driver-class-name", System.getenv("DB_DRIVER"));
        } else {
            // En local, charge le .env
            Dotenv dotenv = Dotenv.configure()
                    .directory("./")
                    .load();
            System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
            System.setProperty("spring.datasource.username", dotenv.get("DB_USER"));
            System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
            System.setProperty("spring.datasource.driver-class-name", dotenv.get("DB_DRIVER"));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Api2026Application.class, args);
    }

}
