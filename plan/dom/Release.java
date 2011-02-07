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

/** Represents a possible release of a software product. */
public class Release extends DefaultFeatureListImplementation implements FeatureList {
    /**
     * Removes features from this release that appear in the other release.
     *  @param other the release containing features to be removed
     */
    public void subtract(Release other) {
        super.subtract(other);
    }

    public String getDesignator(){
            return designator;
    }

    public void setDesignator(String designator){
            this.designator = designator;
    }

    /** The FeatureList label is the same as the release designator. */
    public String getLabel() {
        return designator;
    }

    private String designator;
}
