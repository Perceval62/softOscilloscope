package com.vincentperrier.softOscilloscope;

import java.util.Vector;

/**
 * @name observable
 * @author Vincent
 */

/**
 * This abstract class represents an item that can be observed.
 * Calling addObserver and notifyObserver on a children class
 * will result in the observer pattern behavior.
 *
 * Usually, in an MVC application such as this one, the data model should
 * inherit from this abstract class.
 */
public class observable {
    protected Vector<observer> listOfObservers;

    /**
     * public class methods
     */
    observable()
    {
        listOfObservers = new Vector<>();
    }

    public void addObserver(observer o)
    {
        if (o != null) {this.listOfObservers.add(o); }
    }

    /**
     * protected class methods
     */
    protected void notifyObservers()
    {
        for (observer i : listOfObservers ) {
            i.update();
        }
    }
}
