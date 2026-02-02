package training.afpa.cda24060.api2026.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {

    // Gestion des exceptions génériques
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleApiException(Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Une erreur est survenue");
        errorResponse.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // Gestion des exceptions "ressource non trouvée"
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NoSuchElementException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Ressource non trouvée");
        errorResponse.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Gestion des erreurs de validation (ex : champ manquant)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Erreur de validation");
        errorResponse.put("message", "Les données envoyées sont invalides");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
