
package cityOfHeroes;
import shapes.*;
import java.util.*;

/**
 * Clase Heroe
 * Representa un heroe de la ciudad CityOfHeroes
 * Un heroe se representa como un cuadrado, y su fuerza se puede ilustrar gráficamente
 */
public abstract class Heroe{
    protected String color;
    protected int edificioActual;
    protected int fuerza;
    protected int xPosition, yPosition;
    protected Rectangle cuerpo;
    protected Rectangle vida;
    protected Circle cabeza;
        
    /**
     * Método constructor de la clase Heroe
     */
    public Heroe(String color, int edificioAparicion, int fuerza){
        this.color = color;
        this.edificioActual = edificioAparicion;
        this.fuerza = fuerza;
        cuerpo = new Rectangle();
        cuerpo.changeColor(color);
        vida = new Rectangle();
        cabeza = new Circle();
        cabeza.changeColor(pideColor());
        setVida(fuerza);
    }
    
    /**
      * Retorna el color que va a usar dependiendo del tipo de heroe "black" sí es heroe normal, "red" sí es inmortal,
      * "magenta" sí es Paratrooper, "gray" sí es Climber y "blue" sí es Careful
      * @return el color del edificio
      */
    public abstract String pideColor();
    
    /**
     * Establece la posición del héroe
     */
    public void setPosition(int xEdificio,int ancho,int largo,int largoEdificio){
        cuerpo.setxPosition(xEdificio+(ancho/2)-15);
        cuerpo.setyPosition(largo-largoEdificio-15);
        vida.setxPosition((xEdificio+(ancho/2))-14);
        vida.setyPosition((largo-largoEdificio)-14);
        cabeza.setxPosition(xEdificio+(ancho/2)-15);
        cabeza.setyPosition(largo-largoEdificio-30);
    }
    
    /**
     * Establece la posición del héroe según sus coordenadas x,y
     */
    public void setPosition(int x,int y){
        cuerpo.setxPosition(x);
        cuerpo.setyPosition(y);
        vida.setxPosition(x+1);
        vida.setyPosition(y+1);
        cabeza.setxPosition(x);
        cabeza.setyPosition(y-15);
        cuerpo.makeVisible();
        vida.makeVisible();
        cabeza.makeVisible();
    }
    
    /**
      * Revisa si un heore es igual a uno existente o llega al mismo edificio.
      * @param colorN color del nuevo heroe
      * @param edificioN edificio de aparicion del nuevo heroe
      * @return si el nuevo heroe molesta.
      */
    public boolean molestar(String colorN,int edificioN){
        return (getColor().equals(colorN)||getEdificio()+1==edificioN); 
    }
    
    /**
     * Se encarga de revisar si el heroe llega a salvo del salto
     */
    public boolean aSalvo(int y, int x, Edificio e,int largoCiudad){
        boolean legal = true;
        if (y+15 >= e.getY() && ((x+15 > e.getX() && x+15 < e.getX()+e.getAncho())||(x > e.getX() && x < e.getX()+e.getAncho()))){ //Cuando el heroe llega a salvo
            setEdificioActual(e.getPosicion()+1);
            legal = false; 
        }else if (e.tocaEdificio(x,y,largoCiudad) && getFuerza() < e.getDureza()){ //Cuando el heroe muere contra el edificio
            dieHero();
            legal = false;
        }else if (e.tocaEdificio(x,y,largoCiudad) && getFuerza() >= e.getDureza()){//cuando el heroe corta el edificio
            e.cortaEdificio(largoCiudad,y);
            setFuerza(e.getDureza());
            setEdificioActual(e.getPosicion()+1);
            legal = false;
        }
        return legal;
    }
    
    /**
     * Se encarga de revisar si el heroe llega a salvo del salto
     */
    public boolean isSalvo(int y, int x, Edificio e,int largoCiudad){
        boolean legal = true;
        if (y+15 >= e.getY() && ((x+15 > e.getX() && x+15 < e.getX()+e.getAncho())||(x > e.getX() && x < e.getX()+e.getAncho()))){ //Cuando el heroe llega a salvo
            legal = false; 
        }
        return legal;
    }
    /**
     * Se encarga de decir si va a saltar o no dependiendo de la cuidad y el salto
     * @param ciudad ciudad actual
     * @param velocidad velocidad del salto
     * @param angulo angulo del salto
     * @return si el heroe va a saltar
     */
    public boolean saltar(CityOfHeroes ciudad,int velocidad,int angulo){
        return true;
    }
    
    /**
     * se encarga de ver si un heroe se va a salir de la cuidad
     * @param anchoCiudad el ancho de la ciudad
     * @param x Posicion x a evaluar para el heroe
     * @param y Posicion y a evaluar para el heroe
     * @param largoCiudad el largo de la ciudad
     * @param edificios Edificios de la ciudad
     */
    public boolean estrellado(int anchoCiudad,int x, int y,int largoCiudad, ArrayList<Edificio> edificios,CityOfHeroes ciudad){
        boolean estrellado = false;
        if(y<0 || x<0|| x+15>anchoCiudad||getFuerza()<=0){
            estrellado = true;
            dieHero();
        }
        return estrellado;
    }
    
    /**
     * @return Posicion en x del heroe
     */
    public int getX(){
        return cuerpo.getxPosition();
    }
    
    /**
     * @return Posicion en y del heroe
     */
    public int getY(){
        return cabeza.getyPosition();
    }
    
    /**
     * @return Posicion del edificio en el que está el heroe
     */
    public int getEdificio(){
        return edificioActual-1;
    } 
    
    /**
     * @return Fuerza del heroe
     */
    public int getFuerza(){
        return fuerza;
    }   
    
    /**
     * @return Color del heroe
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Establece la posición en x del heroe
     * @param x Posicion en x del heroe
     */
    public void setxPosition(int x){
        cuerpo.setxPosition(x);
    }
    
    /**
     * Establece la posición en y del heroe
     * @param y Posicion en y del heroe
     */
    public void setyPosition(int y){
        cuerpo.setyPosition(y);
    }
    
    /**
     * Establece la posición del edificio actual del heroe
     * @param nuevoEdificio Posicion del edificio actual del heroe
     */
    public void setEdificioActual (int nuevoEdificio){
        edificioActual = nuevoEdificio;
    }
    
    /**
     * Establece la fuerza del heroe
     * @param dureza Dureza del edificio que se le restará a la fuerza del heroe
     */
    public void setFuerza(int dureza){
        fuerza-=dureza;
        setVida(fuerza);
    }   
    
    /**
     * Establece la barra de vida del heroe
     * @param fuerza Fuerza del heroe
     */
    public void setVida(int fuerza){
        vida.changeSize((int)(13*(100-fuerza)/100),13);
    }
    
    /**
     * Cuando el heroe muere, se hace invisible y pierde toda su fuerza
     */
    public void dieHero(){
        makeInvisible();
        fuerza = 0;
        setVida(fuerza);
        edificioActual = -1;
    }
    
    /**
     * Hace visible al héroe
     */
    public void makeVisible(){
        cuerpo.makeVisible();
        vida.makeVisible();
        cabeza.makeVisible();
    }
    
    /**
     * Hace invisible al heroe
     */
    public void makeInvisible(){
        cuerpo.makeInvisible();
        vida.makeInvisible();
        cabeza.makeInvisible();
    }    
    
}