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
public class GUI {
    
    public enum State {
        Greeting,
        FirstGreeting;
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
    private Scanner ui = new Scanner(System.in);
    
    public void changeState(State state) {
        currentState = state;
    }
    
    @Override
    public String toString() {
        String output = "";
        
        switch(currentState) {
            case FirstGreeting: output += FirstGreetingGUI();
                break;
            case Greeting: output += GreetingGUI();
                break;
        }
        
        return output;
    }
    
    // User Interface Elements
    
    
    // Construction Methods
    
    private String FirstGreetingGUI() {
        String output = "";
        int buffer = 3;
        
        output += divider();
        String[] temp = {"Name: ???", "Please Enter Your Name"};
        output += partitionedLine(temp, buffer);
        output += divider();
        
        String prompt = "Name: ";
        output += prompt;
        System.out.print(output);
        String name = ui.nextLine();
        
        character = Character.getInstance(name);
        
        output = " ";
        return output;
    }
    
    private String GreetingGUI() {
        String output = "";
        
        output += divider();
        String message = "Hello, ";
        message += character.getName();
        output += bodyLine(message);
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
     * @param middle Body Text
     * @return 
     */
    private String bodyLine(String middle) {
        String output = "";
        
        int length = middle.length();
        int whiteSpace = (width - length - (2*capSize))/2;
        
        for(int i = 0; i < capSize; ++i) {
            output += verBar;
        } 
        for(int i = 0; i < whiteSpace; ++i) {
            output += space;
        }
        output += middle;
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
            strings[s] = temp;
        }
        int length = 0;
        for(String s : strings) {
            length += s.length();
        }
        int whiteSpace = (width - length) / (strings.length - 1);
        
        for(int i = 0; i < strings.length; ++i) {
            output += strings[i];
            if(i != strings.length - 1) {
                for(int j = 0; j < whiteSpace; ++j) {
                    output += space;
                }
            }
            if(i == strings.length / 2) {
                for(int j = 0; j < (width - length) % (strings.length - 1); ++j) {
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
            strings[s] = temp;
        }
        int length = 0;
        for(String s : strings) {
            length += s.length();
        }
        int whiteSpace = (width - length) / (strings.length - 1);
        
        for(int i = 0; i < strings.length; ++i) {
            output += strings[i];
            if(i != strings.length - 1) {
                for(int j = 0; j < whiteSpace; ++j) {
                    output += space;
                }
            }
            if(i == strings.length / 2) {
                for(int j = 0; j < (width - length) % (strings.length - 1); ++j) {
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
