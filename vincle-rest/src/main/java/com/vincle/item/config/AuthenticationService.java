package com.vincle.item.config;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService implements IAuthentication {

    public String getUserName() {
        //TODO si se usa spring boot consume oauth2 service aqui se debe de hacer la implementacion
        return "jeanquero";
    }

}
