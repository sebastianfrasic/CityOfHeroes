

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;


/**
 * Juan Sebastian Frásica y Juan Sebastián Gómez
 */
public class CityOfHeroes
{
    // instance variables - replace the example below with your own
    private ArrayList<Heroe> heroes = new ArrayList<Heroe>();
    private ArrayList<Edificio> edificios = new ArrayList<Edificio>();
    private ArrayList<Heroe> muertos = new ArrayList<Heroe>();
    private ArrayList<Object> posiciones = new ArrayList<Object>();
    private int ancho;
    private int largo;
    
    
    public CityOfHeroes(int ancho, int largo){
        this.ancho = ancho;
        this.largo = largo;
        Canvas canvas = Canvas.getCanvas(ancho,largo);
       
    } 
    
    private void sortEdificio(){
        int[] posicionesOrdenar = new int[edificios.size()];
        ArrayList<Edificio> buildings = new ArrayList<Edificio>();
        for(int i = 0; i < edificios.size(); i++){
            posicionesOrdenar[i] = edificios.get(i).getX();
        }
        Arrays.sort(posicionesOrdenar);
        for(int i= 0; i< edificios.size(); i++){
            for(int j=0; j<edificios.size(); j++){
                if (posicionesOrdenar[i]==edificios.get(j).getX()){
                    buildings.add(edificios.get(j));
                }
            }
        }
        edificios = buildings;
        
    }
    
    public void addEdificio(int x, int ancho, int largo, int dureza){
       edificios.add(new Edificio(x,ancho,largo,dureza));
       edificios.get(edificios.size()-1).setyPosition(this.largo-largo);
       for(int i=0; i<edificios.size()-1;i++){
           if (edificios.get(i).getAncho()+edificios.get(i).getX() == x && edificios.get(i).getColor()=="black"){
               edificios.get(edificios.size()-1).changeColor("orange");
            }
       }
       edificios.get(edificios.size()-1).makeVisible();
       sortEdificio();
       
       
       /*
        * Este ciclo hace que si se añaden dos edificios adyacentes, estos cambien de color entre negro y naranja.
        */
       
        

    }

    public void addHeroe(String color, int edificioAparicion, int fuerza){
        if (heroes.size() != 0){
            int a = 0;
            for(int i=0; i<heroes.size(); i++){
                if (heroes.get(i).getColor() != color){
                    a+=1;
                }
            }
            if (a==heroes.size()){
               heroes.add(new Heroe(color,edificioAparicion,fuerza));
               Edificio edificio = edificios.get(edificioAparicion-1);
               int xheroe= edificio.getX() + (edificio.getAncho()/2) - 7;
               int yheroe = largo - edificio.getLargo() - 15;
               heroes.get(heroes.size()-1).setX(xheroe);
               heroes.get(heroes.size()-1).setY(yheroe);
               heroes.get(heroes.size()-1).makeVisible();
            } else {
                System.out.println("No se pudo agregar");
            }
        } else if(heroes.size() == 0){
            heroes.add(new Heroe(color,edificioAparicion,fuerza));
            Edificio edificio = edificios.get(edificioAparicion-1);
            int xheroe= edificio.getX() + (edificio.getAncho()/2) - 7;
            int yheroe = largo - edificio.getLargo() - 15;
            heroes.get(0).setX(xheroe);
            heroes.get(0).setY(yheroe);
            heroes.get(0).makeVisible();
            heroes.get(0).makeVisible();
        }
    }
    /* public String[] deads(){
    } */
    
    public void jump(String heroe, int velocidad, int angulo, boolean slow){
    }
    
    /* public boolean isDamaged(int posicion){
    } */
    
    /* public boolean isSaveJump(String heroe, int velocidad, int angulo){
    } */
    public void removeEdificio(int position){
    }
   
    public void removeHeroe(String color){
    }
    
    /* public int fuerza(String color){
    } */
    
    public void makeVisible(){
    }
    
    public void makeInvisible(){
    }
    
    /* public boolean ok(){
    } */
}
