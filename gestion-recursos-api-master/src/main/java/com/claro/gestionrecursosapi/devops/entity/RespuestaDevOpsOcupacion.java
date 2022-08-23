package com.claro.gestionrecursosapi.devops.entity;

import java.util.Objects;

public class RespuestaDevOpsOcupacion {
    private String op;
    private String path;
    private String value;
    private String from;


    public RespuestaDevOpsOcupacion() {
    }

    public RespuestaDevOpsOcupacion(String op, String path, String value, String from) {
        this.op = op;
        this.path = path;
        this.value = value;
        this.from = from;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RespuestaDevOpsOcupacion that = (RespuestaDevOpsOcupacion) o;
        return Objects.equals(op, that.op) && Objects.equals(path, that.path) && Objects.equals(value, that.value) && Objects.equals(from, that.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(op, path, value, from);
    }
}
