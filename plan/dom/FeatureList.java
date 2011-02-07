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

/** A list of features */
public interface FeatureList {
    /** Get an Iterator over the features.
     *  @return the requested Iterator
     */
    public Iterator featureIterator();

    /** Returns the number of features broken out by priority.
     *  @param p if null, all features, else only features of the given priority
     *  @return the count of the specified features
     */
    public int numFeatures(Priority p);

    /** Returns the total sizing of features broken out by priority.
     *  @param p if null, all features, else only features of the given priority
     *  @return the total of the sizings of the specified features
     */
    public double totalSizingOfFeatures(Priority p);

    /** Returns a descriptive label asosciated with this feature list
     *  (Used for reporting purposes).
     *  @return the descriptive (short) label
     */
    public String getLabel();
}
