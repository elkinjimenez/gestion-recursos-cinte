package com.claro.gestionrecursosapi.devops.entity;

import java.util.List;

public class ResponseUsuariosDev {

    private String count;
    private List<UsuarioDevops> value;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<UsuarioDevops> getDatausers() {
        return value;
    }

    public void setDatausers(List<UsuarioDevops> datausers) {
        this.value = datausers;
    }
}
