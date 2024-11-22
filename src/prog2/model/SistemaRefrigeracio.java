/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import prog2.vista.CentralUBException;

/**
 *
 * @author Hatuey
 */
public class SistemaRefrigeracio implements InComponent, Serializable {
    private ArrayList<BombaRefrigerant> bombes;
    
    public SistemaRefrigeracio(){
        bombes = new ArrayList<>();
    }

    public void afegirBomba(BombaRefrigerant bomb){
        bombes.add(bomb);
    }
    @Override
    public void activa() throws CentralUBException {
        Iterator<BombaRefrigerant> iterator = bombes.iterator();
        while(iterator.hasNext()){
            BombaRefrigerant bomba = iterator.next();
            bomba.activa();
        }
    }
    
        public void activa(int id) throws CentralUBException {
        Iterator<BombaRefrigerant> iterator = bombes.iterator();
        while(iterator.hasNext()){
            BombaRefrigerant bomba = iterator.next();
            if(bomba.getId() == id) bomba.activa();
        }
    }

    @Override
    public void desactiva() {
                Iterator<BombaRefrigerant> iterator = bombes.iterator();
        while(iterator.hasNext()){
            BombaRefrigerant bomba = iterator.next();
            bomba.desactiva();
        }
    }
    
    public void desactiva(int id){
        Iterator<BombaRefrigerant> iterator = bombes.iterator();
        while(iterator.hasNext()){
            BombaRefrigerant bomba = iterator.next();
            if(bomba.getId() == id) bomba.desactiva();
        }
    }
    
    

    @Override
    public void revisa(PaginaIncidencies p) {
                Iterator<BombaRefrigerant> iterator = bombes.iterator();
        while(iterator.hasNext()){
            BombaRefrigerant bomba = iterator.next();
            bomba.revisa(p);
        }
    }

    @Override
    public float getCostOperatiu() {
        int cost = 0;
        Iterator<BombaRefrigerant> iterator = bombes.iterator();
        while(iterator.hasNext()){
            BombaRefrigerant bomba = iterator.next();
            if(bomba.getActivat()){
                cost += 125;
            }
        }
        return cost;
    }
    
    public int bombesActives(){   //Funció auxiliar per calcular l'output 
        int N = 0;
        Iterator<BombaRefrigerant> iterator = bombes.iterator();
        while(iterator.hasNext()){
            BombaRefrigerant bomba = iterator.next();
            if(bomba.getActivat()){
                N++;
            }
        }
        return N;
    }
    
    // NUEVO para poder usarlo en Adaptador. 
    public boolean isBombaActiva(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= bombes.size()) {
            throw new IndexOutOfBoundsException("Índice de bomba inválido");
        }
        return bombes.get(index).getActivat();
    }

    @Override
    public float calculaOutput(float input) {
        return Math.min(input, 250 * bombesActives());
    }
    
    public String toString(){
        String text = "";
        Iterator<BombaRefrigerant> iterator = bombes.iterator();
        while(iterator.hasNext()){
            BombaRefrigerant bomba = iterator.next();
            text += bomba.toString();
        }
        return text;
    }
    
}
