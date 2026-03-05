package training.afpa.cda24060.api2026.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import lombok.*;
import training.afpa.cda24060.api2026.model.Loan;

import java.time.LocalDate;

@Getter         // Génère les getters
@AllArgsConstructor  // Génère un constructeur avec tous les arguments
@NoArgsConstructor   // Génère un constructeur sans argument (utile pour certains frameworks)
public class LoanDTO {

    private Long id;
    private BookDTO book;
    @NotNull(message = "La date d'emprunt est obligatoire")
    private LocalDate loanDate;
    @NotNull(message = "La date de retour est obligatoire")
    private LocalDate returnDate;
    private String status;
    private boolean isOverdue;

    // Constructeur personnalisé pour convertir un Loan en LoanDTO
    public LoanDTO(Loan loan) {
        this.id = loan.getLoan_id();
        this.book = new BookDTO(loan.getBook());
        this.loanDate = loan.getLoanDate();
        this.returnDate = loan.getReturnDate();
        this.status = loan.getStatus();
        this.isOverdue = this.calculateIsOverdue(loan);
    }

    // Méthode privée pour calculer si l'emprunt est en retard
    private boolean calculateIsOverdue(Loan loan) {
        return loan.getReturnDate() != null &&
                loan.getReturnDate().isBefore(LocalDate.now()) &&
                "ACTIVE".equals(loan.getStatus());
    }
}
