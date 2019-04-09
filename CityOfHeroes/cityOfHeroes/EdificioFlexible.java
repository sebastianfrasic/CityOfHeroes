package cityOfHeroes;


/**
 * El edificio flexible cambia su forma si así lo necesitan  los héroes y otros edificios
 */
public class EdificioFlexible extends Edificio{
    /**
    * Método constructor de la clase Edificio Flexible
    */
    public EdificioFlexible(int x, int ancho, int largo, int dureza, int largoCiudad){
        super(x,ancho,largo,dureza,largoCiudad);
    }
    public String pideColor(){
        return "magenta";
    }
    public boolean molestar(int xN,int anchoN){
        if( xN+anchoN >super.getX() && xN+anchoN < super.getX()+super.getAncho() && xN <= super.getX()){
            super.setAncho((super.getX()+super.getAncho())-(xN+anchoN));
        }else if (xN >= super.getX() && xN <= super.getX()+super.getAncho()&& xN+anchoN > super.getX()+super.getAncho()){
            super.setAncho(xN-super.getX());
        }
        return false;
    }
    
    public boolean tocaEdificio(int x, int y, int largoCiudad){
        if ((x+15 == getX() || x == (getX() + getAncho())) && y+15 > getY()){
            cortaEdificio(largoCiudad,y);
        }
        return false;
    };      
    
    public boolean tocaEdificioSafe(int x,int y,int largoCiudad){
        return false;
    };
} 

