package cityOfHeroes;
import shapes.*;

/**
 * Clase Heroe
 * Representa a un heroe normal, estos son de color negro por defecto.
 */
public class HeroeNormal extends Heroe{

    /**
     * Método constructor de la clase Heroe
     */
    public HeroeNormal(String color, int edificioAparicion, int fuerza){
        super(color,edificioAparicion,fuerza);
    }
    
    public String pideColor(){
        return "black";
    }   
}