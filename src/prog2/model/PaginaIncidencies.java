/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Hatuey
 */
public class PaginaIncidencies extends PaginaBitacola {
    
    private ArrayList<String> llistaIncidencies;
    
    public PaginaIncidencies(int dia){
        super(dia);
        this.llistaIncidencies = new ArrayList<>();
        
        
    }
    
    public void afegeixIncidencia(String descIncidencia){
        llistaIncidencies.add(descIncidencia);
    }
    
    public String toString(){
        String text = "# Pàgina incidències \n" + "-Dia: " + dia + "\n";
        Iterator<String> iterator = llistaIncidencies.iterator();
        while(iterator.hasNext()){
            String incidencia = iterator.next();
            text += "- Descripció Incidència: " + incidencia + "\n";
        }
        return text;
    }

}
