package com.softlaboratory.hafyapi.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1438608322467195214L;

    private String username;
    private String password;

}
