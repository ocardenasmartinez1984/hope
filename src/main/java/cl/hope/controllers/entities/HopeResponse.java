package cl.hope.controllers.entities;

import lombok.Data;

import java.util.Date;

@Data
public class HopeResponse {

    private String name;

    private String lastName;

    private Date birthDate;

}
