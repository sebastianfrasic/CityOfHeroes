
   public class Heroe extends Circle
    {
        
        private String color;
        private int edificioAparicion;
        private int fuerza;
        
     
        public Heroe(String color, int edificioAparicion, int fuerza){
            this.color = color;
            this.edificioAparicion = edificioAparicion;
            this.fuerza = fuerza;
            super.changeColor(this.color);
        }
                            
        public String getColor(){
            return color;
        }
                            
        public void setColor (String color){
            this.color = color;
            super.changeColor(this.color);
        }
        
              
        public int getEdificioAparicion(){
            return edificioAparicion;
        }
                    
        public void setEdificioAparicion (int edificioAparicion){
            this.edificioAparicion = edificioAparicion;
        }
                     
        public int getFuerza(){
            return fuerza;
        }
        
              
        public void makeVisible(){
            super.makeVisible();
        }
        
        public void makeInvisible(){
            super.makeInvisible();
        }
        
        public void barraFuerza(){
        }
                    
        
}
