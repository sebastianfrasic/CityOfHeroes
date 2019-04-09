package cityOfHeroes;
import java.util.*;

/**
 * El heroe Paratrooper si se va a estrellar con los límites de la ciudad, desciende.
 * Es magenta por defecto
 */
public class HeroeParatrooper extends Heroe{

    /**
     * Método constructor de la clase Heroe
     */
    public HeroeParatrooper(String color, int edificioAparicion, int fuerza){
        super(color,edificioAparicion,fuerza);
    }

    public String pideColor(){
        return "magenta";
    } 
    
    public boolean estrellado(int anchoCiudad,int x, int y,int largoCiudad, ArrayList<Edificio> edificios,CityOfHeroes ciudad){
        boolean estrellado = false;
        if(y<0 || x<0|| x+15>anchoCiudad||getFuerza()<=0){
            estrellado = true;
            caer(x,y,largoCiudad,edificios,ciudad);
        }
        return estrellado;
    }
    public void caer(int x, int y,int largoCiudad, ArrayList<Edificio> edificios,CityOfHeroes ciudad){
        boolean aSalvo = false;
        while(y+15<largoCiudad&&!aSalvo){
            y++;
            setPosition(x,y);
            int i = 0;
            while (i <edificios.size()&&!aSalvo){
                Edificio  e  = edificios.get(i);
                if(e.getY() == (y+15) && ((x >= e.getX() && x <= (e.getX() + e.getAncho()))||(x+15 >= e.getX() && x+15 <= (e.getX() + e.getAncho())))){
                    setEdificioActual(i+1);
                    ciudad.eliminaHeroe(this);
                    aSalvo = true;
                }else{
                    i++;
                }
            }            
        }
        if(y+15==largoCiudad)setEdificioActual(-1);
    }
}
