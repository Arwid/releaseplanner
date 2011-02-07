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

import java.util.*;

/** A feature request recorded against a given software product. */
public class Feature {
    /** Create a new feature with the indicated attributes.
     *  @param id a (unique) numerical id for the feature
     *  @param shortDescription a short textuial description of the feature (a phrase)
     *  @param sizing the effort in person days estimated to code this feature
     *  @param priority the relative importance of implementing this feature 
     */
    public Feature(int id, String shortDescription, double sizing, Priority priority) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.sizing = sizing;
        this.priority = priority;
    }


    /** Measures cumulative customer desirability for this feature.
     *  @return the sume of the customer desirabilities acorss all customers requesting (0 if none)
     */
    public double customerDesirability() {
        double d = 0;
        for (Enumeration e = customerRequests.elements(); e.hasMoreElements(); ) {
            CustomerRequest cr = (CustomerRequest)e.nextElement();
            int cd = cr.getDesirability();
            d += cd;
        }
        return d;
    }

    public void setSizing(double sizing) { this.sizing = sizing; }
    public double getSizing() { return sizing; }

    public void setPriority(Priority priority) { this.priority = priority; }
    public Priority getPriority() { return priority; }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    public String getShortDescription() { return shortDescription; }

    public void setLongDescription(String longDescription) { this.longDescription = longDescription; }
    public String getLongDescription() { return longDescription; }

    public void setChampion(Employee e) { champion = e; }
    public Employee getChampion() { return champion; }

    public void addCustomerRequest(CustomerRequest cr) { customerRequests.add(cr); }
    public Iterator customerRequestIterator() { return customerRequests.iterator(); }

    private Priority priority;
    private Employee champion;
    private double sizing;
    private int id;
    private String shortDescription;
    private String longDescription;
    private Vector customerRequests = new Vector();
}
