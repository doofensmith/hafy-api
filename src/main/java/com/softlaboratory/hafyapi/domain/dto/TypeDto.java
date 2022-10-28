package com.softlaboratory.hafyapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.softlaboratory.hafyapi.constant.AppConstant.AccountType;

/**
 * A DTO for the {@link com.softlaboratory.hafyapi.domain.dao.TypeDao} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TypeDto implements Serializable {

    private static final long serialVersionUID = -2481433282560446536L;

    private Long id;
    private AccountType type;
}