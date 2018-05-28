/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighting.game;

/**
 *
 * @author rewil
 */
public class Character extends Identifiable {
    
    private final String name;
    private static Character instance;
    
    private Character(String n) {
        name = n;
    }
    
    public static Character getInstance(String n) {
        if(instance == null) {
            instance = new Character(n);
        }
        return instance;
    }
    
    public String getName() {
        return name;
    }
    
}
