package cityOfHeroes;


/**
 * El heroe Careful nunca daña un edificio, prefiere no saltar.
 * Es azul por defecto
 */
public class HeroeCareful extends Heroe{

    /**
     * Método constructor de la clase Heroe Careful
     */
    public HeroeCareful(String color, int edificioAparicion, int fuerza){
        super(color,edificioAparicion,fuerza);
    }

    public String pideColor(){
        return "blue";
    } 
    
    public boolean saltar(CityOfHeroes ciudad,int velocidad,int angulo){
        return ciudad.isSafeJump(getColor(),velocidad,angulo);
    }
}
