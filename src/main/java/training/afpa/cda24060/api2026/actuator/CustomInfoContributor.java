package training.afpa.cda24060.api2026.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {

        builder.withDetail("app", Map.of(
                "name", "API SpringBoot 2026",
                "profile", System.getProperty("spring.profiles.active", "default"),
                "version", "1.0.0",
                "description", "Gestion de personnes",
                "author", "neojero",
                "java.version", System.getProperty("java.version"),
                "contact", "support@gestperson.com",
                "os.name", System.getProperty("os.name"),
                "os.version", System.getProperty("os.version"),
                "user.timezone", System.getProperty("user.timezone")
        ));

    }
}
