package cityOfHeroes;
import java.util.Random;

/**
 * El edificio cambia su color cuando un héroe cae en él
 */
public class EdificioColorido extends Edificio{
    protected String color = null;
    /**
    * Método constructor de la clase Edificio Colorido
    */
    public EdificioColorido(int x, int ancho, int largo, int dureza, int largoCiudad){
        super(x,ancho,largo,dureza,largoCiudad);
    }
    public String pideColor(){
        color = (color == null)?"gray":color;
        return color;
    }
    
    public boolean tocaEdificio(int x, int y, int largoCiudad){
        boolean tocado = false;
        if ((x+15 == getX() || x == (getX() + getAncho())) && y+15 > getY()){//si lo toca
            Random rnd = new Random();
            String colorN = colors[rnd.nextInt(colors.length)];
            while (colorN.equals(color)){//busca un color diferente 
                colorN = colors[rnd.nextInt(colors.length)];
            }
            color = colorN;
            marco.changeColor(color);
            tocado = true;
        }
        return tocado;
    }
} 

