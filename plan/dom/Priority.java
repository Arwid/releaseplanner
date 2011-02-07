/******************************************************************************
 *   releaseplanner
 *   Copyright (C) 2011  Arwid Bancewicz <arwid@arwid.ca>
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/
 
package plan.dom;

/** Implements a priority type of {high, med, low} */
public class Priority implements Comparable {
    private static Priority[] prioObj = {new Priority(0), new Priority(1), new Priority(2)};
    private static String[] prioStr = {"high", "med", "low"};
    public static final Priority high = prioObj[0];
    public static final Priority med = prioObj[1];
    public static final Priority low = prioObj[2];
    private int i;

    /** Private access so that only the instances defined above can exist. */
    private Priority(int i) {
        this.i = i;
    }

    /** high &gt; med &gt; low */
    public int compareTo(Object o) {
        // Return values:
        //    -1: this < o
        //     0: this == o
        //    +1: this > o
        Priority other = (Priority)o;
        if (i < other.i)
            return +1;
        else if (i > other.i)
            return -1;
        else
            return 0;
    }

    /** tests for equality of this priority with the other */
    public boolean equals(Object o) {
        return i == ((Priority)o).i;
    }

    /** converts to a standard string representation of priorities */
    public String toString() {
        return prioStr[i];
    }

    /** Return the priority object corresponding to the given name.
     *  @param name the name of the priority level
     *  @return the requested object or null if the name does not match
     */
    public static Priority get(String name) {
        for (int i = 0; i < prioStr.length; i++)
            if (prioStr[i].equalsIgnoreCase(name.trim()))
                return prioObj[i];
        return null;
    }
}
