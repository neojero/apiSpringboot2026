package training.afpa.cda24060.api2026.controller;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import training.afpa.cda24060.api2026.dto.filter.PersonFilterDTO;
import training.afpa.cda24060.api2026.model.Person;
import training.afpa.cda24060.api2026.service.PersonService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    @Description("Test de récupération de la liste des personnes")
    @Severity(SeverityLevel.CRITICAL)
    public void getPersonsTest() throws Exception {

        // Mock du retour (Page vide)
        Page<Person> page = new PageImpl<>(List.of());

        when(personService.getPersons(any(Pageable.class), any(PersonFilterDTO.class)))
                .thenReturn(page);

        mockMvc.perform(get("/persons")
                                .param("page", "0")
                                .param("size", "10")
                        // tu peux ajouter des filtres si ton controller les attend
                        // .param("lastname", "Doe")
                        // .param("firstname", "John")
                )
                .andExpect(status().isOk());
    }

}
