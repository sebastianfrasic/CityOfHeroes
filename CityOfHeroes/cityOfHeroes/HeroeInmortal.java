package cityOfHeroes;


/**
 * El heroe Inmortal jamás muere, es decir, su vida nunca cambiará a pesar de que se estrelle con los edificios.
 * Es rojo por defecto
 */
public class HeroeInmortal extends Heroe{

    /**
     * Método constructor de la clase Heroe
     */
    public HeroeInmortal(String color, int edificioAparicion, int fuerza){
        super(color,edificioAparicion,fuerza);
    }

    public String pideColor(){
        return "red";
    } 
    
    public void setFuerza(int dureza){
        fuerza = fuerza;
    }   
}
