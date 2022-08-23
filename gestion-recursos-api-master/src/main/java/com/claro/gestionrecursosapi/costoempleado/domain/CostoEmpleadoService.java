package com.claro.gestionrecursosapi.costoempleado.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CostoEmpleadoService {

  @Autowired
  private Environment environment;

  public Integer traerVariableCalFactorPunto(String name) {
    return Integer.parseInt(environment.getProperty(name));
  }
}
