package training.afpa.cda24060.api2026.mapper;

import org.mapstruct.Mapper;
import training.afpa.cda24060.api2026.dto.AddressDTO;
import training.afpa.cda24060.api2026.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDTO addressDTO);
    AddressDTO toDTO(Address address);
}
