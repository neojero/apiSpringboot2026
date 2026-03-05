package training.afpa.cda24060.api2026.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import training.afpa.cda24060.api2026.dto.PersonDTO;
import training.afpa.cda24060.api2026.model.Person;

@Mapper(
        componentModel = "spring",
        uses = {AddressMapper.class}  // Pour gérer AddressDTO ↔ Address)
)
public interface PersonMapper {

    // Conversion DTO → Entité (création)
    @Mapping(target = "id", ignore = true)  // Ignore l'ID (auto-généré)
    @Mapping(target = "loans", ignore = true)  // Ignore les emprunts (gérés séparément)
    Person toEntity(PersonDTO personDTO);

    // Conversion Entité → DTO (summary)
    PersonDTO toDTO(Person person);

}
