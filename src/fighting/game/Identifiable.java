/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighting.game;

import java.util.Random;

/**
 *
 * @author rewil
 */
public class Identifiable {
    
    private int id;
    
    protected Identifiable() {
        Random rand = new Random();
        id = rand.nextInt();
        while(id < 0) {
            id = rand.nextInt();
        }
    }
    
    public int getID() {
        return id;
    }
    
    public void changeID(int num) {
        id = num;
    }
    
}
