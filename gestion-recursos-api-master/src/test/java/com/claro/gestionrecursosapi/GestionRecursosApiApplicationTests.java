package com.claro.gestionrecursosapi;

import java.net.URI;
import java.util.HashMap;

import com.claro.gestionrecursosapi.estimaciones.domain.EstimacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.claro.gestionrecursosapi.devops.domain.OcupacionApiImplementacion;

@SpringBootTest
class GestionRecursosApiApplicationTests {

    private OcupacionApiImplementacion implementacion;

    WebTestClient cliente;
    //
    // @Autowired
    // private AutenticacionService service;
    //
    // @Value("${spring.azure.devops.api.list.all.users}")
    // private String api_list_all_users;
    //
    // @Value("${spring.azure.devops.api.list.all.users}")
    // private String api_all_users;
    //
    // @Test
    // void contextLoads() {
    //// servicio.getUsuariosDevops();
    // try {
    // StringBuilder sb = new StringBuilder();
    // Mono<Object[]> response = service.getClientWebAutenticado()
    // .get()
    // .uri(api_all_users)
    // .accept(MediaType.APPLICATION_JSON)
    // .retrieve()
    // .bodyToMono(new ParameterizedTypeReference<Object[]>() {
    // });
    //
    // Object[] data = response.block();
    // if(data != null){
    // sd
    // }
    //
    // } catch (Exception e) {
    // System.out.printf("Error la conexion con la api error : %s ",
    // e.getMessage());
    // }
    // }

//    @BeforeEach
//    private void Setup(ApplicationContext context) {
//        try {
//            String url = "GET https://vssps.dev.azure.com/alminspiraclaro/_apis/graph/users?api-version=7.1-preview.1";
//            cliente = WebTestClient.bindToApplicationContext(context).build();
//
//
//        } catch (Exception e) {
//            System.out.println("error en la conexioin " + e.getMessage());
//        }
//
//    }
//
//

    @Autowired
    EstimacionService servicio;
    @Test
    public void iniciar(){
        servicio.dataHorasExtras(1380, "00000000200000000001");
    }
    

}
