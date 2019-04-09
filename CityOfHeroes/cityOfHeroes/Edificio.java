package cityOfHeroes;
import shapes.*;
import java.util.Random;
    /**
     * Clase Edificio
     * Representa un edificio de la ciudad
     */
    public abstract class Edificio{
        protected int dureza;
        protected int largoCiudad;
        protected int alturaActual;
        protected int posicion;
        protected Rectangle cuerpo;
        protected Rectangle marco;
        protected static String[] colors = {"blue","red","yellow","green","magenta","white","gray","orange","pink"};
        protected boolean valido; 
        protected Heroe heroeEncima = null;
        
        /**
         * Método constructor de la clase Edificio
         */
        public Edificio(int x, int ancho, int largo, int dureza, int largoCiudad){
            this.largoCiudad = largoCiudad;
            this.dureza = dureza;
            alturaActual = largo;
            cuerpo = new Rectangle();
            marco = new Rectangle();
            setPosition(largoCiudad,largo,ancho,x);
            valido = true;
            posicion = -1;
        }
        
        /**
         * Establece la posición del edificio
         * @param largoCiudad Altura de la ciudad
         * @param largo Altura del edificio
         * @param ancho Ancho del edificio
         * @param x Posición en la coordenada x del edificio
         */
        public void setPosition(int largoCiudad,int largo,int ancho,int x){
            cuerpo.changeSize(largo,ancho);
            marco.changeSize(largo-2,ancho-2);
            cuerpo.setyPosition(largoCiudad-largo);
            cuerpo.setxPosition(x);
            marco.setyPosition(largoCiudad-largo+1);
            marco.setxPosition(x+1);
            cuerpo.changeColor("black");
            String color = pideColor();
            marco.changeColor(color);
        }
        
        /**
         * Retorna el color que va a usar dependiendo del tipo de edificio "white" sí es edificio normal, "red" sí es radical,
         * "magenta" sí es flexible y "gray" sí es NUEVO
         * @return el color del edificio
         */
        public abstract String pideColor();
        
        /**
         * Revisa si un nuevo edificio se sobrepone.
         * @param xN x del nuevo edificio
         * @param anchoN ancho del nuevo edificio
         * @return si el nuevo edificio se sobrepone.
         */
        public boolean molestar(int xN,int anchoN){
            return((xN >= getX() && xN < (getX() + getAncho()))|| (xN + anchoN > getX() && xN + anchoN <= getX() + getAncho())||(xN <= getX() && xN + anchoN > getX() + getAncho()));
        }
        
        /**
         * Se encarga decir si un edificio es tocado en determinada posicion
         */
        public boolean tocaEdificio(int x,int y,int largoCiudad){
            return ((x+15 == getX() || x == (getX() + getAncho())) && y+15 > getY());
        }
        
        /**
         * Se encarga decir si un edificio es tocado en determinada posicion
         */
        public boolean tocaEdificioSafe(int x,int y,int largoCiudad){
            return ((x+15 == getX() || x == (getX() + getAncho())) && y+15 > getY());
        }
        
        /**
         * Se encarga de romper el edificio cuando un héroe se estrella con él, y cuando el héroe muere contra el edificio
         */
        public void cortaEdificio(int largoCiudad,int y){
            setPosition(largoCiudad - (y+15));
            setPosition(largoCiudad, (largoCiudad - (y+15)), getAncho(), getX());
        }
        
        /**
         * @return La posicion en x del edificio
         */
        public int getX(){
            return cuerpo.getxPosition();
        }
        /**
        * @return La posicion en y del edificio
        */   
        public int getY(){
            return cuerpo.getyPosition();
        }
        
        /**
         * @return El ancho del edificio
         */       
        public int getAncho(){
            return cuerpo.getAncho();
        }
        
        /**
         * @return El largo del edificio
         */
        public int getLargo(){
            return cuerpo.getLargo();
        }
        
        /**
         * @return La altura actual del edificio
         */
        public int getAlturaActual(){
            return alturaActual;
        }
        
        /**
         * @return Si el edificio es válido
         */
        public boolean getValido(){
            return valido;
        }
        
        /**
         * @return La dureza del edificio
         */
        public int getDureza(){
            return dureza;
        }
        
        /**
         * @return La posicion del edificio
         */
        public int getPosicion(){
            return posicion;
        }
        
        /**
         * @return el heroe en el edificio
         */
        public Heroe getHeroe(){
            return heroeEncima;
        }
        
        /**
         * Establece la nueva altura del edificio
         * @param y Posición en y (altura) del edificio
         */
        public void setPosition (int y){
            alturaActual = y;
        }
        
        /**
         * Establece la nueva posicion en x
         * @param xn la nueva posicion en x
         */
        public void setX(int xn){
            cuerpo.setxPosition(xn);
            marco.setxPosition(xn+1);
        }
        
        /**
         * Establece el nuevo ancho
         * @param anchoN el nuevo ancho
         */
        public void setAncho(int anchoN){
            cuerpo.changeSize(cuerpo.getLargo(),anchoN);
            marco.changeSize(marco.getLargo(),anchoN);
        }
        
        /**
         * Establece la altura actual del edificio
         * @param alturaActual Altura actual del edificio
         */
        public void setAlturaActual (int alturaActual){
            this.alturaActual = alturaActual;
        }
        
        /**
         * Establece la posicion actual del edificio
         * @param posActual Posicion actual del edificio
         */
        public void setPosicion (int posActual){
            posicion = posActual;
        }
        
        /**
         * Establece un heroe actual en el  edificio
         * @param hero Heroe actual en el  edificio
         */
        public void setHeroeEncima (Heroe hero){
            heroeEncima = hero;
        }
        
        /**
         * Hace que aparezca el edificio
         */
        public void makeVisible(){
            cuerpo.makeVisible();
            marco.makeVisible();
        }
        
        /**
         * Hace que desaparezca el edificio
         */
        public void makeInvisible(){
            cuerpo.makeInvisible();
            marco.makeInvisible();
        }
    }