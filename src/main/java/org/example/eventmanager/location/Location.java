package org.example.eventmanager.location;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {

    private Integer id;
    private String name;
    private String address;
    private Integer capacity;
    private String description;

}
