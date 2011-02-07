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

import java.io.FileNotFoundException;
import plan.dom.*;

/** A class used for testing other parts of the application.
 *  It only pretends to read from a file.
 */
public class TestInput implements FileInput {

    /** Generates test sets of objects depending on the parameter.
     *  If the parameter is "planetaria.xml", generates a test set cotnaining the product "Planetaria"
     *  and a bunch of features.<br>
     *  If the parameter is "random.xml", generates a test set containing randomly generated
     *  products and features. Product names are "S&lt;n&gt;".
     *  @param filename either "planetaria.xml" or "random.xml".
     *  @throws FileNotFoundException when none of the allowed filenames are presented
     *  @return a Company object rooting all the data from the test dataset.
     */
    public Company readFile(String filename) throws FileNotFoundException {
        if (filename.equals("planetaria.xml"))
            return readPlanetaria();
        else if (filename.equals("random.xml"))
            return readRandom();
        else
            throw new java.io.FileNotFoundException();
    }


    /** Generates a pre-determined set of test data.
     *  @return a Company object rooting all the data from the test dataset.
     */
    private Company readPlanetaria() {
        Company c;
        Feature f;
        Software s;

        c = new Company();

        s = c.lookupOrCreateSoftware("Planetaria");

        s.addFeature(f = new Feature(354, "show angular separation", 4.0, Priority.high));
        new CustomerRequest(f, c.lookupOrCreateCustomer("ATT"), 3);
        new CustomerRequest(f, c.lookupOrCreateCustomer("IBM"), 4);
        f.setChampion(c.lookupOrCreateEmployee("Dave"));

        s.addFeature(f = new Feature(304, "FOV indicators", 8.0, Priority.high));
        new CustomerRequest(f, c.lookupOrCreateCustomer("ATT"), 5);
        f.setChampion(c.lookupOrCreateEmployee("Britney"));

        s.addFeature(f = new Feature(234, "NGC/IC objects", 22.0, Priority.med));
        f.setChampion(c.lookupOrCreateEmployee("Dave"));

        s.addFeature(f = new Feature(389, "time simulation", 35.0, Priority.med));
        new CustomerRequest(f, c.lookupOrCreateCustomer("NASA"), 10);
        f.setChampion(c.lookupOrCreateEmployee("Stuart"));

        s.addFeature(f = new Feature(230, "change location", 5.0, Priority.high));
        new CustomerRequest(f, c.lookupOrCreateCustomer("NASA"), 5);
        f.setChampion(c.lookupOrCreateEmployee("Amanda"));
        return c;
    }



    /** Generates a randomly-determined set of test data.
     *  @return a Company object rooting all the data from the test dataset.
     */
    private Company readRandom() {
        final int N_SOFTWARE = 3;
        final int N_EMPLOYEE = 20;
        final int N_CUSTOMER = 100;
        final int N_FEATURE = 100;
        final int N_LOWP = 5;
        final int N_MEDP = 3;
        final int N_HIGHP = 1;
        final int N_PRIORITY = N_LOWP + N_MEDP + N_HIGHP;

        Company company = new Company();

        Software s[] = new Software[N_SOFTWARE];
        for(int i = 0; i < N_SOFTWARE; i++)
            s[i] = company.lookupOrCreateSoftware("S"+i);

        Employee e[] = new Employee[N_EMPLOYEE];
        for(int i = 0; i < N_EMPLOYEE; i++)
            e[i] = company.lookupOrCreateEmployee("E"+i);

        Customer c[] = new Customer[N_CUSTOMER];
        for(int i = 0; i < N_CUSTOMER; i++)
            c[i] = company.lookupOrCreateCustomer("C"+i);

        Priority p[] = new Priority[N_PRIORITY];
        for(int i = 0; i < N_PRIORITY; i++)
            if( i < N_HIGHP )
                p[i] = Priority.high;
            else if( i < N_HIGHP + N_MEDP )
                p[i] = Priority.med;
            else
                p[i] = Priority.low;

        for(int i = 0; i < N_FEATURE; i++) {
            Feature f = new Feature(i,"SHORT"+i,(double)randint(39)+1.0,p[randint(N_PRIORITY-1)]);
            f.setChampion(e[randint(N_EMPLOYEE-1)]);
            s[randint(N_SOFTWARE-1)].addFeature(f);
            for(int j = randint(5); j > 0; j--)
                new CustomerRequest(f,c[randint(N_CUSTOMER-1)],randint(9)+1);
        }

        return company;
    }


    /** Returns a random integer in the range 0..max drawn from a uniform distribution.
     *  @param max the largest number allowed to be returned
     *  @return a random integer between 0 and max inclusive
     */
    private int randint(int max) {
        double r  = Math.random()*(0.999999 + max);
        return (int)Math.floor(r);
    }
}
