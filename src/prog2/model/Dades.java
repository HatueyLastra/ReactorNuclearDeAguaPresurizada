/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import prog2.vista.CentralUBException;
import java.io.Serializable;

/**
 *
 * @author dortiz
 */
public class Dades implements InDades, Serializable{
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 200;

    // Afegir atributs:
    VariableUniforme variableUniforme;
    float insercioBarres;
    Reactor reactor;
    SistemaRefrigeracio sistemaRefrigeracio;
    GeneradorVapor generadorVapor;
    Turbina turbina;
    Bitacola bitacola;
    int dia;
    float guanysAcumulats;

    public Dades(){
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);
    }
    
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        float beneficis = Math.min(calculaPotencia(), demandaPotencia);
        float exces;
        if(calculaPotencia() > demandaPotencia) exces = 200;
        else exces = 0;
        float costosOperatius = reactor.getCostOperatiu() + sistemaRefrigeracio.getCostOperatiu() + generadorVapor.getCostOperatiu() + turbina.getCostOperatiu();
        guanysAcumulats = guanysAcumulats + beneficis - costosOperatius - exces;
        PaginaEconomica paginaEconomica = new PaginaEconomica(dia,beneficis,exces,costosOperatius,guanysAcumulats);
        return paginaEconomica;
    }
    
    /**
     * Actualitza l'estat de la central. El mètodo ha de establir la nova
     * temperatura del reactor i revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void actualitzaEstatCentral(PaginaIncidencies paginaIncidencies) {
       float temperatura = reactor.calculaOutput(insercioBarres) - sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(getInsercioBarres()));
       if(temperatura >= 30) reactor.setTemperatura(temperatura);
       else reactor.setTemperatura(30);
       reactor.revisa(paginaIncidencies);
       sistemaRefrigeracio.revisa(paginaIncidencies);
    }
    
    @Override
    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Genera pàgina d'estat
        PaginaEstat paginaEstat = mostraEstat(demandaPotencia);

        // Actualitza estat central i registra incidencies
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        actualitzaEstatCentral(paginaIncidencies);
        

        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }

    @Override
    public float getInsercioBarres() {
        return insercioBarres;
    }

    @Override
    public void setInsercioBarres(float ib) throws CentralUBException {
        if(ib < 0 || ib > 100) throw new CentralUBException("Les barres no es troben en un percentatge d'inserció correcte");
        else insercioBarres = ib;
    }

    @Override
    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }

    @Override
    public void desactivaReactor() {
        reactor.desactiva();
    }

    @Override
    public Reactor mostraReactor() {
        return reactor;
    }

    @Override
    public void activaBomba(int id) throws CentralUBException {
        sistemaRefrigeracio.activa(id);
    }
    @Override
    public void desactivaBomba(int id) {
        sistemaRefrigeracio.desactiva(id);
    }

    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    @Override
    public float calculaPotencia() {
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres))));
    }

    @Override
    public PaginaEstat mostraEstat(float demandaPotencia) {
        float outputReactor = reactor.calculaOutput(insercioBarres);
        float outputRefrigeracio = sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres));
        float outputVapor = generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres)));
        float outputTurbina = calculaPotencia();
        float potenciaSatisfeta = (demandaPotencia / outputTurbina) * 100;
        PaginaEstat paginaEstat = new PaginaEstat(dia,demandaPotencia,insercioBarres,outputReactor,outputRefrigeracio,outputVapor,outputTurbina,potenciaSatisfeta);
        return paginaEstat;
    }

    @Override
    public Bitacola mostraBitacola() {
        return bitacola;
    }

    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }
    
    public int getDia() {
        return dia;
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }
    
    public SistemaRefrigeracio getSistemaRefrigeracio(){
        return sistemaRefrigeracio; 
    }
}
