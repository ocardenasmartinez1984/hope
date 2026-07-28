package cl.hope.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HopeRequest {

    @NotBlank(message = "The name is required.")
    private String name;

    @NotBlank(message = "The lastName is required.")
    private String lastName;

    @NotNull(message = "The birthDate is required.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

}
