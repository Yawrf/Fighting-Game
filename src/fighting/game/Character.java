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
    private final int defenseDie = 6;
    
    private int maxHealth = 10;
    private int health = maxHealth;
    private int strength = 2;
    private int defense = 1;
    private int speed = 1;
    
    private int level = 1;
    private int nextExp = calculateNextExp();
    private int currentExp = 0;
    
    /**
     * Creates a Character instance with the name n
     * @param n Name of character
     */
    public Character(String n) {
        super();
        name = n;
        Distributor.addCharacter(this);
    }
    
    // Actions
    
    /**
     * Returns an attack damage by rolling a number of dice equal to the character's strength with a number of sides equal to attackDie
     * @return 
     */
    public int attack() {
        
        boolean crit = ((int)(rand.nextDouble() * 100) == 0);
        int damage = rollDice(attackDie, strength);
        if(crit) {
            damage *= 2;
        }
        
        return damage;
    }
    
    /**
     * Returns an amount of blocked damage by rolling a number of dice equal to the character's defense with a number of sides equal to defenseDie
     * @return 
     */
    public int defend() {
        
        boolean crit = ((int)(rand.nextDouble() * 100) == 0);
        int defend = rollDice(defenseDie, defense);
        if(crit) {
            defend *= 2;
        }
        
        return defend;
    }
    
    // Tools - Combat
    
    /**
     * Rolls a die with the specified number of sides
     * @param sides Number of sides on die
     * @return 
     */
    private int rollDice(int sides) {
        int temp = 0;
        temp += rand.nextInt(sides) + 1;
        return temp;
    }
    
    /**
     * Rolls a specified number of dice with the specified number of faces
     * @param sides Number of sides on dice
     * @param quantity Number of dice
     * @return 
     */
    private int rollDice(int sides, int quantity) {
        int temp = 0;
        for(int i = 0; i < quantity; ++i) {
            temp += rand.nextInt(sides) + 1;
        }
        return temp;
    }
    
    // Tools - Levelling
    
    /**
     * Calculates the Exp needed for the next level based on the current level
     * @return 
     */
    private int calculateNextExp() {
        int temp = 0;
        for(int i = 0; i <= level; ++i) {
            temp += 1000 * i;
        }
        return temp;
    }
    
    // Stats - Special
    
    /**
     * Gets the name of the Character
     * @return 
     */
    public String getName() {
        return (char)27 + "[34m" + name + (char)27 + "[30m";
    }
    
    /**
     * Get the current Level
     * @return 
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Change the current Level
     * @param l 
     */
    private void changeLevel(int l) {
        level = l;
        changeNextExp(calculateNextExp());
    }
    
    /**
     * Get the amount of Exp needed for the next Level
     * @return 
     */
    public int getNextExp() {
        return nextExp;
    }
    
    /**
     * Change the amount of Exp needed for the next Level
     * @param e 
     */
    private void changeNextExp(int e) {
        nextExp = e;
    }
    
    /**
     * Get the Current Exp
     * @return 
     */
    public int getExp() {
        return currentExp;
    }
    
    /**
     * Set new Current Exp
     * @param e 
     */
    public void changeExp(int e) {
        currentExp = e;
        while(currentExp >= nextExp) {
            changeLevel(level + 1);
        }
    }
    
    // Stats - Fundamental
    
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
