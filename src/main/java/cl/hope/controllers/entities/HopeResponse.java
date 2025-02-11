package cl.hope.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class HopeResponse {

    private String name;

    private String lastName;

    private Date birthDate;

}
