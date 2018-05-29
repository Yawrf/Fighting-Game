/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighting.game;

import java.util.Scanner;

/**
 *
 * @author rewil
 */
public class GUI extends Identifiable {
    
    public enum State {
        FirstGreeting,
        Greeting,
        
        GameOver;
    }
    
    private State currentState = State.FirstGreeting;
    private Character character;
    
    private int width = 75; 
    private int capSize = 2;
    private char horBar = '~';
    private char verBar = '|';
    private char space = ' ';
    private char openPart = '<';
    private char closePart = '>';
    
    private char escapeChar = (char)27;
    private int escapeSize = 5;
    private Scanner ui = new Scanner(System.in);
    
    public GUI() {
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
        output += bodyLine("This line is too long. This line is too long. This line is too long. This line is too long. This line is too long. This line is too long. This line is too long. ");
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
        
        changeState(State.GameOver);
        
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
        if((length % 2 != width % 2) && (length < width)) {
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
