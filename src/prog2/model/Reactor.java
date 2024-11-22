/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import prog2.vista.CentralUBException;

/**
 *
 * @author Hatuey
 */
public class Reactor implements InComponent, Serializable {
    private float temperatura;
    private boolean Activat;
    
    public Reactor(){
        this.temperatura = 30f;
        this.Activat = false;
    }
    
    public void setTemperatura(float t){
        temperatura = t;
    }

    public float getTemperatura(){
        return temperatura;
    }
    @Override
    public void activa() throws CentralUBException {
        if(temperatura > 1000){
            throw new CentralUBException("El reactor no es pot iniciar," +
                                         "s'ha sobrepasat la temperatura màxima");
        }
        else{
            Activat = true;
        }
    }
    @Override
    public void desactiva() {
        Activat = false;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
                if(temperatura > 1000){
                    Activat = false;
            p.afegeixIncidencia("El reactor es va desactivar per superar" +
                                "la temperatura màxima");
        }
    }

    @Override
    public float getCostOperatiu() {
        if(Activat) return 30f;
        else return 0f;
    }
    @Override
    public float calculaOutput(float input) {
        if(!Activat) return temperatura;
        else return temperatura + (100 - input) * 10;
        
    }
    
    @Override
    public String toString(){
        return "El reactor es troba " + (Activat ? "Activat" : "Desactivat") +
               " i la seva temperatura és de " + temperatura + " graus Celsius\n";
    
}
    
}
