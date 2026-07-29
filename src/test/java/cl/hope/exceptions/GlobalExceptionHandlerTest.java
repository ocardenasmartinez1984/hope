package cl.hope.exceptions;

import cl.hope.controllers.HopeController;
import cl.hope.mappers.HopeMapper;
import cl.hope.services.HopeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HopeController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HopeService hopeService;

    @MockBean
    private HopeMapper hopeMapper;

    @Test
    void handleGeneral_shouldReturn500_whenUnexpectedExceptionOccurs() throws Exception {
        when(hopeService.getHopes()).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/hopes"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred"));
    }

}
