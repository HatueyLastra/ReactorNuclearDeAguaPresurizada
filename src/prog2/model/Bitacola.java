/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Hatuey
 */
public class Bitacola implements InBitacola, Serializable {
    private ArrayList<PaginaBitacola> pagsBitac;
    
    public Bitacola(){
        this.pagsBitac = new ArrayList<>();
    }

    @Override
    public void afegeixPagina(PaginaBitacola p) {
        pagsBitac.add(p);
    }

    @Override
    public List<PaginaIncidencies> getIncidencies() {
        Iterator<PaginaBitacola> iterator = pagsBitac.iterator();
        List<PaginaIncidencies> llistaPagsIncidencies = new ArrayList<>();
        while(iterator.hasNext()){
            PaginaBitacola pagina = iterator.next();
            if(pagina instanceof PaginaIncidencies){
                PaginaIncidencies paginaIncidencies = (PaginaIncidencies) pagina;
                llistaPagsIncidencies.add(paginaIncidencies);
            }
        }
        return llistaPagsIncidencies;
    }
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    for (PaginaBitacola pagina : pagsBitac) {
        if (pagina != null) {
            sb.append(pagina.toString());
        }
    }
    return sb.toString();
}
}
