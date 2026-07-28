package cl.hope.controllers;

import cl.hope.repositories.HopeRepository;
import cl.hope.repositories.entities.HopeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HopeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HopeRepository hopeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        hopeRepository.deleteAll();
    }

    @Test
    void createHope_shouldReturn201() throws Exception {
        var request = """
                {
                    "name": "John",
                    "lastName": "Doe",
                    "birthDate": "15/01/1990"
                }
                """;

        mockMvc.perform(post("/hopes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void createHope_shouldReturn400_whenNameIsBlank() throws Exception {
        var request = """
                {
                    "name": "",
                    "lastName": "Doe",
                    "birthDate": "15/01/1990"
                }
                """;

        mockMvc.perform(post("/hopes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors", hasSize(greaterThan(0))));
    }

    @Test
    void getHopes_shouldReturnList() throws Exception {
        var entity = new HopeEntity();
        entity.setName("Jane");
        entity.setLastName("Smith");
        entity.setBirthDate(LocalDate.of(1985, 5, 20));
        hopeRepository.save(entity);

        mockMvc.perform(get("/hopes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Jane"));
    }

    @Test
    void getHope_shouldReturnHope_whenExists() throws Exception {
        var entity = new HopeEntity();
        entity.setName("Jane");
        entity.setLastName("Smith");
        entity.setBirthDate(LocalDate.of(1985, 5, 20));
        entity = hopeRepository.save(entity);

        mockMvc.perform(get("/hopes/" + entity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    void getHope_shouldReturn404_whenNotFound() throws Exception {
        mockMvc.perform(get("/hopes/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Hope not found with id: 999"));
    }

}
