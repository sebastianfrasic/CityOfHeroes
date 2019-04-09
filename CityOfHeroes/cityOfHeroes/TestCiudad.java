package cityOfHeroes;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import javax.swing.JOptionPane;

/**
* The test class TestCiudad.
* @author Juan Sebastian Frásica y Juan Sebastián Gómez
*/
public class TestCiudad{
    public CityOfHeroes ciudad;
    
    @Before
    public void setUp()
    {
    }
    
    
    @Test 
    public void deberiaSaltar(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 100, 1);
        ciudad.addBuilding(300, 100, 100, 20);
        ciudad.addHeroe("green", 1, 100);
        //ciudad.makeVisible();
        ciudad.jump("green",2);
        ciudad.undo();
        assertTrue(ciudad.ok());
    }
    
    @Test
    public void mejorSalto(){ 
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(30, 100, 350, 1);
        ciudad.addBuilding(250, 100, 400, 20);
        ciudad.addBuilding(590, 100, 620, 20);
        ciudad.addHeroe("green", 2, 100);
        //ciudad.makeVisible();
        ciudad.jump("green");
        assertTrue(ciudad.ok());
    }
    
    @Test 
    public void segunFGdeberiaSaltarCarefull(){
        CityOfHeroes ciudad = new CityOfHeroes(600,600);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 200, 1);
        ciudad.addBuilding(100, 200, 100, 20);
        ciudad.addHeroe("careful","green", 2, 100);
        //ciudad.makeVisible();
        ciudad.jump("green",70,100,true);
        assertTrue(ciudad.ok());
    }
    
    @Test 
    public void segunFGnoDeberiaSaltarCarefull(){
        CityOfHeroes ciudad = new CityOfHeroes(600,600);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 200, 1);
        ciudad.addBuilding(100, 200, 100, 20);
        ciudad.addHeroe("careful","green", 2, 100);
        //ciudad.makeVisible();
        ciudad.jump("green",70,140,true);
        assertFalse(ciudad.ok());
    }
    
    @Test 
    public void deberiCambiarColorColorido(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding("colorido",0, 100, 200, 1);
        ciudad.addBuilding(100, 200, 100, 20);
        ciudad.addHeroe("green", 2, 100);
        //ciudad.makeVisible();
        ciudad.jump("green",70,135,true);
        assertTrue(ciudad.ok());
    }
    
    
    @Test 
    public void deberiDesaparecerRadical(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding("radical",0, 100, 200, 1);
        ciudad.addBuilding(100, 200, 100, 20);
        ciudad.addHeroe("green", 2, 100);
        //ciudad.makeVisible();
        ciudad.jump("green",70,135,true);
        assertTrue(ciudad.ok());
    }
    
    
    
    @Test 
    public void deberiaAcomodarseFlexible(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.addBuilding("flexible",0, 200, 200, 1);
        ciudad.addBuilding(100, 200, 100, 20);
        //ciudad.makeVisible();
        int[][][] resumen = ciudad.city();
        assertTrue(resumen[0][0][1] == 100 && resumen[0][1][0] == 100);
        //Que el ancho cambie a 100 y la posX sea 100
    }
    
    @Test
    public void deberiaCambiarColorido(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding("colorido",0, 100, 200, 1);
        ciudad.addBuilding(100, 200, 100, 20);
        ciudad.addHeroe("green", 2, 100);
        ciudad.makeVisible();
        ciudad.jump("green",70,135,true);
        
        boolean cambio = Boolean.parseBoolean(JOptionPane.showInputDialog(null, "¿Cambio el color?"));
        assertTrue(cambio);
        
    }
    
    @Test 
    public void peleaClimbervsParatrooper(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.addBuilding("flexible",0, 100, 200, 1);
        ciudad.addBuilding("colorido",200, 100, 400, 20);
        ciudad.addBuilding(500,100,200,20);
        ciudad.addHeroe("paratrooper","red", 2, 100);
        ciudad.addHeroe("climber","green", 3, 100);
        //ciudad.makeVisible();
        ciudad.jump("green",60,135,false);
        ciudad.jump("red",65,110,false);
        int[][][] resumen = ciudad.city();
        assertTrue(resumen[1][0][0] == 1);
    }
    
    @Test
    public void deberiaCaerAlEstrellar(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.addBuilding(0, 100, 200, 1);
        ciudad.addBuilding(200, 100, 100, 20);
        ciudad.addHeroe("paratrooper","green", 2, 100);
        //ciudad.makeVisible();
        ciudad.jump("green",70,110,false);        
        assertTrue(ciudad.ok());
    }
    
    
    @Test
    public void deberiaEscalar(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 200, 1);
        ciudad.addBuilding(200, 100, 100, 20);
        ciudad.addHeroe("climber","green", 2, 100);
        //ciudad.makeVisible();
        ciudad.jump("green",30,135,false);        
        assertTrue(ciudad.ok());
    }
    
    @Test
    public void zoomMas(){ 
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(30, 100, 350, 1);
        ciudad.addBuilding(250, 100, 370, 20);
        ciudad.addHeroe("green", 1, 100);
        //ciudad.makeVisible();
        for(int i=0; i<2; i++){
            ciudad.zoom('+');
        }
        assertTrue(ciudad.ok());
    }
    
    
    @Test
    public void zoomMenos(){ 
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(30, 100, 350, 1);
        ciudad.addBuilding(250, 100, 370, 20);
        ciudad.addHeroe("green", 1, 100);
        //ciudad.makeVisible();
        for(int i=0; i<2; i++){
            ciudad.zoom('-');
        }
        assertTrue(ciudad.ok());
    }
    
    
    
    
    
    @Test
    public void pruebaPeleaDeHeroes(){ //Gana el verde que tiene mas fuerza
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 150, 1);
        ciudad.addBuilding(200, 100, 500, 20);
        ciudad.addBuilding(600, 50, 600, 45);
        ciudad.addHeroe("green", 1, 100);
        ciudad.addHeroe("red", 2, 10);
        //ciudad.makeVisible();
        ciudad.jump("green",60,45,true);
        ciudad.deads();
        assertTrue(ciudad.ok());
    }
    
    
    @Test
    public void pruebaPeleaDeHeroes2(){ //Gana el rojo que tiene mas fuerza
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 150, 1);
        ciudad.addBuilding(300, 100, 500, 20);
        ciudad.addBuilding(600, 50, 600, 45);
        ciudad.addHeroe("green", 1, 100);
        ciudad.addHeroe("red", 2, 90);
        //ciudad.makeVisible();
        ciudad.jump("green",60,70,true);
        assertTrue(ciudad.ok());
    }
    
    
    @Test
    public void pruebaSaltoHaciaAtras(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 100, 1);
        ciudad.addBuilding(100, 100, 200, 1);
        ciudad.addHeroe("red", 2, 10);
        //ciudad.makeVisible();
        ciudad.jump("red",60,95,true);
        assertTrue(ciudad.ok());
    }
    
    
    @Test
    public void pruebaSaltoHaciaAdelante(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 100, 1);
        ciudad.addBuilding(200, 110, 250, 1);
        ciudad.addHeroe("blue", 1, 10);
        //ciudad.makeVisible();
        ciudad.jump("blue",60,60,true);
        assertTrue(ciudad.ok());
    }
    
    @Test
    public void muereHeroe(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 150, 1);
        ciudad.addBuilding(300, 100, 500, 20);
        ciudad.addBuilding(600, 50, 600, 45);
        ciudad.addHeroe("green", 1, 100);
        ciudad.addHeroe("red", 2, 90);
        //ciudad.makeVisible();
        ciudad.jump("red",90,135,true);
        ciudad.deads();
        assertTrue(ciudad.ok());
    }
    
    
    @Test
    public void pruebaSaltoEdificio(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 150, 1);
        ciudad.addBuilding(300, 100, 300, 1);
        ciudad.addBuilding(500, 100, 500, 1);
        ciudad.addHeroe("green", 1, 100);
        ciudad.addHeroe("red", 2, 90);
        //ciudad.makeVisible();
        ciudad.jump("green",3);
        assertTrue(ciudad.ok());
    }
    
    @Test
    public void pruebaUndoSalto(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 150, 1);
        ciudad.addBuilding(300, 100, 500, 20);
        ciudad.addBuilding(600, 50, 600, 45);
        ciudad.addHeroe("green", 1, 100);
        ciudad.addHeroe("red", 2, 90);
        //ciudad.makeVisible();
        ciudad.jump("red");
        ciudad.undo();
        assertTrue(ciudad.ok());
    }
    
    @Test
    public void mejorSaltoConPelea(){
        ciudad = new CityOfHeroes(700,700);
        ciudad.makeInvisible();
        ciudad.addBuilding(0, 100, 150, 1);
        ciudad.addBuilding(200, 100, 250, 20);
        ciudad.addBuilding(600, 100, 450, 20);
        ciudad.addHeroe("green", 1, 45);
        ciudad.addHeroe("red", 3, 100);
        //ciudad.makeVisible();
        ciudad.jump("green");
        assertTrue(ciudad.ok());
    }
}