/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import adaptador.Adaptador;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author dortiz
 */
public class CentralUB {
    
    enum MainMenuOption {
        GESTIO_BARRES,
        GESTIO_REACTOR,
        GESTIO_REFRIGERACIO,
        MOSTRAR_ESTAT,
        MOSTRAR_BITACOLA,
        MOSTRAR_INCIDENCIES,
        FINALITZAR_DIA,
        GUARDAR_DADES,
        CARREGAR_DADES,
        SORTIR
    }
    
    enum SubMenuBarres{
        OBTENIR_BARRES,
        ESTABLIR_BARRES,
        SORTIR_MENU
    }
    
    enum SubMenuReactor{
        ACTIVA_REACTOR,
        DESACTIVA_REACTOR,
        MOSTRAR_REACTOR,
        SORTIR_MENU
}
    
    enum SubMenuRefrigeracio{
        ACTIVA_BOMBA,
        DESACTIVA_BOMBA,
        MOSTRAR_BOMBES,
        SORTIR_MENU
    }
    public final static float DEMANDA_MAX = 1600;
    public final static float DEMANDA_MIN = 200;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 600;
    public final static long VAR_NORM_SEED = 123;
    
    private Adaptador adaptador;
    private Scanner sc;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;
    
    /* Constructor*/
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        adaptador = new Adaptador();
        sc = new Scanner(System.in);
        
        // Afegir codi adicional si fos necessari:

    }
    
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
        
                // Definimos las opciones del menú
        MainMenuOption[] options = MainMenuOption.values();
        // Creamos el menú
        Menu<MainMenuOption> menu = new Menu<>("Menú Principal", options);

        boolean exit = false;
        while (!exit) {
            menu.mostrarMenu();
            MainMenuOption selectedOption = menu.getOpcio(sc);
            switch (selectedOption) {
                case GESTIO_BARRES:
                    float percentatgeBarres;
                    SubMenuBarres[] opcioBarres = SubMenuBarres.values();
                    Menu<SubMenuBarres> menuBarres = new Menu<>("SubMenu Barres", opcioBarres);
                    
                    boolean exitBarres = false;
                    while (!exitBarres) {
                        menuBarres.mostrarMenu();
                        SubMenuBarres selOpcioBarres = menuBarres.getOpcio(sc);
                        switch (selOpcioBarres){
                            case OBTENIR_BARRES:
                            System.out.println(adaptador.getInsercioBarres());
                                break;
                            case ESTABLIR_BARRES:
                            System.out.println("Estableix un percentatge d'inserció de les barres:\n");
                            percentatgeBarres = sc.nextInt();
                            sc.nextLine();
                            try{
                                adaptador.setInsercioBarres(percentatgeBarres);
                                System.out.println("Barres insertades correctament\n");
                            }
                            catch(CentralUBException e) {
                        System.out.println("Error en establir barres: " + e.getMessage());
                            }
                                break;
                            case SORTIR_MENU:
                                exitBarres = true;
                                break;
                        }
                    }
                    break;
                case GESTIO_REACTOR:
                    
                    SubMenuReactor[] opcioReactor = SubMenuReactor.values();
                    Menu<SubMenuReactor> menuReactor = new Menu<>("SubMenu Reactor", opcioReactor);
                    
                    boolean exitReactor = false;
                    while (!exitReactor) {
                        menuReactor.mostrarMenu();
                        SubMenuReactor selOpcioReactor = menuReactor.getOpcio(sc);
                        switch (selOpcioReactor){
                            case ACTIVA_REACTOR:
                                try{
                                   adaptador.activaReactor();
                                   System.out.println("El reactor ha sigut activat\n");
                                }
                                catch(CentralUBException e){
                                System.out.println("Error al activar el reactor: " + e.getMessage());
                                }
                                break;
                            case DESACTIVA_REACTOR:
                            adaptador.desactivaReactor();
                            System.out.println("El reactor ha sigut desactivat\n");
                                break;
                                
                            case MOSTRAR_REACTOR:
                            System.out.println(adaptador.mostraEstatReactor());
                            break;
                            case SORTIR_MENU:
                                exitReactor = true;
                                break;
                        }
                    }
                    break;
                case GESTIO_REFRIGERACIO:
                    
                    int id;
                    SubMenuRefrigeracio[] opcioRefrigeracio = SubMenuRefrigeracio.values();
                    Menu<SubMenuRefrigeracio> menuRefrigeracio = new Menu<>("SubMenu Refrigeracio", opcioRefrigeracio);
                    
                    boolean exitRefrigeracio = false;
                    while (!exitRefrigeracio) {
                        menuRefrigeracio.mostrarMenu();
                        SubMenuRefrigeracio selOpcioRefrigeracio = menuRefrigeracio.getOpcio(sc);
                        switch (selOpcioRefrigeracio){
                            case ACTIVA_BOMBA:
                                try{
                            System.out.println("Escriu el id de la bomba que vols activar\n");
                            id = sc.nextInt();
                            sc.nextLine();
                            adaptador.activaBomba(id);
                            System.out.println("La bomba ha sigut activada\n");
                                }
                                catch(CentralUBException e){
                                System.out.println("Error al activar la bomba: " + e.getMessage());
                                }
                                break;
                            case DESACTIVA_BOMBA:
                            System.out.println("Escriu el id de la bomba que vols desactivar\n");
                            id = sc.nextInt();
                            sc.nextLine();
                            adaptador.desactivaBomba(id);
                            System.out.println("La bomba ha sigut desactivada\n");
                                break;
                                
                            case MOSTRAR_BOMBES:
                            System.out.println(adaptador.mostraEstatSistemaRefrigeracio());
                            break;
                            
                            case SORTIR_MENU:
                                exitRefrigeracio = true;
                                break;
                        }
                    }
                    break;
                case MOSTRAR_ESTAT:
                    System.out.println(adaptador.mostrarEstatCentral(demandaPotencia));
                    break;
                case MOSTRAR_BITACOLA:
                    System.out.println(adaptador.mostrarBitacola());
                    break;
                case MOSTRAR_INCIDENCIES:
                    System.out.println(adaptador.mostrarIncidencies());
                    break;
                case FINALITZAR_DIA:
                    finalitzaDia();
                    break;
                
                case GUARDAR_DADES:
                    try {
                        System.out.print("Introdueix la ruta on vols desar les dades: ");
                        String rutaGuardat = sc.nextLine();
                        adaptador.guardaDades(rutaGuardat);
                        
                    } catch (CentralUBException e) {
                        System.out.println("Error en desar les dades: " + e.getMessage());
                    }
                    break;
                   
                case CARREGAR_DADES:
                    try {
                        System.out.print("Introdueix la ruta des d'on vols carregar les dades: ");
                        String rutaCarrega = sc.nextLine();
                        Adaptador dadesCarregades = Adaptador.carregaDades(rutaCarrega);
                        adaptador = dadesCarregades;
                    } catch (CentralUBException e) {
                        System.out.println("Error en carregar les dades: " + e.getMessage());
                    }
                    break;
                    
                    
                    
                    
                    
                case SORTIR:
                    exit = true;
                    System.out.println("Sortint...");
                    break;
            }
        }
        
    }
    
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }
    
    //Modificada para mejor interacción con la Interfaz. 
    protected String finalitzaDia() {
    // Finalizar el día y obtener la información de la central
    String info = adaptador.finalitzaDia(demandaPotencia);
    info += "\nDia finalizado!\n";
    
    // Generar y obtener la nueva demanda de potencia
    demandaPotencia = generaDemandaPotencia();
    info += "La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats";
    
    return info;
}
    
    // Retorna el Adaptador. NUEVO
    public Adaptador getAdaptador(){
        return adaptador; 
    }
    
    // Retorna la DemandaDePotencia. NUEVO
    public float getDemandaPotencia() {
        return demandaPotencia;
    }
}
