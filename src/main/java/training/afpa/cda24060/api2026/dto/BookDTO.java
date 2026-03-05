package training.afpa.cda24060.api2026.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import training.afpa.cda24060.api2026.model.Book;
import training.afpa.cda24060.api2026.model.Category;
import training.afpa.cda24060.api2026.model.Loan;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookDTO {

    private Integer boo_id;
    private String title;
    private int quantity;
    private String author;

    private CategoryDTO category; // clé etrangere ici

    public BookDTO() {}

    public BookDTO(Book book) {

        this.boo_id = book.getBoo_id();
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.category = new CategoryDTO(book.getCategory());
    }

}
