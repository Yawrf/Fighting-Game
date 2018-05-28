/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighting.game;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author rewil
 */
public class Distributor {
    
    private final static Random rand = new Random();
    
    private final static HashMap<Integer, GUI> GUIs = new HashMap<>();
    private final static HashMap<Integer, Character> Characters = new HashMap<>();
    
    
    /**
     * Adds a GUI to the GUI map, keyed to its ID
     * @param g 
     */
    public static void addGUI(GUI g) {
        
        int id = g.getID();
        if(GUIs.containsKey(id)) {
            id = newGUIID();
            g.changeID(id);
        }
        GUIs.put(id, g);
        
    }
    
    public static GUI getGUI(int id) {
        
        GUI g = GUIs.get(id);
        
        return g;
    }
    
    public static int newGUIID() {
        
        int temp = rand.nextInt();
        while(temp < 0 || GUIs.containsKey(temp)) {
            temp = rand.nextInt();
        }
        
        return temp;
    }
    
    public static void addCharacter(Character c) {
        
        int id = c.getID();
        if(Characters.containsKey(id)) {
            id = newCharacterID();
            c.changeID(id);
        }
        Characters.put(id, c);
        
    }
    
    public static Character getCharacter(int id) {
        
        Character c = Characters.get(id);
        
        return c;
    }
    
    public static int newCharacterID() {
        
        int temp = rand.nextInt();
        while(temp < 0 || Characters.containsKey(temp)) {
            temp = rand.nextInt();
        }
        
        return temp;
    }
    
    
}
