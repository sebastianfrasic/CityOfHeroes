package cityOfHeroes;

    
    import static org.junit.Assert.*;
    import org.junit.After;
    import org.junit.Before;
    import org.junit.Test;

public class CityOfHeroesContestTest{
    CityOfHeroesContest problema;

    
    /*
    @Test
    public void deberiaSimular(){
        CityOfHeroesContest problema = new CityOfHeroesContest();
        int[] config = {4,1,100,55,1,1};
        int[][] edificios = {{0,10,20,30}};
        problema.simulate(config,edificios,3);
    }
    */
    
   
    @Test
    public void deberiaSimular(){
        CityOfHeroesContest problema = new CityOfHeroesContest();
        int[] config = {4,1,100,55,1,1};
        int[][] edificios = {{10,40,60,10}};
        problema.simulate(config,edificios,4);
    }
    
    
    
    @Test
    public void deberiaSolve(){
        CityOfHeroesContest problema = new CityOfHeroesContest();
        int[] config = {4,1,100,55,1,1};
        String [][] solucion;
        int[][] edificios = {{0,10,20,30}};
        solucion =problema.solve(config,edificios);
        String[] res = {"0","1","1","1"};
        assertEquals(solucion[0],res);
    }
   
    
    @Test
    public void deberiaSolve1(){
        CityOfHeroesContest problema = new CityOfHeroesContest();
        int[] config = {4,1,100,55,1,1};
        String [][] solucion;
        int[][] edificios = {{10,40,60,10}};
        solucion =problema.solve(config,edificios);
        String[] res = {"0","1","1","1"};
        assertEquals(solucion[0],res);
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
