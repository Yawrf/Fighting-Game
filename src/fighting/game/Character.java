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
public class Character extends Identifiable {
    
    private final String name;
    private static Character instance;
    private final Random rand = new Random();
    private final int attackDie = 6;
    
    private int maxHealth = 10;
    private int health = maxHealth;
    private int strength = 1;
    private int defense = 1;
    private int speed = 1;
    
    /**
     * Creates a Character instance with the name n
     * @param n Name of character
     */
    public Character(String n) {
        name = n;
        Distributor.addCharacter(this);
    }
    
    // Actions
    
    public int attack() {
        
        boolean crit = ((int)(rand.nextDouble() * 100) == 0);
        int damage = rollDice(attackDie, strength);
        if(crit) {
            damage *= 2;
        }
        
        return damage;
    }
    
    // Tools
    
    private int rollDice(int sides) {
        int temp = 0;
        temp += rand.nextInt(sides) + 1;
        return temp;
    }
    
    private int rollDice(int sides, int quantity) {
        int temp = 0;
        for(int i = 0; i < quantity; ++i) {
            temp += rand.nextInt(sides) + 1;
        }
        return temp;
    }
    
    
    // Stats
    
    /**
     * Gets the name of the Character
     * @return 
     */
    public String getName() {
        return (char)27 + "[34m" + name + (char)27 + "[30m";
    }
    
    /**
     * Returns Max Health of the Character
     * @return 
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * Sets the new Max Health
     * @param m 
     */
    public void changeMaxHealth(int m) {
        maxHealth = m;
    }
    
    /**
     * Returns Health of the Character
     * @return 
     */    
    public int getHealth() {
        return health;
    }
    
    /**
     * Sets the new Health
     * @param h 
     */
    public void changeHealth(int h) {
        health = h;
    }
    
    /**
     * Returns Strength of the Character
     * @return 
     */    
    public int getStrength() {
        return strength;
    }
    
    /**
     * Sets the new Strength
     * @param s 
     */
    public void changeStrength(int s) {
        strength = s;
    }
    
    /**
     * Returns Defense of the Character
     * @return 
     */
    public int getDefense() {
        return defense;
    }
    
    /**
     * Sets the new Defense
     * @param d 
     */
    public void changeDefense(int d) {
        defense = d;
    }
    
    /**
     * Returns Speed of the Character
     * @return 
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * Sets the new Speed
     * @param s 
     */
    public void changeSpeed(int s) {
        speed = s;
    }
    
    
}
