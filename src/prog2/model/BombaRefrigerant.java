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
public class BombaRefrigerant implements InBombaRefrigerant, Serializable {
    private VariableUniforme vu;
    private int id;
    private boolean Activat;
    private boolean ForaDeServei;
    
    public BombaRefrigerant(VariableUniforme vu, int id){
    this.id = id;
    this.vu = vu;
    this.Activat = false;
    this.ForaDeServei = false;
}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void activa() throws CentralUBException {
        if(ForaDeServei){
            throw new CentralUBException("No es pot activar una Bomba Refrigerant fora de servei");
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
    public boolean getActivat() {
        return Activat;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        int posibilitat = vu.seguentValor();
        if(posibilitat < 20){
            ForaDeServei = true;
            Activat = false;
            p.afegeixIncidencia("La bomba refrig. " + id + " està fora de servei");
        }
        
    }

    @Override
    public boolean getForaDeServei() {
        return ForaDeServei;
    }
    
    public String toString(){
        return "Id=" + getId() + ", Activat=" + getActivat() + ", Fora de servei=" + getForaDeServei() + "\n";
    }
    
}
