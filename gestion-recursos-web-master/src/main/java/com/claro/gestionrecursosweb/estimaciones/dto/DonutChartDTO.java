package com.claro.gestionrecursosweb.estimaciones.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;

public class DonutChartDTO {

    private String element;
    private Iterable<DonutChartDataDTO> data = new ArrayList<DonutChartDataDTO>();
    private String[] colors;


    public DonutChartDTO() {

    }

    public DonutChartDTO(String element, Iterable<DonutChartDataDTO> data, String[] colors) {
        this.element = element;
        this.data = data;
        this.colors = colors;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Iterable<DonutChartDataDTO> getData() {
        return data;
    }

    public void setData(Iterable<DonutChartDataDTO> data) {
        this.data = data;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }
}