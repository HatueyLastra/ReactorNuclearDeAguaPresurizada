/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptador;

import java.io.Serializable;
import prog2.vista.CentralUBException;
import prog2.model.*;
import java.io.*;
import java.util.List;

/**
 *
 * @author Hatuey
 */

public class Adaptador implements Serializable {
    
    Dades dades;
    public Adaptador(){
        dades = new Dades();
    }
    
    
    public float getInsercioBarres() {
        return dades.getInsercioBarres();
    }
    
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        dades.setInsercioBarres(insercioBarres);
    }
    
    public void activaReactor() throws CentralUBException {
        dades.activaReactor();
    }
    
    public void desactivaReactor() {
        dades.desactivaReactor();
    }
    
    public String mostraEstatReactor() {
        return dades.mostraReactor().toString();
    }
    
    public void activaBomba(int id) throws CentralUBException {
        dades.activaBomba(id);
    }
    
    public void desactivaBomba(int id) {
        dades.desactivaBomba(id);
    }
    
    public String mostraEstatSistemaRefrigeracio() {
        return dades.mostraSistemaRefrigeracio().toString();

    }
    
    public String mostrarEstatCentral(float demandaPotencial) {
        return dades.mostraEstat(demandaPotencial).toString();
    }
    
    public String mostrarBitacola() {
        return dades.mostraBitacola().toString();
    }
    
    public String mostrarIncidencies() {
        String incidencies = "";
        List<PaginaIncidencies> lista = dades.mostraIncidencies();
        for(PaginaIncidencies current : lista){
            incidencies += current + "\n";
        }
        return incidencies;
    }

    public String finalitzaDia(float demandaPotencia) {
        String finalitza = mostrarEstatCentral(demandaPotencia); 
        dades.finalitzaDia(demandaPotencia);
        return finalitza; 
    }
    
    
public void guardaDades(String camiDesti) throws CentralUBException {
    try {
        File fitxer = new File(camiDesti);
        FileOutputStream fout = new FileOutputStream(fitxer);
        ObjectOutputStream oos = new ObjectOutputStream(fout);

        oos.writeObject(dades); // Guarda el objeto dades, no this
        oos.close();
        fout.close();
    } catch (IOException e) {
        throw new CentralUBException("Error en guardar les dades: " + e.getMessage());
    }
}
    
public static Adaptador carregaDades(String camiOrigen) throws CentralUBException {
    try {
        File fitxer = new File(camiOrigen);
        FileInputStream fin = new FileInputStream(fitxer);
        ObjectInputStream ois = new ObjectInputStream(fin);
        
        Dades dades = (Dades) ois.readObject();
        ois.close();
        fin.close();
        
        Adaptador adaptador = new Adaptador(); // Crear una instancia de Adaptador
        adaptador.dades = dades; // Asignar los datos cargados a su atributo dades
        
        return adaptador;
    } catch (IOException | ClassNotFoundException e) {
        throw new CentralUBException("Error en carregar les dades: " + e.getMessage());
    }
}
// Funciones NUEVAS para usar en AppCentralUB.  
    public int obtenerNumeroDia() {
        return dades.getDia();
    }

    public float obtenerGananciasAcumuladas() {
        return dades.getGuanysAcumulats();
    }
    
    public boolean isBombaActiva(int index) {
            return dades.getSistemaRefrigeracio().isBombaActiva(index); 
    }
}

