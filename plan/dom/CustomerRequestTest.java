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

import static org.easymock.classextension.EasyMock.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Assignment2
 * 
 * @author Arwid Bancewicz
 */
public class CustomerRequestTest {

	Customer customer;
	Feature feature;

	@Before
	public void setUp() throws Exception {
		// create mocks for the collaborating objects
		feature = createMock(Feature.class);
		customer = createMock(Customer.class);
	}

	@Test
	public void testCustomerRequest() {
		new CustomerRequest(feature, customer, 1);
		new CustomerRequest(feature, customer, 10);
		new CustomerRequest(feature, null, 5);
	}

	@Test(expected = Error.class)
	public void testCustomerRequestLowerBoundary() {
		new CustomerRequest(feature, customer, 0);
	}

	@Test(expected = Error.class)
	public void testCustomerRequestUpperBoundary() {
		new CustomerRequest(feature, customer, 11);
	}

}
