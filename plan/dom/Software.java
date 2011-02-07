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

public class Software extends DefaultFeatureListImplementation implements FeatureList {
    Software(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** Suggests a release of this software product.
     *  @param capacity the number of person-days of coding effort available to work on the release
     *  @return a Release containing a suggested list of features
     */
    public Release planRelease(double capacity) {
        double inplan = 0.0;
        sortFeatures(ReverseFeaturePlanningOrder.get());
        Release r = new Release();
        for (Iterator i = featureIterator(); i.hasNext(); ) {
            Feature f = (Feature)i.next();
            if (inplan + f.getSizing() <= capacity) {
                r.addFeature(f);
                inplan += f.getSizing();
            }
        }
        return r;
    }

    /** The FeatureList label is the name of the Software product. */
    public String getLabel() {
        return name;
    }

    private String name;
}
