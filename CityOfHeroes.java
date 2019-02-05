

import java.util.ArrayList;
import java.awt.*;


/**
 * Juan Sebastian Frásica y Juan Sebastián Gómez
 */
public class CityOfHeroes
{
    // instance variables - replace the example below with your own
    private ArrayList<Heroe> heroes = new ArrayList<Heroe>();
    private ArrayList<Edificio> edificios = new ArrayList<Edificio>();
    private int ancho;
    private int largo;
    
    
    public CityOfHeroes(int ancho, int largo){
        this.ancho = ancho;
        this.largo = largo;
    } 
    
    public void addEdificio(int x, int ancho, int largo, int dureza){
       edificios.add(new Edificio(x,ancho,largo,dureza));
       if (edificios.size()!=0){
           for(int i=0; i<edificios.size();i++){
               edificios.get(i).makeVisible();
            }
        }
        
        
    }

    public void addHeroe(String color, int edificioAparicion, int fuerza){
        if (heroes.size() != 0){
            int a = 0;
            for(int i=0; i<heroes.size(); i++){
                if (heroes.get(i).getColor() != color){
                    a+=1;
                }
                heroes.get(i).makeVisible();
            }
            if (a==heroes.size()){
               heroes.add(new Heroe(color,edificioAparicion,fuerza));
            } else {
                System.out.println("No se pudo agregar");
            }
        } else if(heroes.size() == 0){
            heroes.add(new Heroe(color,edificioAparicion,fuerza));
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
