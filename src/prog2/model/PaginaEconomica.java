/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

/**
 *
 * @author Hatuey
 */
public class PaginaEconomica extends PaginaBitacola {
    
    private float beneficis;
    private float exces;
    private float costosOperatius;
    private float guanysAcumulats;
    
    public PaginaEconomica(int dia,float beneficis,float exces,float costosOperatius,float guanysAcumulats){
        super(dia);
        this.beneficis = beneficis;
        this.exces = exces;
        this.costosOperatius = costosOperatius;
        this.guanysAcumulats = guanysAcumulats;
        
    }
    
    public String toString(){
        return "# Pàgina Econòmica \n" +
                "- Dia: " + dia + "\n" +
                "- Beneficis: " + beneficis + " Unitats Econòmiques\n" +
                "- Penalització Excés Producció: " + exces + " Unitats Econòmiques\n" +
                "- Cost Operatiu: " + costosOperatius + " Unitats Econòmiques\n" +
                "- Guanys acumulats: " + guanysAcumulats + " Unitats Econòmiques\n";
        
                
    }
    
}
