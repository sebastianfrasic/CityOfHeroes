//Este es el proyecto final

package cityOfHeroes;
import shapes.*;
import java.util.*;
import javax.swing.JOptionPane;
    
/**
 * Representa la ciudad donde están ubicados los héroes y los edificios.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 */
public class CityOfHeroes{
    private ArrayList<Heroe> heroes = new ArrayList<Heroe>();
    private ArrayList<Edificio> edificios = new ArrayList<Edificio>();
    private int ancho;
    private int largo;
    private static final double gravedad = 9.81;
    private boolean ok;
    private boolean visible = false;
    private String ultimaAccion;
    private ArrayList<String> parametros;
    private int[][] consulta = new int[2][];
    
    /**
     * Método constructor de la clase 
     * @param ancho Ancho de la ciudad
     * @param largo Largo (altura) de la ciudad
     */
    public CityOfHeroes(int ancho, int largo){
        this.ancho = ancho;
        this.largo = largo;
        if(ancho+largo>1){
            Canvas z = Canvas.getCanvas(ancho,largo);
            z.setVisible(visible);
            ok = true;
        }
        else{
            alerta("Tamaño de la ciudad inválido");
        }
    } 
        
    /**
     * Ordena los edificios para que el héroe sea agregado al edificio con esa posición, contando de izquierda a derecha
     * Insertion sort
     */
    private void ordenEdificios(){
        Edificio cur;
        for(int k =0;k<edificios.size();k++){
            if(!edificios.get(k).getValido()){
                edificios.get(k).makeInvisible();
                edificios.remove(k);
            }
        }
        for(int k =0;k<edificios.size();k++){
            cur = edificios.get(k);
            int j = k;
            while(j>0 && edificios.get(j-1).getX()>cur.getX()){
                edificios.set(j,edificios.get(j-1));
                j-=1;
            }
            edificios.set(j,cur);
        }
        for(int k =0; k<edificios.size();k++){
            edificios.get(k).setPosicion(k);
        }
    }
    
    /**
     * Agrega un edificio en la ciudad
     * Para adicionar un edificio es necesario que la ciudad tenga libre el espacio que el edificio requiere.
     * La dureza del edificio debe ser entre 0 y 100
     * @param x La posición en x en el plano cartesiano donde se dibujará el edificio
     * @param ancho El ancho del edificio
     * @param largo El largo del edificio
     * @param dureza Dureza o fortaleza del edificio
     */
    public void addBuilding(int x, int ancho, int largo, int dureza){
       boolean legal = true;
       for (Edificio e : edificios){
           if (e.molestar(x,ancho)){
               legal = false;
               alerta("Ya existe un edificio en este campo");
            }
       }
       if(dureza <= 0 || dureza > 100){
           legal=false;
           alerta("Dureza no permitida.");
       }else if (x < 0 || x > this.ancho||x+ancho > this.ancho||largo > this.largo) {
           legal=false;
           alerta("El edificio no cabe en la ciudad.");
       }
       if (legal){
            edificios.add(new EdificioNormal(x,ancho,largo,dureza,this.largo));
            Edificio e = edificios.get(edificios.size()-1);
            if (visible){
                e.makeVisible();}
            ordenEdificios();
            ok = true;
            ultimaAccion = "addBuilding";
            int[]enviar = {e.getPosicion()};
            addParametros(enviar);
       }
    }
    
     /**
     * Agrega un edificio en la ciudad
     * Para adicionar un edificio es necesario que la ciudad tenga libre el espacio que el edificio requiere.
     * La dureza del edificio debe ser entre 0 y 100
     * @param x La posición en x en el plano cartesiano donde se dibujará el edificio
     * @param ancho El ancho del edificio
     * @param largo El largo del edificio
     * @param dureza Dureza o fortaleza del edificio
     * @param tipo Tipo de edificio (
     */
    public void addBuilding(String tipo,int x, int ancho, int largo, int dureza){
       tipo = tipo.toLowerCase();
       boolean legal = true;
       for (Edificio e : edificios){
           if (e.molestar(x,ancho)){
               legal = false;
               alerta("Ya existe un edificio en este campo");
            }
       }
       if(dureza <= 0 || dureza > 100){
           legal=false;
           alerta("Dureza no permitida.");
       }else if (x < 0 || x > this.ancho||x+ancho > this.ancho||largo > this.largo) {
           legal=false;
           alerta("El edificio no cabe en la ciudad.");
       }
       if (legal){
           añadirEdificio(tipo,x,ancho,largo,dureza);
           Edificio e = edificios.get(edificios.size()-1);
           if (visible)e.makeVisible();
           ordenEdificios();
           ok = true;
           ultimaAccion = "addBuilding";
           int[]enviar = {e.getPosicion()};
           addParametros(enviar);
       }
    }
    
    
    /**
     * Añade un edificio segun su tipo: normal, radical, flexible o colorido
     */
    private void añadirEdificio(String tipo,int x,int ancho,int largo,int dureza){
        switch(tipo){
            case "normal":
                edificios.add(new EdificioNormal(x,ancho,largo,dureza,this.largo));
                break;
            case "radical":
                edificios.add(new EdificioRadical(x,ancho,largo,dureza,this.largo));
                break;
            case "flexible":
                edificios.add(new EdificioFlexible(x,ancho,largo,dureza,this.largo));
                break;
            case "colorido":
                edificios.add(new EdificioColorido(x,ancho,largo,dureza,this.largo));
                break;
        }
    }
    
    
     /**
     * Agrega un héroe a la ciudad
     * Los heroes se identifican por su color. Solo puede haber un heroe de cada color.
     * Solo hay 10 colores permitidos, por lo que solo podrán haber 10 héroes
     * @param color Color que identifica al héroe
     * @param edificioAparicion Edificio donde aparecerá el heroe
     * @param fuerza Fuerza del héroe
     */
    public void addHeroe(String color, int edificioAparicion, int fuerza){
        boolean legal = true;
        int a = 0;
        for(Heroe h : heroes){
            if (h.molestar(color,edificioAparicion)){
                legal = false;
                alerta("Ya hay un heroe en ese edificio o ya existe un héroe de ese color.");
            }
        }
        if(fuerza <= 0 || fuerza > 100){
           legal = false;
           alerta("La fuerza del héroe debe ser entre 0 y 100.");
        }else if (edificioAparicion <= 0 || edificioAparicion > edificios.size()){
            legal = false;
            alerta("El edificio de aparición debe estar entre [1,"+edificios.size()+"]");
        }
        if (legal){
            heroes.add(new HeroeNormal(color,edificioAparicion,fuerza));
            Edificio edificio = edificios.get(edificioAparicion-1);
            Heroe hero = heroes.get(heroes.size()-1);
            hero.setPosition(edificio.getX(),edificio.getAncho(),largo,edificio.getAlturaActual());
            if (visible){
                hero.makeVisible();
            }
            ok = true;
            ultimaAccion = "addHeroe";
            int[]enviar = {};
            addParametros(hero.getColor(),enviar);
        }
    }
    
     /**
     * Agrega un héroe a la ciudad
     * Los heroes se identifican por su color. Solo puede haber un heroe de cada color.
     * Solo hay 10 colores permitidos, por lo que solo podrán haber 10 héroes
     * @param color Color que identifica al héroe
     * @param edificioAparicion Edificio donde aparecerá el heroe
     * @param fuerza Fuerza del héroe
     */
    public void addHeroe(String tipo, String color, int edificioAparicion, int fuerza){
        tipo = tipo.toLowerCase();
        boolean legal = true;
        int a = 0;
        for(Heroe h : heroes){
            if (h.molestar(color,edificioAparicion)){
                legal = false;
                alerta("Ya hay un heroe en ese edificio o ya existe un héroe de ese color.");
            }
        }
        if(fuerza <= 0 || fuerza > 100){
           legal = false;
           alerta("La fuerza del héroe debe ser entre 0 y 100.");
        }else if (edificioAparicion <= 0 || edificioAparicion > edificios.size()){
            legal = false;
            alerta("El edificio de aparición debe estar entre [1,"+edificios.size()+"]");
        }
        if (legal){
            añadirHeroe(color,edificioAparicion,fuerza,tipo);
            Edificio edificio = edificios.get(edificioAparicion-1);
            Heroe hero = heroes.get(heroes.size()-1);
            hero.setPosition(edificio.getX(),edificio.getAncho(),largo,edificio.getAlturaActual());
            if (visible){
                hero.makeVisible();
            }
            ok = true;
            ultimaAccion = "addHeroe";
            int[]enviar = {};
            addParametros(hero.getColor(),enviar);
        }
    }
    
    private void añadirHeroe(String color, int edificioAparicion, int fuerza,String tipo){
        switch(tipo){
            case "normal":
                heroes.add(new HeroeNormal(color,edificioAparicion,fuerza));
                break;
            case "careful":
                heroes.add(new HeroeCareful(color,edificioAparicion,fuerza));
                break;
            case "climber":
                heroes.add(new HeroeClimber(color,edificioAparicion,fuerza));
                break;
            case "paratrooper":
                heroes.add(new HeroeParatrooper(color,edificioAparicion,fuerza));
                break;
            case "inmortal":
                heroes.add(new HeroeInmortal(color,edificioAparicion,fuerza));
                break;
        }
    }
    
    /**
     * Retorna un ArrayList con los héroes fallecidos
     * @return Héroes muertos
     */
    public String[] deads(){
        String[] s = new String[0];
        for (Heroe h :heroes){
            if(h.getFuerza()<=0){
                String[] nueva = new String[s.length+1];
                for(int j = 0;j<s.length;j++){
                    nueva[j] = s[j]; 
                }
                nueva[nueva.length-1]=h.getColor();
                s=nueva;
            }
        }
        ok = true;
        return s;
    } 
    
 
    /**
     * Verifica si existe un héroe
     * @return true si el heroe existe
     */
    private boolean getHeroBuilding(String color){
        boolean check = false;
        for(int i=0;i < heroes.size();i++){
            if (heroes.get(i).getColor().equals(color)){
                check = true;
            }
        }
        return check;
    }
    
    /**
     * Busca a un heroe según su color
     * @param color Color del heroe
     * @return Heroe buscado
     */
    private Heroe searchHeroe(String color){
        Heroe heroe = null;
        for(int i=0; i<heroes.size(); i++){
            if (heroes.get(i).getColor().equals(color)){
                heroe = heroes.get(i);
            }
        }
        return heroe;
    }
    
        
    /**
     * Realiza el salto del heroe
     * @param heroe Heroe que va a saltar
     * @param velocidad Velocidad a la que saltará el héroe
     * @param angulo Angulo del salto
     * @param slow Si va a ir despacio
     */
    public void jump(String heroe, int velocidad, int angulo, boolean slow){
        boolean check = getHeroBuilding(heroe), legal = true, isOnAir=true;
        Heroe hero = searchHeroe(heroe);
        if(check && hero != null){
            if (!validarAngulo(angulo)){
                alerta("Angulo invalido");
            }else{
                ok = hero.saltar(this,velocidad,angulo);
                if (!ok){
                    alerta("El heroe no quiere saltar");
                }else{
                    int x0 = hero.getX(), y0 = hero.getY(),dx=(angulo>90)?-1:1, x=x0+dx, y=y0;
                    double vx = velocidad*Math.cos(Math.toRadians(angulo)),vy = velocidad*Math.sin(Math.toRadians(angulo)), t;
                    while (legal && isOnAir){
                        legal = !(hero.estrellado(ancho,x,y,largo,edificios,this));
                        if(legal){
                            t = (x-x0)/vx;
                            double dy = ((vy*t)-((0.5)*gravedad*Math.pow(t,2)));
                            y = (int)(y0-dy);
                            for(Edificio e: edificios){
                                isOnAir = (!aSalvo(y, x, e, hero))?false:isOnAir;
                                ordenEdificios();
                            }
                            mover( x, y, hero, slow);
                            x+=dx;
                        }
                    }
                    eliminaHeroe(hero);
                }
            }
        }else{
            alerta("Heroe Inválido");
        }
    }
    
    /**
     * Mueve al heroe al centro del edifico cuando este cae en la parte superior del edificio   
     */
    private void moverAlCentro(Heroe hero){
        if(hero.getEdificio()>-1){
            Edificio e = edificios.get(hero.getEdificio());
            e.setHeroeEncima(hero);
            hero.setPosition((e.getX()+(e.getAncho()/2)-15),(largo-e.getAlturaActual()-15));
        }
    }  
    
    /**
     * Se encarga de mover el heroe cuando este salta
     */
    private void mover(int x, int y, Heroe hero, boolean slow){
        if(visible&&!slow){
            if(x%3 ==0){
                hero.setPosition(x,y);
            }
        }else if(visible && slow){
            hero.setPosition(x,y);
        }
    }
    
    /**
     * Se encarga de revisar si el heroe llega a salvo del salto
     */
    private boolean aSalvo(int y, int x, Edificio e,Heroe hero){
        boolean legal = hero.aSalvo(y,x,e,largo);
        if(y+15>=largo){
            legal = false;
            hero.setEdificioActual(-1);
        }
        return legal;
    }
    
    protected void eliminaHeroe(Heroe hero){
        int i = 0;
        boolean legal=true;
        while(legal){
            if (i < heroes.size()){
                if (!heroes.get(i).getColor().equals(hero.getColor()) && heroes.get(i).getEdificio() == hero.getEdificio()){
                    legal = false;
                }else{i++;}
            }else{
                legal =false;
            }
        }
        if (i!=heroes.size()){
            if(heroes.get(i).getEdificio() == hero.getEdificio() && !heroes.get(i).getColor().equals(hero.getColor())){
                if(heroes.get(i).getFuerza() >= hero.getFuerza()){
                    hero.dieHero();
                    moverAlCentro(heroes.get(i));
                }else{
                    heroes.get(i).dieHero();
                    moverAlCentro(hero);
                }
            }else{
                moverAlCentro(hero);
            }
        }else{
            moverAlCentro(hero);
        }
    }
    
    /**
     * Se encarga de verificar los angulos posibles para realizar el salto, y permite que se salte si se ingresan ángulos negativos
     */
    private boolean validarAngulo(int angulo){
        boolean legal = true;
        while(angulo<0){
            angulo+=360;
        }
        while(angulo>360){
            angulo-=360;
        }
        if (angulo>180 && angulo<360){
            legal = false;
        }
        return legal;
    }
    
    /**
     * Realiza el salto del heroe hacia el edificio que se indique
     * @param heroe Heroe que va a saltar
     * @param building Edificio al que saltará el héroe
     */
    public void jump(String heroe, int building){
        int[] salto = jumpPlan(heroe,building);
        Heroe hero = searchHeroe(heroe);
        boolean legal = !(salto[0]==-1)||building-1 == hero.getEdificio();
        if(!legal){
            alerta("Salto inválido.");
        }else {
            ultimaAccion = "jumpEdificio";
            ok=true;
            int[] enviar = {hero.getEdificio()+1};
            addParametros(heroe,enviar);
            if(building-1 != hero.getEdificio()){
                jump(heroe,salto[0],salto[1],false);
            }
        }
    }
    
    /**
     * Realiza el "mejor" salto del heroe.
     * El mejor salto del héroe es que este salte al edificio más alto
     * @param heroe Heroe que va a saltar
     */
    public void jump(String heroe){
        int building = masAlto();
        int[] salto = jumpPlan(heroe,building+1);
        Heroe hero =searchHeroe(heroe);
        boolean legal = !(salto[0]==-1)||building == hero.getEdificio();
        if(!legal){
            alerta("Salto invalido.");
        }else{
            ok=true;
            ultimaAccion = "jumpMejor";
            int[] enviar = {hero.getEdificio()+1};
            addParametros(heroe,enviar);
            if (building != hero.getEdificio()){
                jump(heroe,salto[0],salto[1],false);
            }
        }
    }
    
    /**
     * Revisa cual es el edificio mas alto de la ciudad
     * @return Posición en el que esta el edificio mas alto de la ciudad
     */
    private int masAlto(){
        int masAlto = 0;
        for (int i = 0; i < edificios.size();i++){
            if(edificios.get(i).getAlturaActual()>masAlto){
                masAlto = i;
            }
        }
        return masAlto;
    }

/**
 * Planea un buen salto
 * @param heroe Heroe que saltará
 * @param edificio Edificio al que saltará el héroe
 * @return Velocidad y angulo para dar un buen salto
*/

public int[] jumpPlan(String heroe, int edificio){
    int angulo = 0, v = -1;
    boolean legal = false;
    Edificio e = (edificio<1 || edificio > edificios.size()) ? null:edificios.get(edificio-1);
    Heroe hero = searchHeroe(heroe);
    if(e != null && hero != null){
        int y = largo-e.getY(), minimo = (e.getX() > hero.getX())?1:91, maximo= (e.getX() > hero.getX())?89:179;
        int x = e.getX()+e.getAncho()/2;
        for(int angle = minimo; angle<=maximo; angle++){
            v = velDeAngulo(x,y,angle,hero.getX(),largo-hero.getY());
            legal = isSafeJump(heroe,v,angle,x);
            angulo = (legal)?angle:angulo;
            if(legal){
                break;
            }
        }
    }else{
            alerta("Edificio o heroe invalido.");
    }
    int[] res = new int[2];
    res[0] = (!legal)?-1:v;
    res[1] = (!legal)?-1:angulo;
    return res;
}

public boolean jumpPlan(String heroe,int edificio,int v){
    int angulo = 0, v1= -1;
    boolean legal = false;
    Edificio e = (edificio<1 || edificio > edificios.size()) ? null:edificios.get(edificio-1);
    Heroe hero = searchHeroe(heroe);
    if(e != null && hero != null){
        int y = largo-e.getY(), minimo = (e.getX() > hero.getX())?1:91, maximo= (e.getX() > hero.getX())?89:179,x = e.getX()+e.getAncho()/2;
            for(int angle = minimo; angle<=maximo; angle++){
                v1 = velDeAngulo(x,y,angle,hero.getX(),largo-hero.getY());
                legal = isSafeJump(heroe,v,angle,x)&&v1+1>=v;
                if(legal){break;}
            }
    }else{
        alerta("Edificio o heroe invalido.");
    }
    return legal;
}

/**
 * Retorna una velocidad a partir de un angulo y una coordenada en el plano
 * @param x coordenada en x
 * @param y coordenada en y
 * @param grados Angulo en grados
 * @return Velocidad positiva(Si es negativa significa que no es posible llegar a ese punto)
 */
private int velDeAngulo(int x, int y, int grados,int x0,int y0){
    int ans = -1;
    double theta = Math.toRadians(grados);
    ans=(int)Math.sqrt((gravedad*Math.pow((x-x0),2))/(2*Math.pow(Math.cos(theta),2)*((x-x0)*Math.tan(theta) - (y-y0))));
    return ans;
}


/**
 * Verifica si un edificio está dañado
 * @param posicion Posicion del edificio
 * @return true si el edificio está dañado, false de lo contrario
 */
public boolean isDamaged(int posicion){
        return(!(edificios.get(posicion-1).getLargo() == edificios.get(posicion-1).getAlturaActual()));
} 

/**
 * Verifica si un salto es seguro
 * @param heroe Heroe que va a saltar
 * @param velocidad Velocidad a la que saltará el héroe
 * @param angulo Angulo del salto
 * @return true si en el salto el heroe no se estrella, false de lo contrario
 */
public boolean isSafeJump(String heroe, int velocidad, int angulo){
    boolean check = getHeroBuilding(heroe);
    Heroe hero = searchHeroe(heroe);
    if(check && hero != null){
        if (!validarAngulo(angulo)){
            alerta("Angulo invalido");
        }else{
            ok = true;
            int x0 = hero.getX(), y0 = hero.getY(),dx=(angulo>90)?-1:1, x=x0+dx, y=y0;
            double vx = velocidad*Math.cos(Math.toRadians(angulo)),vy = velocidad*Math.sin(Math.toRadians(angulo)), t;
            boolean legal = true, isOnAir=true;  
            while (legal && isOnAir){
                if(y<0 || x<0|| x+15>ancho||hero.getFuerza()<=0){
                    legal = false;
                }else{
                    t = (x-x0)/vx;
                    double dy = ((vy*t)-((0.5)*gravedad*Math.pow(t,2)));
                    y = (int)(y0-dy);
                    for(Edificio e: edificios){
                        legal = (e.tocaEdificioSafe(x,y,largo))?false:legal;
                        isOnAir = (!isSave(y, x, e, hero))?false:isOnAir;
                    }
                    x+=dx;
                }
            }
            return(legal && !isOnAir);
        }
    }
    return false;
} 

private boolean isSave(int y, int x, Edificio e,Heroe hero){
    boolean legal = hero.isSalvo(y,x,e,largo);
    if(y+15>=largo){
        legal = false;
    }
    return legal;
}

private boolean isSave(int y, int x, Edificio e,Heroe hero,int xFinal){
    boolean legal = true;
    if (!hero.isSalvo(y,x,e,largo)){//cuando el heroe llega a salvo w.w
        if (xFinal>e.getX()&&xFinal<e.getX()+e.getAncho()){
            legal = false;   
        }
    }
    if(y+15>=largo){
        if (xFinal > x && xFinal < x+15){
            legal = false;   
        }
    }
    return legal;
}

/**
 * Verifica si un salto es seguro y llega a un xFinal determinado 
 * @param heroe Heroe que va a saltar
 * @param velocidad Velocidad a la que saltará el héroe
 * @param angulo Angulo del salto
 * @return true si en el salto el heroe no se estrella, false de lo contrario
 */
public boolean isSafeJump(String heroe, int velocidad, int angulo, int xFinal){
    boolean check = getHeroBuilding(heroe);
    Heroe hero = searchHeroe(heroe);
    if(check && hero != null){
        if (!validarAngulo(angulo)){
            alerta("Angulo invalido");
        }else{
            ok = true;
            int x0 = hero.getX(), y0 = hero.getY(),dx=(angulo>90)?-1:1, x=x0+dx, y=y0;
            double vx = velocidad*Math.cos(Math.toRadians(angulo)),vy = velocidad*Math.sin(Math.toRadians(angulo)), t;
            boolean legal = true, isOnAir=true;  
            while (legal && isOnAir){
                if(y<0 || x<0|| x+15>ancho||hero.getFuerza()<=0){
                    legal = false;
                }else{
                    t = (x-x0)/vx;
                    double dy = ((vy*t)-((0.5)*gravedad*Math.pow(t,2)));
                    y = (int)(y0-dy);
                    for(Edificio e: edificios){
                        legal = (e.tocaEdificioSafe(x,y,largo))?false:legal;
                        isOnAir = (!isSave(y, x, e, hero,xFinal))?false:isOnAir;
                    }
                    x+=dx;
                }
            }
            return(legal && !isOnAir);
        }
    }
    return false;
} 

/**
 * Verifica si un salto es seguro
 * @param heroe Heroe que va a saltar
 * @param edificio Edificio al que va a saltar el héroe
 * @return true si en el salto el heroe no se estrella, false de lo contrario
 */
public boolean isSafeJump(String heroe, int building){
    int[] salto = jumpPlan(heroe,building);
    boolean legal;
    if(salto[0] == -1){
        legal = false;
    }else{
        legal = true;
    }
    return legal;
}    

/**
 * Retorna un vector con la información de los edificios y heroes de la ciudad
 * @return Retorna un vector con dos vectores. El primero con la información de los edificios [x,ancho, alto, dureza] ordenado por posición en x. 
 * El segundo con la información de los heroes [edificio, fortaleza] ordenado por el número de edificio.
 */
public int[][][] city(){
    int[][][] consulta = new int[2][Math.max(edificios.size(),heroes.size())][4];
    int[][] resumenEdificios = new int[edificios.size()][4];
    int[][] resumenHeroes = new int[heroes.size()][2];
    for(int i=0;i<edificios.size();i++){
        Edificio e = edificios.get(i);
        int[] edificio = new int[4];
        edificio[0] = e.getX();
        edificio[1] = e.getAncho();
        edificio[2] = e.getLargo();
        edificio[3] = e.getDureza();
        resumenEdificios[i] = edificio;
    }
    for(int i=0;i<heroes.size();i++){
        Heroe h = heroes.get(i);
        int[] hero = new int[2];
        hero[0] = h.getEdificio()+1;
        hero[1] = h.getFuerza();
        resumenHeroes[i] = hero;
    }
    consulta[0] = resumenEdificios;
    consulta[1] = resumenHeroes;
    return consulta;
}
/**
 * Elimina un edificio según la posición que se indique 
 * La posicion inicia desde el edificio #1
 * @param posicion Posicion del edificio
 */
public void removeBuilding(int posicion){
    boolean legal = true;
    for (Heroe h : heroes){
        if(h.getEdificio()==posicion-1){
            legal = false;
        }
    }
    if (posicion>edificios.size()||posicion<1){
        alerta("El edificio no existe.");
    }else if(!legal){
        alerta("El edificio tiene un heroe.");
    }else{
        Edificio e =edificios.get(posicion-1);
        if (visible){
            e.makeInvisible();
        }
        ultimaAccion = "removeBuilding";
        int[] enviar =  {e.getX(),e.getAncho(),e.getAlturaActual(),e.getDureza()};
        addParametros(enviar);
        edificios.remove(posicion-1);
        ordenEdificios();
        ok = true;
    }
}

private void addParametros(int[] s){
    parametros = new ArrayList();
    for (int i = 0;i<s.length;i++){
       parametros.add(Integer.toString(s[i]));
    }
}

private void addParametros (String s,int[] h){
    parametros = new ArrayList();
    parametros.add(s);
    for (int i = 0;i<h.length;i++){
       parametros.add(Integer.toString(h[i]));
    }
}

/**
 * Elimina el héroe según su color
 * @param color Color del héroe
 */
public void removeHeroe(String color){
    int i=0;
    while(i<heroes.size() && heroes.get(i).getColor() != color){
        i++;
    }
    if(!heroes.get(i).getColor().equals(color)){
        alerta("El heroe no existe");
    }else{
        if (visible){
            heroes.get(i).makeInvisible();
        }
        ok = true;
        ultimaAccion = "removeHeroe";
        int[] enviar = {heroes.get(i).getEdificio()+1,heroes.get(i).getFuerza()};
        addParametros(heroes.get(i).getColor(),enviar);
        heroes.remove(i);
    }
}

/**
 * Retorna la fuerza del héroe según su color
 * @param color Color del héroe
 */
public int strength(String color){
    int i=0;
    while(i<heroes.size()&& heroes.get(i).getColor() != color){
        i++;
    }
    if(!heroes.get(i).getColor().equals(color)){
        alerta("El heroe no existe");
        return -1;
    }else{
        ok = true;
        return heroes.get(i).getFuerza();
    }
} 

/**
 * Hace visible el simulador (la ciudad)
 */
public void makeVisible(){
    if(visible){
        alerta("Ya está visible.");
    }else{
        for(int i =0;i<edificios.size();i++){
            if(edificios.get(i).getAlturaActual()>0){
                edificios.get(i).makeVisible();
            }
        }
        for(int i =0;i<heroes.size();i++){
            if(heroes.get(i).getFuerza()>0){
                heroes.get(i).makeVisible();
            }
        }
        visible = true;
        Canvas canvas = Canvas.getCanvas(0,0);
        canvas.setVisible(visible);
        ultimaAccion = "makeVisible";
        ok = true;
    }
}

/**
 * Hace invisible el simulador (la ciudad)
 */
public void makeInvisible(){
    if(!visible){
        alerta("Ya está invisible.");
    }else{
        for(int i =0;i<edificios.size();i++){
            edificios.get(i).makeInvisible();
        }
        for(int i =0;i<heroes.size();i++){
            heroes.get(i).makeInvisible();
        }
        visible = false;
        Canvas canvas = Canvas.getCanvas(0,0);
        canvas.setVisible(visible);
        ultimaAccion = "makeInvisible";
        ok = true;
    }        
}


/**
 * Rehace la última accion realizada
 */
public void redo(){
    undo();
}

/**
 * Deshace la última accion realizada
 */
public void undo(){
    if (ultimaAccion.equals("addBuilding")){
        removeBuilding(Integer.parseInt(parametros.get(0))+1);     
    }else if (ultimaAccion.equals("removeBuilding")){
        addBuilding(Integer.parseInt(parametros.get(0)),Integer.parseInt(parametros.get(1)),Integer.parseInt(parametros.get(2)),Integer.parseInt(parametros.get(3)));
    }else if (ultimaAccion.equals("addHeroe")){
        removeHeroe(parametros.get(0));
    }else if (ultimaAccion.equals("removeHeroe")){
        addHeroe(parametros.get(0),Integer.parseInt(parametros.get(1)),Integer.parseInt(parametros.get(2)));
    }else if (ultimaAccion.equals("jumpEdificio")){
        jump(parametros.get(0),Integer.parseInt(parametros.get(1)));
    }else if (ultimaAccion.equals("jumpMejor")){
        jump(parametros.get(0),Integer.parseInt(parametros.get(1)));
    }else if (ultimaAccion.equals("makeVisible")){
        makeInvisible();
    }else if (ultimaAccion.equals("makeInvisible")){
        makeVisible();
    }else{
        alerta("No hay acción para deshacer.");
    }
}

/**
 * Agranda o achica la pantalla dependiendo del caracter que se le ingrese
 * @param z (+) Para agrandar la ciudad. (-) Para achicar la ciudad
 */
public void zoom(char z){
    Canvas.getCanvas(ancho,largo).zoom(Character.toString(z));
}


/**
 * Finaliza el simulador
 */
public void finish()
{
    System.exit(0);
}

private void alerta(String s){
    if (visible){
        JOptionPane.showMessageDialog(null,s);
    }
    ok = false;
}

/**
 * Retorna si la última operación se pudo realizar o no
 * @return true si la ultima operación se logró realizar, de lo contrario: false
 */
public boolean ok(){
    return ok;
}
}