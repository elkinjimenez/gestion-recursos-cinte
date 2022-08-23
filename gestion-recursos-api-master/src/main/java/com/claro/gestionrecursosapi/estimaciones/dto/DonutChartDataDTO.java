package com.claro.gestionrecursosapi.estimaciones.dto;

import java.math.BigDecimal;

public class DonutChartDataDTO {

    private String label;
    private Long value;


    public DonutChartDataDTO(String label, Long value) {
        this.label = label;
        this.value = value;
    }

    public DonutChartDataDTO(String label, BigDecimal value) {
        this.label = label;
        this.value = value.longValue();
    }

    public DonutChartDataDTO(String label, float value) {
        this.label = label;
        long costoHoras = (long) value;
        this.value = costoHoras;
    }

    public DonutChartDataDTO() {

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}