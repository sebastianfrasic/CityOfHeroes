package cityOfHeroes;

/**
 * Si golpean al Edificio Radical, desaparece
 */
public class EdificioRadical extends Edificio{
    /**
    * Método constructor de la clase Edificio Radical
    */
    public EdificioRadical(int x, int ancho, int largo, int dureza, int largoCiudad){
        super(x,ancho,largo,0,largoCiudad);
    }
    
    public String pideColor(){
        return "red";
    }
    
    public boolean tocaEdificio(int x, int y, int largoCiudad){
        if ((x+15 == getX() || x == (getX() + getAncho())) && y+15 > getY()){
            cortaEdificio(largoCiudad,y);
        }
        return false;
    }
    
    public void cortaEdificio(int largoCiudad,int y){        
        super.valido = false;
        if (heroeEncima != null){
            heroeEncima.dieHero();
        }
    };
    
    public boolean tocaEdificioSafe(int x,int y,int largoCiudad){
        return false;
    };
}
