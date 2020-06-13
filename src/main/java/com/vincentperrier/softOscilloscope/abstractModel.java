/*
    This file is part of softOscilloscope.

    softOscilloscope is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    softOscilloscope is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with softOscilloscope.  If not, see <https://www.gnu.org/licenses/>.
*/
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
 * <p>
 * Usually, in an MVC application such as this one, the data model should
 * inherit from this abstract class.
 */
public abstract class abstractModel {
    protected Vector<view> listOfViews;

    /**
     * public class methods
     */
    abstractModel() {
        listOfViews = new Vector<view>();
    }

    public void addObserver(view o) {
        if (o != null) {
            this.listOfViews.add(o);
        }
    }

    /**
     * protected class methods
     */
    protected void notifyObservers() {
        for (view i : listOfViews) {
            i.update();
        }
    }
}
