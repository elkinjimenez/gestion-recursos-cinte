package com.claro.gestionrecursosapi.devops.domain;


import com.claro.gestionrecursosapi.devops.entity.RespuestaDevOpsOcupacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class OcupacionApiImplementacion {

    @Value("${spring.azure.devops.api.add.ocupation}")
    private String api_add_ocupacion;

    @Value("${spring.azure.devops.api.edit.ocupation}")
    private String api_edit_ocupation;

    @Value("${spring.azure.devops.api.list.all.users}")
    private String api_list_all_users;


    @Autowired
    private AutenticacionService serviceauth;

    public boolean agregarOcupacion(List<RespuestaDevOpsOcupacion> itemsOcupacion)  {

        try {
            boolean agregado = false;
            WebClient webclient = serviceauth.getClientWebAutenticado()
                    .mutate()
                    .defaultHeader("Content-Type", "application/json-patch+json").build();

            ClientResponse responseclient = webclient
                    .post()
                    .uri(api_add_ocupacion)
                    .bodyValue(itemsOcupacion)
                    .exchange().block();

            HttpStatus status = responseclient.statusCode();
            if (status == HttpStatus.OK)
                agregado = true;
            return agregado;

        } catch (Exception ex) {
            System.out.println("Error en el mensaje" + ex.getMessage());
            throw ex;
        }
    }


    public boolean actualizarOcupacion(List<RespuestaDevOpsOcupacion> itemsOcupacion) {
        boolean actualizado = false;
        try {
            boolean agregado = false;
            WebClient webclient = serviceauth.getClientWebAutenticado()
                    .mutate()
                    .defaultHeader("Content-Type", "application/json-patch+json").build();

            ClientResponse responseclient = webclient
                    .patch()
                    .uri(api_edit_ocupation)
                    .bodyValue(itemsOcupacion)
                    .exchange().block();

            HttpStatus status = responseclient.statusCode();
            if (status == HttpStatus.OK)
                agregado = true;
            return agregado;

        } catch (Exception ex) {
            System.out.println("Error en el mensaje" + ex.getMessage());
            throw ex;
        }
    }

    public void getAllUsersProyect() {
        boolean actualizado = false;
        try {
            boolean agregado = false;
            ClientResponse responseclient = serviceauth.getClientWebAutenticado()
                    .get()
                    .uri(api_list_all_users)
                    .exchange().block();

            HttpStatus status = responseclient.statusCode();

        } catch (Exception ex) {
            System.out.println("Error en el mensaje" + ex.getMessage());
            throw ex;
        }
    }


}
