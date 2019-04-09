package cityOfHeroes;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CityOfHeroesTest.
 *
 * @version (2.0)
 */
public class CityOfHeroesTest{
    public CityOfHeroes ciudad;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {

    }
    
    
     @Test
    public void segunFGnodeberiasaltar()
    {
        CityOfHeroes ch = new CityOfHeroes(600,600);
        ch.addBuilding(0,100,200,30);
        ch.addBuilding(200,100,500,80);
        ch.addHeroe("careful","blue",1,40);
        ch.jump("blue",60,45,true);
        assertFalse(ch.ok());
    }
    
    @Test
    public void deberiaAñadirEdificioNormal() {
        CityOfHeroes c1 = new CityOfHeroes(100,250);
        c1.makeInvisible();
        c1.addBuilding(0, 10, 20, 5);
        assertTrue(c1.ok());
        c1.addBuilding(11, 10, 20, 10);
        assertTrue(c1.ok());
    }
    @Test
    public void noDeberiaAñadirEdificioNormal() {
        CityOfHeroes c1 = new CityOfHeroes(100,250);
        c1.makeInvisible();
        c1.addBuilding(0, 10, 20, 5);
        assertTrue(c1.ok());
        c1.addBuilding(0, 10, 20, 10);
        assertFalse(c1.ok());
        c1.addBuilding(20, 150, 20, 10);
        assertFalse(c1.ok());
    }

    @Test
    public void deberiaAñadirEdificioFlexible() {
        CityOfHeroes c1 = new CityOfHeroes(100,250);
        c1.makeInvisible();
        c1.addBuilding("flexible",0, 10, 20, 5);
        assertTrue(c1.ok());
        c1.addBuilding("flexible",11, 10, 20, 10);
        assertTrue(c1.ok());
    }

    @Test
    public void deberiaAgregarHeroeNormal() {
        CityOfHeroes c1 = new CityOfHeroes(100,250);
        c1.makeInvisible();
        c1.addBuilding(0, 10, 20, 5);
        assertTrue(c1.ok());
        c1.addBuilding(11, 10, 20, 10);
        assertTrue(c1.ok());
        
        c1.addHeroe("blue", 1, 100);
        assertTrue(c1.ok());
        c1.addHeroe("yellow", 2, 100);
        assertTrue(c1.ok());
    }
    @Test
    public void noDeberiaAgregarHeroeNormal() {
        CityOfHeroes c1 = new CityOfHeroes(100,250);
        c1.makeInvisible();
        c1.addBuilding(0, 10, 20, 5);
        assertTrue(c1.ok());
        c1.addBuilding(11, 10, 20, 10);
        assertTrue(c1.ok());
        
        c1.addHeroe("blue", 1, 100);
        assertTrue(c1.ok());
        c1.addHeroe("yElLoW", 3, 100);
        assertFalse(c1.ok());
    }
    @Test
    public void noDeberianHaberMuertos() {
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.makeInvisible();
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("yellow", 2, 100);
        assertTrue(ch.ok());
        
        assertArrayEquals(ch.deads(), new String[]{});
    }
    @Test
    public void deberiaHaberMuertos() {
        CityOfHeroes c1 = new CityOfHeroes(25, 250);
        c1.makeInvisible();
        c1.addBuilding(0, 10, 20, 5);
        assertTrue(c1.ok());
        c1.addBuilding(11, 10, 20, 10);
        assertTrue(c1.ok());
        
        c1.addHeroe("blue", 1, 100);
        assertTrue(c1.ok());
        c1.addHeroe("yellow", 2, 100);
        assertTrue(c1.ok());
        
        c1.jump("blue", 200, 45, true);
        assertArrayEquals(c1.deads(), new String[]{"blue"});
    }
 
    
    @Test
    public void noDeberiaIndicarDaño() {
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.makeInvisible();
        ch.addBuilding(0, 10, 10, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 200, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("blue", 1, 100);
        assertTrue(ch.ok());
        
        assertFalse(ch.isDamaged(1));
        assertFalse(ch.isDamaged(2));
    }
    
    
    @Test
    public void noDeberiaSerSafeJump() {
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.makeInvisible();
        ch.addBuilding(0, 10, 10, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 200, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("blue", 1, 100);
        assertTrue(ch.ok());
        
        assertFalse(ch.isSafeJump("blue", 200, 20));
    }
    
  
    
    @Test
    public void deberiaRemoveHeroes() {
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.makeInvisible();
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("yellow", 2, 100);
        assertTrue(ch.ok());
        
        ch.removeHeroe("blue");
        assertTrue(ch.ok());
        ch.removeHeroe("yellow");
        assertTrue(ch.ok());
    }
    

    @Test
    public void deberiaStrength() {
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.makeInvisible();
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("yellow", 2, 50);
        assertTrue(ch.ok());
        
        assertEquals(ch.strength("blue"), 100);
        assertEquals(ch.strength("yellow"), 50);
    }
    

   
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After 
    public void tearDown()
    {
    }
}