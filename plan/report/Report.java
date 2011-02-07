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
 
package plan.report;

import java.util.*;
import plan.dom.*;

/**
 * Utility class for printing text-oriented reports to standard output.
 */
public class Report {
    /** Entirely static class. Ensure no instances are created. */
    private Report() { }

    // Old FORTRAN trick gives a picture of the field-widths.
    static final private String LINE = "=============================================================================";
    static final private String PICT = "iiiii dddddddddddddddddddddddddd pppp ssss hhhhhhhhhh ccccccccccccccccccccccc";
    static final private String HEAD = "id    short description          prio size champion   customers";
    static final private int WIDTH_ID    = PICT.lastIndexOf("i") - PICT.indexOf("i") + 1;
    static final private int WIDTH_DESC  = PICT.lastIndexOf("d") - PICT.indexOf("d") + 1;
    static final private int WIDTH_PRIO  = PICT.lastIndexOf("p") - PICT.indexOf("p") + 1;
    static final private int WIDTH_SIZE  = PICT.lastIndexOf("s") - PICT.indexOf("s") + 1;
    static final private int WIDTH_CHAMP = PICT.lastIndexOf("h") - PICT.indexOf("h") + 1;
    static final private int WIDTH_CUST  = PICT.lastIndexOf("c") - PICT.indexOf("c") + 1;

    /** Write the report header.
     *  @param head the header message to be printed
     */
    static public void writeHeader(String head) {
        System.out.println(LINE);
        System.out.println(head);
	System.out.print("Report generated at: "); System.out.println(new Date().toString());
        System.out.println(LINE);
    }

    /** Write a table summarizing the number of features and their sizings.
     *  The table has the feature lists along the columns and four rows:
     *  <ul>
     *       <li>high priority features
     *       <li>medium priority features
     *       <li>low priority features
     *       <li>all features
     *  </ul>
     *  Each entry in the table gives the number of features and the sum of the sizings.
     *  @param fl the variouos FeatureLists comprising the columns
     */
    static public void writeSummaryTable(FeatureList[] fl) {
        final int ROW_LABEL_WIDTH = 8;
        final int COL_WIDTH = (80 - ROW_LABEL_WIDTH) / 3;

	// print summar headers
        System.out.println("Summary");
        System.out.println("num_features / total_sizing:");

	// print column-label row
        writeField("", ROW_LABEL_WIDTH);
        for(int i = 0; i < fl.length; i++)
            writeField(fl[i].getLabel(), COL_WIDTH);
        System.out.println();

	// print each of the 4 other rows
        final Priority[] pr = {Priority.high,Priority.med,Priority.low,null};
        for (int p = 0; p < pr.length; p++) {
            writeField((pr[p] == null ? "TOTAL" : pr[p].toString()), ROW_LABEL_WIDTH);
            for (int i = 0; i < 3; i++) {
                String field = "" + fl[i].numFeatures(pr[p]) + "/" + fl[i].totalSizingOfFeatures(pr[p]);
                writeField(field, COL_WIDTH);
            }
            System.out.println();
        }

	// terminate this section of the report with a line
        System.out.println(LINE);
    }


    /** Write out details for all the features in the specified FeatureList.
     *  @param title a title for this section of the report
     *  @param fl the list of features to print
     */
    static public void writeFeatures(String title, FeatureList fl) {
        System.out.println(title);
        System.out.println(HEAD);
        Iterator i = fl.featureIterator();
        if (i.hasNext())
            while (i.hasNext())
                writeFeature((Feature)i.next());
        else
            System.out.println("<NO FEATURES>");
        System.out.println(LINE);
    }


    /** Write details for the given feature.
     *  @param f the feature to print
     */
    static void writeFeature(Feature f) {
        writeField("" + f.getId(), WIDTH_ID);
        System.out.print(" ");
        writeField(f.getShortDescription(), WIDTH_DESC);
        System.out.print(" ");
        writeField(f.getPriority().toString(), WIDTH_PRIO);
        System.out.print(" ");
        writeField("" + f.getSizing(), WIDTH_SIZE);
        System.out.print(" ");
        writeField(f.getChampion().getName(), WIDTH_CHAMP);
        System.out.print(" ");
        writeField(customerDesirability(f), WIDTH_CUST);
        System.out.println();
    }


    /** Write the given string in a filed of the given width.
     *  If the length of the string is bigger than the field width,
     *  replace the end of the string by a "...".
     *  @param s the string to print in the field
     *  @param width the field width
     */
    static void writeField(String s, int width) {
        if (s.length() > width)
            s = s.substring(0, width - 3).concat("...");
        int nBlanks = width - s.length();
        while (nBlanks-- > 0) s += " ";
        System.out.print(s);
    }


    /** Return a string that expresses the desirability
     *  by customers of the specified feature.
     *  @param f the features whose customer desirability is to be returned
     *  @return a textual expression of the desirability
     */
    static String customerDesirability(Feature f) {
	// start with the numerical rating
        String s = "" + f.customerDesirability();

	// add a comma-separated list of the names of desirous customers
	// along with their level of desire.
        Iterator i = f.customerRequestIterator();
        if (i.hasNext()) {
            s += " [";
            while (i.hasNext()) {
                CustomerRequest cr = (CustomerRequest)i.next();
                s += cr.getCustomer().getName() + "(" + cr.getDesirability() + ")";
                if (i.hasNext())
                    s += ",";
            }
            s += "]";
        } else
            s += " [<none>]";
        return s;
    }
}
