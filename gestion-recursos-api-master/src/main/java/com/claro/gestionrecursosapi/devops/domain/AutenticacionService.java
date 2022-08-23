package com.claro.gestionrecursosapi.devops.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@Service
public class AutenticacionService {

    @Value("${spring.azure.devops.username}")
    private String username;

    @Value("${spring.azure.devops.password}")
    private String password;

    public WebClient getClientWebAutenticado() {
        try {
            WebClient cliente = WebClient.builder()
                    .filter(basicAuthentication(username, password))
                    .build();
            return cliente;

        } catch (Exception ex) {
            System.out.println("Error :" + ex.getMessage());
            return null;
        }
    }

}
