package training.afpa.cda24060.api2026.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import training.afpa.cda24060.api2026.model.Address;

@Data
@Schema(description = "DTO représentant une adresse, utilisée pour les échanges avec l'API.")
public class AddressDTO {

    // pour swagger
    @Schema(description = "Identifiant unique de l'adresse", example = "1")
    private int add_id;
    @NotBlank(message = "La rue est obligatoire")
    @Schema(description = "rue de l'adresse (obligatoire)", example = "rue du centre")
    private String street;
    @NotBlank(message = "Le code postal est obligatoire")
    @Schema(description = "code postal de l'adresse (obligatoire)", example = "54000")
    private String cp;
    @NotBlank(message = "La ville est obligatoire")
    @Schema(description = "ville de l'adresse (obligatoire)", example = "Nancy")
    private String city;

    /**
     * Constructeur par défaut requis pour la désérialisation JSON.
     */
    public AddressDTO() {}

    /**
     * Constructeur de conversion depuis une entité Address.
     * @param address L'entité Address à convertir en DTO.
     */
    public AddressDTO(Address address) {
        this.add_id = address.getAdd_id();
        this.street = address.getStreet();
        this.cp = address.getCp();
        this.city = address.getCity();
    }
}
