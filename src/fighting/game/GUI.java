/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighting.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rewil
 */
public class GUI extends Identifiable {
    
    public enum State {
        FirstGreeting,
        Greeting,
        Battle,
        Attacking,
        Defending,
        Fleeing,
        
        GameOver;
    }
    
    private State currentState = State.FirstGreeting;
    private Character character;
    
    private final int width = 75; 
    private final int capSize = 2;
    private final char horBar = '~';
    private final char verBar = '|';
    private final char space = ' ';
    private final char openPart = '<';
    private final char closePart = '>';
    
    private final char escapeChar = (char)27;
    private final int escapeSize = 5;
    private final Random rand = new Random();
    private final Scanner ui = new Scanner(System.in);
    
    public GUI() {
        super();
        Distributor.addGUI(this);
    }
    
    /**
     * Changes the state of the GUI, controls how it functions
     * @param state 
     */
    public void changeState(State state) {
        currentState = state;
    }
    
    /**
     * Gets the state of the GUI
     * @return 
     */
    public State getState() {
        return currentState;
    }
    
    
    @Override
    public String toString() {
        String output = "";
        
        switch(currentState) {
            case FirstGreeting: output += FirstGreetingGUI();
                break;
            case Greeting: output += GreetingGUI();
                break;
            case Battle: {
                try {
                        output += BattleGUI();
                    } catch (Unidentified e) {
                        System.out.println(escapeChar + "[31m" + e.getMessage());
                        System.exit(0);
                    }
                }
                break;
            case Attacking: {
                    try {
                        output += AttackingGUI();
                    } catch (Unidentified ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                
                
            case GameOver: output += GameOverGUI();
                break;
        }
        
        return output;
    }
    
    // User Interface Elements
    
    /**
     * Fetches and returns a Name, using the current line and ending with a new one
     * @return 
     */
    private String getName() {
        String prompt = "Name: ";
        System.out.print(prompt);
        String name = ui.nextLine();
        
        return name;
    }
    
    private State selectBattleOption() throws Unidentified {
        
        System.out.print("Select Option: ");
        String temp = ui.nextLine();
        switch(temp.toLowerCase()) {
            case "a": return State.Attacking;
            case "b": return State.Defending;
            case "c": return State.Fleeing;
            default: throw new Unidentified("Answer Unknown");
        }
        
    }
    
    // Construction Methods
    
    /**
     * Runs if currentState is FirstGreeting; this establishes a name for the character
     * @return 
     */
    private String FirstGreetingGUI() {
        String output = "";
        int buffer = 3;
        
        output += divider();
        String[] temp = {"Name: ???", "Please Enter Your Name"};
        output += partitionedLine(temp, buffer);
        output += divider();
        System.out.print(output);
        String name = getName();
        character = new Character(name);
        
        changeState(State.Greeting);
        
        output = " ";
        return output;
    }
    
    /**
     * Runs if currentState is Greeting; this merely says Hello, followed by their name.
     * @return 
     */
    private String GreetingGUI() {
        String output = "";
        
        output += divider();
        String message = "Hello, ";
        message += character.getName();
        output += bodyLine(message);
        output += divider();
        
        changeState(State.Battle);
        
        return output;
    }
    
    private ArrayList<Integer> monsterIDs = new ArrayList<>();
    private Integer monsterID = null;
    /**
     * Runs if currentState is Battle; this states the names of the Character and the Monster, then the current stats of the Character and Monster, and gives options for how to continue
     * @return
     * @throws Unidentified 
     */
    private String BattleGUI() throws Unidentified {
        String output = "";
        
        if(monsterID == null || Distributor.getMonster(monsterID).getDefeated()) {
            monsterID = new Goblin().getID();
            monsterIDs.add(monsterID);
        }
        Monster m = Distributor.getMonster(monsterID);
        String[] names = {character.getName(), m.getName()};
        String[] health = {character.getHealth() + "/" + character.getMaxHealth() + " HP", m.getHealth() + "/" + m.getMaxHealth() + " HP"};
        String[] strength = {"Str: " + character.getStrength(), "Str: " + m.getStrength()};
        String[] defense = {"Def: " + character.getDefense(), "Def: " + m.getDefense()};
        String[] speed = {"Spd: " + character.getSpeed(), "Spd: " + m.getSpeed()};
        String[] exp = {"Lvl: " + character.getLevel(), character.getExp() + "/" + character.getNextExp() + " EXP"};
        String message = "Select an option";
        String[] options = {"A: Attack", "B: Defend", "C: Flee"};
        
        output += divider();
        output += partitionedLine(names);
        output += partitionedLine(health);
        output += partitionedLine(strength);
        output += partitionedLine(defense);
        output += partitionedLine(speed);
        output += partitionedLine(exp, 3);
        output += bodyLine(message);
        output += optionsLine(options);
        
        System.out.println(output);
        changeState(selectBattleOption());
        
        output = "\n";
        
        return output;
    }
    
    /**
     * Runs if currentState is Attacking; this calculates the damage the Character does to the Monster, then vice versa if it's still alive, then checks for passive regeneration; Damage numbers are in red, regen is in green.
     * @return
     * @throws Unidentified 
     */
    private String AttackingGUI() throws Unidentified {
        String output = "";
        
        Monster m = Distributor.getMonster(monsterID);
        output += divider();
        
        int attackDamage = (character.attack() - m.defend());
        if(attackDamage < 0) {
            attackDamage = 0;
        }
        String attack = character.getName() + " attacks " + m.getName() + " for " + escapeChar + "[31m" + attackDamage + escapeChar + "[30m" + " damage.\n";
        m.changeHealth(m.getHealth() - attackDamage);
        output += attack;
        if(m.getHealth() > 0) {
            int defenseDamage = (m.attack() - character.defend());
            if(defenseDamage < 0) {
                defenseDamage = 0;
            }
            String defense = m.getName() + " attacks " + character.getName() + " for " + escapeChar + "[31m" + defenseDamage + escapeChar + "[30m" + " damage.\n";
            character.changeHealth(character.getHealth() - defenseDamage);
            output += defense;
        }
        if(character.getHealth() > 0 && character.getHealth() < character.getMaxHealth()) {
            if(rand.nextBoolean()) {
                int regen = rand.nextInt(character.getDefense()) + 1;
                int temp = (character.getHealth() + regen) - character.getMaxHealth();
                if(temp > 0){
                    regen -= temp;
                }
                character.changeHealth(character.getHealth() + regen);
                output += character.getName() + " passively regenerates " + escapeChar + "[32m" + regen + escapeChar + "[30m" + " HP.\n";
            }
        }
        
        output += divider();
        changeState(State.Battle);
        
        return output;
    }
    
    
    
    /**
     * Runs if currentState is GameOver; this merely says Game Over
     * @return 
     */
    private String GameOverGUI() {
        String output = "";
        
        output += divider();
        output += bodyLine("Game");
        output += bodyLine("Over");
        output += divider();
        
        return output;
    }
    
    // Construction Elements
    
    /**
     * Prints a divider line for GUI
     * @return 
     */
    private String divider() {
        String output = "";
        
        for(int i = 0; i < width; ++i) {
            output += horBar;
        }
        
        output += '\n';
        return output;
    }
    
    /**
     * Prints the body area given a String to use
     * @param message Body Text
     * @return 
     */
    private String bodyLine(String message) {
        String output = "";
        
        int length = message.length();
        for(char c : message.toCharArray()) {
            if (c == escapeChar) {
                length -= escapeSize;
            }
        }
        int whiteSpace = (width - length - (2*capSize))/2;
        
        for(int i = 0; i < capSize; ++i) {
            output += verBar;
        } 
        for(int i = 0; i < whiteSpace; ++i) {
            output += space;
        }
        output += message;
        for(int i = 0; i < whiteSpace; ++i) {
            output += space;
        }
        if(length % 2 != width % 2) {
            output += space;
        }
        for(int i = 0; i < capSize; ++i) {
            output += verBar;
        }
        
        output += '\n';
        return output;
    }    
    
    /**
     * Creates a line with multiple options listed in it
     * @param strings List of Options
     * @return 
     */
    private String optionsLine(String[] strings) {
        String output = "";
        String[] newStrings = new String[strings.length];
        for(int s = 0; s < strings.length; ++s) {
            String temp = "";
            for(int i = 0; i < capSize; ++i) {
                temp += openPart;
            }
            temp += space;
            temp += strings[s];
            temp += space;
            for(int i = 0; i < capSize; ++i) {
                temp += closePart;
            }
            newStrings[s] = temp;
        }
        int length = 0;
        for(String s : newStrings) {
            length += s.length();
            for(char c : s.toCharArray()) {
                if (c == escapeChar) {
                    length -= escapeSize;
                }
            }
        }
        int whiteSpace = (width - length) / (newStrings.length - 1);
        
        for(int i = 0; i < newStrings.length; ++i) {
            output += newStrings[i];
            if(i != newStrings.length - 1) {
                for(int j = 0; j < whiteSpace; ++j) {
                    output += space;
                }
            }
            if(i == newStrings.length / 2) {
                for(int j = 0; j < (width - length) % (newStrings.length - 1); ++j) {
                    output += space;
                }
            }
        }
        
        
        output += '\n';
        return output;
    }
    
    /**
     * Creates a line with multiple parts, not setup as options
     * @param strings Parts
     * @return 
     */
    private String partitionedLine(String[] strings) {
        String output = "";
        String[] newStrings = new String[strings.length];
        
        for(int s = 0; s < strings.length; ++s) {
            String temp = "";
            for(int i = 0; i < capSize; ++i) {
                temp += verBar;
            }
            temp += space;
            temp += strings[s];
            temp += space;
            for(int i = 0; i < capSize; ++i) {
                temp += verBar;
            }
            newStrings[s] = temp;
        }
        int length = 0;
        for(String s : newStrings) {
            length += s.length();
            for(char c : s.toCharArray()) {
                if (c == escapeChar) {
                    length -= escapeSize;
                }
            }
        }
        int whiteSpace = (width - length) / (newStrings.length - 1);
        
        for(int i = 0; i < newStrings.length; ++i) {
            output += newStrings[i];
            if(i != newStrings.length - 1) {
                for(int j = 0; j < whiteSpace; ++j) {
                    output += space;
                }
            }
            if(i == newStrings.length / 2) {
                for(int j = 0; j < (width - length) % (newStrings.length - 1); ++j) {
                    output += space;
                }
            }
        }
        
        
        output += '\n';
        return output;
    }
    
    /**
     * Creates a line with multiple parts, not setup as options, and inset from the edges with buffers
     * @param strings Parts
     * @param buffers Size of buffers on ends
     * @return 
     */
    private String partitionedLine(String[] strings, int buffers) {
        String output = "";
        
        String openBuffer = "";
        String closeBuffer = "";
        for(int i = 0; i < capSize; ++i) {
            openBuffer += verBar;
        }
        for(int i = 0; i < buffers; ++i) {
            openBuffer += space;
            closeBuffer += space;
        }
        for(int i = 0; i < capSize; ++i) {
            closeBuffer += verBar;
        }
        
        String[] newStrings = new String[strings.length + 2];
        newStrings[0] = openBuffer;
        for(int i = 1; i <= strings.length; ++i) {
            newStrings[i] = strings[i - 1];
        }
        newStrings[newStrings.length - 1] = closeBuffer;
        
        for(int s = 1; s < newStrings.length - 1; ++s) {
            String temp = "";
            for(int i = 0; i < capSize; ++i) {
                temp += verBar;
            }
            temp += space;
            temp += newStrings[s];
            temp += space;
            for(int i = 0; i < capSize; ++i) {
                temp += verBar;
            }
            newStrings[s] = temp;
        }
        int length = 0;
        for(String s : newStrings) {
            length += s.length();
            for(char c : s.toCharArray()) {
                if (c == escapeChar) {
                    length -= escapeSize;
                }
            }
        }
        int whiteSpace = (width - length) / (newStrings.length - 1);
        
        for(int i = 0; i < newStrings.length; ++i) {
            output += newStrings[i];
            if(i != newStrings.length - 1) {
                for(int j = 0; j < whiteSpace; ++j) {
                    output += space;
                }
            }
            if(i == newStrings.length / 2) {
                for(int j = 0; j < (width - length) % (newStrings.length - 1); ++j) {
                    output += space;
                }
            }
        }
        
        
        output += '\n';
        return output;
    }
    
}
