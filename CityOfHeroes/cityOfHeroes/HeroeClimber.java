package cityOfHeroes;


/**
 * El heroe Climber no rompe los edificios, los escala.
 * Es gris por defecto
 */
public class HeroeClimber extends Heroe{

    /**
     * Método constructor de la clase Heroe
     */
    public HeroeClimber(String color, int edificioAparicion, int fuerza){
        super(color,edificioAparicion,fuerza);
    }

    public String pideColor(){
        return "gray";
    } 
    
    public boolean aSalvo(int y, int x, Edificio e,int largoCiudad){
        boolean legal = true;
        if (y+15 >= e.getY() && ((x+15 > e.getX() && x+15 < e.getX()+e.getAncho())||(x > e.getX() && x < e.getX()+e.getAncho()))){ //Cuando el heroe llega a salvo
            setEdificioActual(e.getPosicion()+1);
            legal = false; 
        }else if (e.tocaEdificio(x,y,largoCiudad)){
            escalarEdificio(e,x,y);
            setEdificioActual(e.getPosicion()+1);
        }
        return legal;
    } 
    
    public void escalarEdificio(Edificio e,int x,int y){
        while(y-15 >= e.getY()){
            y--;
            setPosition(x,y);
        }
    }
}
