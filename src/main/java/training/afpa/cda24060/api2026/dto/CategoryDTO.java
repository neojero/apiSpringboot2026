package training.afpa.cda24060.api2026.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import training.afpa.cda24060.api2026.model.Category;

@Data
public class CategoryDTO {

    private Integer id;
    @NotNull(message = "Le nom est obligatoire")
    private String name;
    @NotNull(message = "La description est obligatoire")
    private String description;

    public CategoryDTO() {}

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }
}
