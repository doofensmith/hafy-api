package com.softlaboratory.hafyapi.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * A DTO for the {@link com.softlaboratory.hafyapi.domain.dao.TypeDao} entity
 */
@Data
public class TypeDto implements Serializable {

    private static final long serialVersionUID = -2481433282560446536L;

    private final Long id;
    private final String type;
}