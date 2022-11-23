package com.softlaboratory.hafyapi.config.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = -5893148261707024987L;

    private String fullname;
    private String username;
    private String password;

}
