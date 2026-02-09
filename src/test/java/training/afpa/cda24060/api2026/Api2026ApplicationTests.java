package training.afpa.cda24060.api2026;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class Api2026ApplicationTests {

    @Test
    @Description("Test de chargement du contexte Spring Boot")
    @Severity(SeverityLevel.BLOCKER)
    void contextLoads() {
        performContextLoad();
    }

    @Step("Charger le contexte Spring Boot")
    private void performContextLoad() {
        // Le contexte Spring Boot est chargé automatiquement par l'annotation @SpringBootTest
        // Aucune action supplémentaire n'est nécessaire ici
    }

}
