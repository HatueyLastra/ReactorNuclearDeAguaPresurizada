/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

/**
 *
 * @author Hatuey
 */
public class PaginaEstat extends PaginaBitacola {
    
    private float demandaPotencia;
    private float insercioBarres;
    private float outputReactor;
    private float outputRefrigeracio;
    private float outputVapor;
    private float outputTurbina;
    private float potenciaSatisfeta;
    
    public PaginaEstat(int dia, float demandaPotencia, float insercioBarres, float outputReactor, float outputRefrigeracio, float outputVapor, float outputTurbina, float potenciaSatisfeta){
        super(dia);
        this.demandaPotencia = demandaPotencia;
        this.insercioBarres = insercioBarres;
        this.outputReactor = outputReactor;
        this.outputRefrigeracio = outputRefrigeracio;
        this.outputVapor = outputVapor;
        this. outputTurbina = outputTurbina;
        this.potenciaSatisfeta = potenciaSatisfeta;
    }
    
    public String toString(){
                return "# Pàgina Estat \n" + 
                        "- Dia: " + dia + "\n" +
                        "- Demanda de potencia: " + demandaPotencia + "\n" +
                        "- Inserció Barres: " + insercioBarres + " %\n" +
                        "- Output Reactor: " + outputReactor + " Graus\n" +
                        "- Output Sistema de Refrigeració: " + outputRefrigeracio + " Graus\n" +
                        "- Output Generador de Vapor: " + outputVapor + " Graus\n" +
                        "- Output Turbina: " + outputTurbina + " Unitats de Potència\n" +
                        "- Demanda de Potència Satisfeta: " + potenciaSatisfeta + " %\n";           
    }
    
}
