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

import static org.junit.Assert.*;
import static org.easymock.classextension.EasyMock.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Assignment2
 * 
 * @author Arwid Bancewicz
 */
public class FeatureTest {

	Feature feature;
	CustomerRequest customerRequestA;
	CustomerRequest customerRequestB;

	@Before
	public void setUp() throws Exception {
		feature = new Feature(101, "F1", 0.0, Priority.high);
		// create mocks for the collaborating objects
		customerRequestA = createMock(CustomerRequest.class);
		expect(customerRequestA.getDesirability()).andReturn(2);
		customerRequestB = createMock(CustomerRequest.class);
		expect(customerRequestB.getDesirability()).andReturn(5);
	}

	@Test
	public void testCustomerDesirabilityNone() {
		assertTrue(feature.customerDesirability() == 0);
	}

	@Test
	public void testCustomerDesirabilityMultiple() {
		replay(customerRequestA);
		replay(customerRequestB);
		feature.addCustomerRequest(customerRequestA);
		feature.addCustomerRequest(customerRequestB);
		assertTrue(feature.customerDesirability() == 7);
		verify(customerRequestA);
		verify(customerRequestB);
	}

}
