package com.claro.gestionrecursosapi.devops.domain;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.claro.gestionrecursosapi.devops.entity.ResponseUsuariosDev;
import com.claro.gestionrecursosapi.devops.entity.RespuestaDevOpsOcupacion;
import com.claro.gestionrecursosapi.devops.entity.UsuarioDevops;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsuariosInfoDevops {

    @Autowired
    private AutenticacionService service;

    @Value("${spring.azure.devops.api.list.all.users}")
    private String api_all_users;


    public String getUserEmail(String email) throws IOException {
        Charset charset = Charset.forName("US-ASCII");
        Gson gson = new Gson();
        Path currentdirectory = Paths.get("").toAbsolutePath();
        Path completefile = Paths.get(currentdirectory.toString(), "src/main/DevOpsFiles/users.json");
        String response = "";
        if (Files.exists(completefile)) {
            try {
                Reader lector = Files.newBufferedReader(completefile);
                Map<?, ?> mapajsonusuarios = gson.fromJson(lector, Map.class);
                List datausuarios = (ArrayList) mapajsonusuarios.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList()).get(1);

                for (Object data : datausuarios) {
                    Map<String, Object> mapuser = (Map<String, Object>) data;
                    Map.Entry<String, Object> filtro = mapuser.entrySet().stream().filter(d -> d.getKey().equalsIgnoreCase("mailAddress")).collect(Collectors.toList()).get(0);
                    if (filtro.getValue().toString().equalsIgnoreCase(email)) {
                        response = email;
                        break;
                    }
                }
            } catch (Exception ex) {
                throw ex;
            }
        } else {

        }
        return response;
    }

    public void createFileUsers() throws IOException {
        Path currentdirectory = Paths.get("").toAbsolutePath();
        Path completefile = Paths.get(currentdirectory.toString(), "src/DevOpsFiles/users.json");
        if (Files.exists(currentdirectory)) {
            try {
                Files.delete(completefile);
                Files.createFile(completefile);

            } catch (Exception ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        } else {
            try {
                Files.createDirectory(currentdirectory);
            } catch (Exception ex) {
                throw ex;
            }

        }
    }

//    public List<UsuarioDevops> getUsuariosDevops() {
//        try {
//            StringBuilder sb = new StringBuilder();
//            Mono<Object[]> response = service.getClientWebAutenticado()
//                    .get()
//                    .uri(api_all_users)
//                    .accept(MediaType.APPLICATION_JSON)
//                    .retrieve()
//                    .bodyToMono(Object[].class);
//
//            Object[] data =  response.block();
//            if(data != null){
//
//            }
//
//            return new ArrayList<UsuarioDevops>();
//        } catch (Exception e) {
//            System.out.printf("Error la conexion con la api error : %s ", e.getMessage());
//            return null;
//        }
//
//    }
}
