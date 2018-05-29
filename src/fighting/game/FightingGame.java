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
public class FightingGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GUI gui = new GUI();
        while(gui.getState() != GUI.State.GameOver) {
            System.out.println(gui);
        }
        System.out.println(gui);
        
    }
    
}
