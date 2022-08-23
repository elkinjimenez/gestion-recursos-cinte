package com.claro.gestionrecursosweb.estimaciones.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize()
public class DonutChartDataDTO {

    private String label;
    private Long value;


    public DonutChartDataDTO(String label, Long value) {
        this.label = label;
        this.value = value;
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