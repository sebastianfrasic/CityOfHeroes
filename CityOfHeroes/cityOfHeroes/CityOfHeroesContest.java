package cityOfHeroes;

import java.util.PriorityQueue;
import java.lang.*;
import javax.swing.JOptionPane;
import java.util.LinkedList;
    /**
    * Clase para resolver el problema de la maratón 
    * 
    * @author Juan Sebastian Frásica y Juan Sebastián Gómez
    * 
    */
    public class CityOfHeroesContest{
        private static final double g = 9.80665;
        private int[] padres; //
        private static int anchoEdificios; 
        private int largoCiudad;
        public CityOfHeroesContest(){
        }
        
        
        /**
         * Simulacion de la ciudad
         * @param configuracion Caracteristicas de la ciudad: columnas, filas, anchoEdificio, velocidad, x, y
         * @param edificios Alturas de los edificios
         * @param building A cual edificio va a saltar el heroe
         * @return Si puedo saltar o no a building
         */
        public static boolean simulate(int[] configuracion, int [][] edificios,int building){ 
            boolean legal = false;
            if(edificios.length> 1){
                alerta("No se puede hacer la simulación.");
            }else{
                CityOfHeroes city = armarCiudad(configuracion[2],configuracion[3],edificios[0],configuracion[4]);
                int[][] grafo = armarGrafo(city,edificios[0],configuracion[3]);
                int [] ruta =ruta(building-1,bfs(grafo,configuracion[4]-1)); 
                //System.out.println(configuracion[4]);
                city.jump("blue",1);
                city.makeVisible();
                for(int j: ruta){
                    //System.out.println("salto a "+(j+1));
                    city.jump("blue",j+1);
                }
                legal = city.ok();
            }
            return legal;
        }
        
        private static int[] bfs(int[][]grafo,int source){ //source es punto de inicio
            int[] padres  = new int[grafo.length];
            boolean[] visit = new boolean[grafo.length];
            LinkedList cola = new LinkedList();
            for(int i = 0;i<padres.length;i++){ 
                padres[i] = -1; //como si no tuvieran padres
                visit[i] = false;
            }
            visit[source] = true; //yo soy mi propio padre
            cola.add(source);
            while (!cola.isEmpty()){
                int u = (int)cola.poll();
                for(int v = 0;v<grafo[u].length;v++){ 
                    if (grafo[u][v]==1){
                        if (!visit[v]){
                            visit[v] = true;
                            padres[v]=u;
                            cola.add(v);
                        }
                    }
                }
            }
            return padres;
        }
        
        private static int[] ruta(int target,int[] padres){ 
            int[] ruta = new int[0]; //ruta vacia
            do{ //Garantiza que por lo menos lo voy a hacer una vez
                int[] nueva = new int[ruta.length+1]; 
                for (int i = 0;i<ruta.length;i++){ //copiar todos los elementos
                    nueva[i]=ruta[i];
                }
                nueva[ruta.length]=target; //ir mirando hasta donde llegar hasta que encuentro un target
                ruta = nueva;
                target = padres[target];
            }while(padres[target]!=-1);
            return ruta; //ruta que tengpo que seguir para llegar desde el source hasta el target
        }
        
        
        /**
         * Solución del problema de la maratón
         * @param configuracion Caracteristicas de la ciudad: [columnas, filas, anchoEdificio, velocidad, x, y]
         * @param edificios Alturas de los edificios
         * @return Respuestas de la solución convertida en String
         */
        public static String[][] solve(int[] configuracion, int [][] edificios){ 
            int[] solucion = new int[edificios[0].length]; //Almacena la respuesta
            String [][] res = new String[1][edificios[0].length]; //Pasa la resp a String
            if(configuracion[1]> 1){ //Si es mas de una linea
                alerta("No se puede hacer la solución.");  
            }else{
                CityOfHeroes city = armarCiudad(configuracion[2],configuracion[3],edificios[0],configuracion[4]);
                int[][] grafo = armarGrafo(city,edificios[0],configuracion[3]);  // edificios[0] = lista de las alturas de los edificios, configuracion[3] = velocidad
                solucion = dijkstra(configuracion[4]-1,grafo,edificios[0]); //[4] edificio donde arrancó
                for(int i=0;i<solucion.length;i++){ //Convertir las respuestas de solucion a string
                    res[0][i]= Integer.toString(solucion[i]);
                }
            }    
            return res;        
        }
        
        private static CityOfHeroes armarCiudad(int ancho, int v,int[] edificios,int posHeroe){
            anchoEdificios = ancho;
            int maximo = edificios[0],ymax; //ymax es para seber qué alto toca ponerle a la ciudad. 
            CityOfHeroes city;
            for(int i =0;i<edificios.length;i++){
                if (edificios[i]>maximo){
                    maximo = edificios[i]; //Busco el edificio mas alto
                }
            }
            ymax = (int)(Math.pow(v,2)/(2*g))+maximo+16; //16 es el ancho del heroe. ymax es para que el heroe haciendo un salto de 90 grados no toque la altura de la ciudad
            city = new CityOfHeroes(ancho*edificios.length,ymax);
            for (int i = 0; i<edificios.length;i++){
                city.addBuilding(i*ancho,ancho,edificios[i],100); //Agrega edificios
            }
            city.addHeroe("blue",posHeroe,100); //Agrega heroe
            return city;
        }
        
        private static int[][] armarGrafo(CityOfHeroes city,int[] edificios,int v){
            int[][] grafo= new int[edificios.length][edificios.length];
            for (int i = 1 ; i <= edificios.length;i++){ 
                city.jump("blue",i); //Que salte al primero
                if (city.ok()){ //Si puede saltar al primero
                    for (int j = i+1 ;j<=edificios.length;j++){
                        if (city.jumpPlan("blue",j,v)){
                            grafo[i-1][j-1] = 1; // Para indicar que hay relacion entre los nodos del grafo
                            grafo[j-1][i-1] = 1;
                        }
                    }
                }
            }
            return grafo;
        }
        
        /**
         *Encuentra la ruta mas corta desde un punto de inicio a cada punto del grafo 
         *@param source punto de inicio 
         *@param grafo grafo a revisar 
         *@edificios edificioas de la ciudad
         *@return las distancias a los puntos del grafo 
         */
        private static int[] dijkstra(int source,int[][]grafo,int[]edificios){
            double[] distance  = new double[edificios.length];
            boolean[] visit = new boolean[edificios.length];
            LinkedList cola = new LinkedList();
            for(int i = 0;i<distance.length;i++){
                distance[i] = Double.POSITIVE_INFINITY; //inicializo todas las distancias en infinito
                visit[i] = false; //visitados inicializan en falso
            }
            distance[source]=0; 
            cola.add(source); 
            while (!cola.isEmpty()){
                int u = (int)cola.poll(); // poll = pop
                if (!visit[u]){
                    visit[u] = true; //si no lo he visitado, lo visito
                    for(int v = 0;v<grafo[u].length;v++){ //toda la fila que me relaciona mi grafo
                        if (grafo[u][v]==1){ //miro si hay una relacion
                            if(distance[v]>distance[u]+1){ //relajamiento
                                distance[v] = distance[u]+1;
                                cola.add(v);
                            }
                        }
                    }
                }
            }
            int[] respuesta = new int [edificios.length]; //en respuesta estan las distancias del djsitkra en double, las convierto a int y la retorno
            for (int k =0;k<distance.length;k++){ 
                respuesta[k] = (int)distance[k];
            }
            return respuesta;
        }
        
        private static void alerta(String s){
            JOptionPane.showMessageDialog(null,s);
        }
}
