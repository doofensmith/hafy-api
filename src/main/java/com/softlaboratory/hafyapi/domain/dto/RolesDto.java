package com.softlaboratory.hafyapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.softlaboratory.hafyapi.domain.dao.RolesDao} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RolesDto implements Serializable {

    private static final long serialVersionUID = -4314195404190785688L;

    private Long id;
    private String role;

}