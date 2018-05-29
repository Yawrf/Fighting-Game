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
public class Monster extends Identifiable {
    
    protected int maxHealth;
    protected int health;
    protected int strength;
    protected int defense;
    protected int speed;
    protected int level;
    protected String name;
    protected boolean defeated = false;
    protected final Random rand = new Random();
    private final int attackDie = 6;
    private final int defenseDie = 6;
    
    protected Monster() {
        super();
        Distributor.addMonster(this);
    }
    
    // Stats - Get
    
    public String getName() {
        return name;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getHealth() {
        return health;
    }
    public int getStrength() {
        return strength;
    }
    public int getDefense() {
        return defense;
    }
    public int getSpeed() {
        return speed;
    }
    public int getLevel() {
        return level;
    }
    public boolean getDefeated() {
        return defeated;
    }    
    
    // Stats - change
    
    public void changeMaxHealth(int i) {
        maxHealth = i;
    }
    public void changeHealth(int i) {
        health = i;
    }
    public void changeStrength(int i) {
        strength = i;
    }
    public void changeDefense(int i) {
        defense = i;
    }
    public void changeSpeed(int i) {
        speed = i;
    }
    public void changeLevel(int i) {
        level = i;
    }
    public void setDefeated() {
        defeated = true;
    }
    
    // Actions
    
    /**
     * Returns an attack damage by rolling a number of dice equal to the monster's strength with a number of sides equal to attackDie
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
     * Returns an amount of blocked damage by rolling a number of dice equal to the monster's defense with a number of sides equal to defenseDie
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
    
}
