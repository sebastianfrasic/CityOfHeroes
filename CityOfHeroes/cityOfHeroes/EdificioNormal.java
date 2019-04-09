package cityOfHeroes;
import shapes.*;
import java.util.Random;

/**
* Clase Edificio
* Representa un edificio de la ciudad
*/
public class EdificioNormal extends Edificio{
    /**
    * Método constructor de la clase Edificio Normal
    */
    public EdificioNormal(int x, int ancho, int largo, int dureza, int largoCiudad){
        super(x,ancho,largo,dureza,largoCiudad);
    }
    public String pideColor(){
        return "white";
    }    
}
