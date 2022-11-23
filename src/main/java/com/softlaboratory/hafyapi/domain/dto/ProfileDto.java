package com.softlaboratory.hafyapi.config.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfileDto implements Serializable {

    private static final long serialVersionUID = -2624627250557582709L;

    private Long id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String address;
    private String birthPlace;
    private LocalDate birthDate;

}
