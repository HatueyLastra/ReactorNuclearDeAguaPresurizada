/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;


/**
 *
 * @author Hatuey
 */
public class GeneradorVapor implements InComponent, Serializable {
    
    boolean Activat;
    public GeneradorVapor(){
        this.Activat = false;
    }

    @Override
    public void activa(){
        Activat = true;
    }

    @Override
    public void desactiva() {
        Activat = false;
    }
    

    @Override
    public float getCostOperatiu() {
        if(Activat) return 25;
        else return 0;
    }

    @Override
    public float calculaOutput(float input) {
        if(!Activat) return 30;
        else return input * 0.8f;
    }

    @Override
    public void revisa(PaginaIncidencies p) {}
    
}
