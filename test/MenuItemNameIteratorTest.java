/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author x
 */
public class MenuItemNameIteratorTest {
    MenuItemNamesRepo repo;
    MenuItemNameIterator iterator;
    
    public MenuItemNameIteratorTest() {
        repo = new MenuItemNamesRepo(new String[] {"Sevval", "Nur"});
        iterator = new MenuItemNameIterator();
        iterator.setAggregate(repo);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasNext method, of class MenuItemNameIterator.
     */
    @Test
    public void testHasNext() {
        assertEquals(true, iterator.hasNext());       
    }

    /**
     * Test of next method, of class MenuItemNameIterator.
     */
    @Test
    public void testNext() {
        assertEquals("Sevval", iterator.next());
        assertEquals("Nur", iterator.next());
    }
    
}
