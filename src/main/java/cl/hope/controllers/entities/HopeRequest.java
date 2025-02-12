package cl.hope.controllers.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class HopeRequest {

    @NotNull
    @NotBlank(message = "The name is required.")
    private String name;

    @NotNull
    @NotBlank(message = "The lastName is required.")
    private String lastName;

    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date birthDate;

}
