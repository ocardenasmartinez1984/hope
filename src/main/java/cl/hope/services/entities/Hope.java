package cl.hope.services.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Hope {

    private String name;

    private String lastName;

    private LocalDate birthDate;

}
