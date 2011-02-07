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
 
package plan.input;

import java.io.*;
import plan.dom.*;

/** Interface for reading input data from a file */
public interface FileInput {
    /** Read objects from the indicated file.
     *  @param filenane the pathname of the file to read
     *  @throws FileNotFoundException when the file cannot be found
     *  @throws IOException when the file cannot be read
     *  @return a Company root object to which all others are connected
     */
    public Company readFile(String filename) throws FileNotFoundException, IOException;
}
