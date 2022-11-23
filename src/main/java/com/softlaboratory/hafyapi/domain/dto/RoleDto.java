package com.softlaboratory.hafyapi.domain.dto;

import com.softlaboratory.hafyapi.domain.dao.RoleDao;
import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link RoleDao} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleDto implements Serializable {

    private static final long serialVersionUID = -4314195404190785688L;

    private Long id;
    private RoleEnum role;

}