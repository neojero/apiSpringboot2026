package training.afpa.cda24060.api2026.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Methode Test getPersons()
     * @throws Exception
     */
    @Test
    public void getPersonsTest() throws Exception {
        performGetPersonsRequest();
    }

    /**
     * Methode de controle que la 1ere personne du Json est Jemima
     * @throws Exception
     */
    private void performGetPersonsRequest() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname", is("Orlando")));
    }


}
