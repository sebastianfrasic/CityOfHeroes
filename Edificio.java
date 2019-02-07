
   public class Edificio extends Rectangle
    {
        private int x;
        private int ancho;
        private int largo;
        private int dureza;
       
        public Edificio(int x, int ancho, int largo, int dureza){
            this.x = x;
            setX(this.x);
            this.ancho = ancho;
            this.largo = largo;
            this.dureza = dureza;
            setDimension();
        }
                            
        public int getX(){
            return x;
        }
        
                                    
        public void setX (int x){
            this.x = x;
            super.setxPosition(this.x);
        }
        
        public void setY (int y){
            super.setyPosition(y);
        }
            
               
        public int getAncho(){
            return ancho;
        }
                    
        public void setAncho (int ancho){
            this.ancho = ancho;
        }
        
        public int getLargo(){
            return largo;
        }
                    
        public void setLargo (int largo){
            this.largo = largo;
        }
        
        private void setDimension(){
            super.changeSize(this.largo,this.ancho);
        }
         
        public int getDureza(){
            return dureza;
        }
        
        public void setDureza (int dureza){
            this.dureza = dureza;
        }
        
        public void makeVisible(){
            super.makeVisible();
        }
        
        public void makeInvisible(){
            super.makeInvisible();
        }
}