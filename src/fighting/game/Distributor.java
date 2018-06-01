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
    private final static HashMap<Integer, Monster> Monsters = new HashMap<>();
    
    private final static String error = "Object not found in Map";
    
    
    /**
     * Adds a GUI to the GUI map, keyed to its ID
     * @param g GUI added
     * @return The ID of the GUI stored
     */
    public static int addGUI(GUI g) {
        
        int id = g.getID();
        if(GUIs.containsKey(id)) {
            id = newGUIID();
            g.changeID(id);
        }
        GUIs.put(id, g);
        return id;
    }
    
    /**
     * Gets a GUI from the list by the given key
     * @param id Key of the wanted GUI
     * @return 
     */
    public static GUI getGUI(int id) {
        GUI g;
        if(GUIs.containsKey(id)) {
            g = GUIs.get(id);
        }
        else {
            g = null;
            Unidentified(error);
        }
        return g;
    }
    
    /**
     * Creates a new ID for a GUI that isn't already being used in the Map
     * @return 
     */
    private static int newGUIID() {
        
        int temp = rand.nextInt();
        while(temp < 0 || GUIs.containsKey(temp)) {
            temp = rand.nextInt();
        }
        
        return temp;
    }
    
    /**
     * Adds a Character to the Character map, keyed to its ID
     * @param c Character added
     * @return The ID of the Character stored
     */
    public static int addCharacter(Character c) {
        
        int id = c.getID();
        if(Characters.containsKey(id)) {
            id = newCharacterID();
            c.changeID(id);
        }
        Characters.put(id, c);
        return id;
    }
    
    /**
     * Gets a Character from the list by the given key
     * @param id Key of the wanted Character
     * @return
     */
    public static Character getCharacter(int id) {
        Character c;
        if(Characters.containsKey(id)) {
            c = Characters.get(id);
        }
        else {
            c = null;
            Unidentified(error);
        }
        return c;
    }
    
    /**
     * Creates a new ID for a Character that isn't already being used in the Map
     * @return 
     */
    private static int newCharacterID() {
        
        int temp = rand.nextInt();
        while(temp < 0 || Characters.containsKey(temp)) {
            temp = rand.nextInt();
        }
        
        return temp;
    }
    
     /**
     * Adds a Monster to the Monster map, keyed to its ID
     * @param m Monster added
     * @return The ID of the Monster stored
     */
    public static int addMonster(Monster m) {
        
        int id = m.getID();
        if(Monsters.containsKey(id)) {
            id = newMonsterID();
            m.changeID(id);
        }
        Monsters.put(id, m);
        return id;
    }
    
    /**
     * Gets a Monster from the list by the given key
     * @param id Key of the wanted Monster
     * @return
     */
    public static Monster getMonster(int id) {
        Monster m;
        if(Monsters.containsKey(id)) {
            m = Monsters.get(id);
        }
        else {
            m = null;
            Unidentified(error);
        }
        return m;
    }
    
    /**
     * Creates a new ID for a Monster that isn't already being used in the Map
     * @return 
     */
    private static int newMonsterID() {
        
        int temp = rand.nextInt();
        while(temp < 0 || Monsters.containsKey(temp)) {
            temp = rand.nextInt();
        }
        
        return temp;
    }
    
    /**
     * Simulation of the Unidentified Exception to avoid throwing
     * @param s 
     */
    private static void Unidentified(String s) {
        System.out.println((char)27 + "[31m" + s + (char)27 + "[30m");
        System.exit(0);
    }
    
}
