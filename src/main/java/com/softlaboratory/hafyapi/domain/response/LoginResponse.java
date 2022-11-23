package com.softlaboratory.hafyapi.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = -254139379680338618L;

    private String token;

}
