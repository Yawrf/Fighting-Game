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
public class Unidentified extends Exception {

    /**
     * Creates a new instance of <code>Unidentified</code> without detail
     * message.
     */
    public Unidentified() {
    }

    /**
     * Constructs an instance of <code>Unidentified</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public Unidentified(String msg) {
        super(msg);
    }
}
