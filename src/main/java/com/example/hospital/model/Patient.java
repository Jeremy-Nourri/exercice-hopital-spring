package com.example.hospital.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Patient {
    private Long id;
    @NotNull(message = "Veuillez saisir le nom")
    @NotBlank
    private String lastName;
    @NotNull(message = "Veuillez saisir le prénom")
    @NotBlank
    private String firstName;
    @NotNull(message = "Veuillez saisir la date de naissance")
    @Past
    private LocalDate birthDate;
}
