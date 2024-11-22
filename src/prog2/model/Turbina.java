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
public class Turbina implements InComponent, Serializable {
    private boolean Activat;
    
    public Turbina(){
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
    public void revisa(PaginaIncidencies p) {}

    @Override
    public float getCostOperatiu() {
        if(Activat) return 20f;
        else return 0f;
    }

    @Override
    public float calculaOutput(float input) {
        if(!Activat) return 0f;
        else if(input < 100) return 0f;
        else return input * 2f;
    }
    
}
