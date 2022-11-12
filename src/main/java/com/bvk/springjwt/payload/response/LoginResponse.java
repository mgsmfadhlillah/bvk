package com.bvk.springjwt.payload.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private JwtResponse result;
    private String status;

    public LoginResponse(String msg, JwtResponse resp, String sts){
        this.message = msg;
        this.result = resp;
        this.status = sts;
    }
}
