package com.example.safaricomtalenthack.model;


/*This is class is required for creating a response containing the JWT to be returned to the user.

 */
import java.io.Serializable;

public class TokenResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final int expires_in= 500 * 60 * 60;

    public TokenResponse(String jwttoken) {
        this.token = jwttoken;
    }

    public String getToken() {
        return this.token ;
    }

    public String getExpires_in() {
        return String.valueOf(expires_in)+"s";
    }
}
