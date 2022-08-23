package com.claro.gestionrecursosapi.estimaciones.dto;

import java.util.ArrayList;
import java.util.Random;

public class DonutChartDTO {

    private String element;
    private Iterable<DonutChartDataDTO> data;
    private String[] colors;


    public DonutChartDTO() {

    }

    public DonutChartDTO(String element, Iterable<DonutChartDataDTO> data, String[] colors) {
        this.element = element;
        this.data = data;
        if (colors == null){
            String[] colores = new String[((ArrayList) data).size()];
            for ( int index = 0; index < colores.length; index++ ) {
                Random rand = new Random();

                int r = rand.nextInt(200 );
                int g = rand.nextInt(120 + index / 1 +index ) + 1;
                int b = rand.nextInt(20 + index ) + 54;
                String hex = String.format("#%02X%02X%02X", r, g, b);
                colores[index] = hex;
            }
            this.colors = colores;
        }else {
            this.colors = colors;
        }
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