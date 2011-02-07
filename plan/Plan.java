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

package plan;

import plan.input.*;
import plan.report.*;
import plan.dom.*;

/** This class is used to parse the command line and drive the program. */
public class Plan {
    /** This class is entirely static. Ensure no objects of this class get create. */
    private Plan() { }

    /** Features fitting under this ratio of resourcing are considered "just-missed". */
    static private final double JUST_MISSED_RATIO = 1.5;

    /** Main program.
     *  Expects three arguments:<br>
     *  <code>  &lt;feature_file.xml&gt; &lt;product_name&gt; &lt;person-days-available&gt;</code><br>
     *  Any errors result in an error message and a usage message to stderr
     *  and termination with an exit code of -1.
     *  Correct execution will result in an exit code of 0.
     */
    public static void main(String[] argv) {
	// get data from test scaffolding
	// The real XML file reader is being worked on now. 
	FileInput reader = new TestInput();

	// check for correct number of arguments
        if (argv.length != 3)
            dieUsage("wrong number of arguments");

	// check for non-null filename
        String filename = argv[0];
        if (filename == null || filename.equals(""))
            dieUsage("empty filename specified");

	// check for non-null osftware product
        String software = argv[1];
        if (software == null || software.equals(""))
            dieUsage("empty software name specified");

	// parse capacity, checking for errors
        String capacityStr = argv[2];
        double capacity = 0.0;
        try {
            capacity = Double.parseDouble(capacityStr);
        } catch (NumberFormatException e) {
            dieUsage("Illegal capacity specified, '" + capacityStr + "'");
        }
        if (capacity <= 0)
            dieUsage("Capacity specified, '" + capacityStr + "' must be be greater than 0");

	// read the XML feature file.  Returns the root "Company" object from the file.
        Company c = null;
        try {
            c = reader.readFile(filename);
        } catch (java.io.FileNotFoundException e) {
            dieUsage("File not found, '" + filename + "'");
        } catch (java.io.IOException e) {
            dieUsage("IO error while opening file '" + filename + "'");
        }

	// check that software product specified on the command line exists in the file
	Software s = c.getSoftware(software);
        if( s == null )
            dieUsage("Specified software product not found, '" + software + "'");

	// plan a release given the capacity in person-days specified on the command line
        Release planned = s.planRelease(capacity);
        planned.setDesignator("in-plan");

	// plan a second release given the extra capacity and subtract the features from
	// the above rleease, leaving just the excess.
        Release overflow = s.planRelease(capacity * JUST_MISSED_RATIO);
        overflow.subtract(planned);
        overflow.setDesignator("just-missed");

	// write the report header, echoing the comamnd line
        Report.writeHeader("File: " + filename + "    Product: " + software + "    Capacity: " + capacityStr);

	// write a summary chart for all the features, the in-plan features, and the just-missed features
        final FeatureList[] featureListsToSummarize = {s, planned, overflow};
        Report.writeSummaryTable(featureListsToSummarize);

	// write details for the in-plan features
        Report.writeFeatures("In-Plan Features for sizing " + capacityStr, planned);

	// write details for the just-missed features
        Report.writeFeatures("Just-Missed Features under sizing " + capacity * JUST_MISSED_RATIO, overflow);

	// all went well
        System.exit(0);
    }

    /** Print an error message and a usage message to stderr, and die with exit code -1
     *  @param msg the error message to print
     */
    private static void dieUsage(String msg) {
        System.err.println("Error: " + msg);
        System.err.println("Usage: java Plan filename productname capacity");
        System.exit(1);
    }
}
