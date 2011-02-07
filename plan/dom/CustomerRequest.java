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

/** The request of a customer for a feature. */
public class CustomerRequest {
    /** Create a customer request from customer "c" for feature "f" with desirability "d".
     *  @param f the feature being requested
     *  @param c the customer requesting the feature
     *  @param desirability the level of desire of the customer for the feature. Must be between 1 and 10 inclusive.
     */
    public CustomerRequest(Feature f, Customer c, int desirability) {
        if (desirability < 1 || desirability > 10)
            throw new Error("Assertion failure: desitability outside of range 1..10");
        f.addCustomerRequest(this);
        customer = c;
        this.desirability = desirability;
    }

    public int getDesirability() {
        return desirability;
    }

    public Customer getCustomer() {
        return customer;
    }

    private int desirability;
    private Customer customer;
}
