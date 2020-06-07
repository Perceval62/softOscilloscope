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
public abstract class model {
    protected Vector<view> listOfViews;

    /**
     * public class methods
     */
    model()
    {
        listOfViews = new Vector<view>();
    }

    public void addObserver(view o)
    {
        if (o != null) {this.listOfViews.add(o); }
    }

    /**
     * protected class methods
     */
    protected void notifyObservers()
    {
        for (view i : listOfViews) {
            i.update();
        }
    }
}
