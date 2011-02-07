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

import org.junit.Before;
import org.junit.Test;

/**
 * Assignment2
 * 
 * @author Arwid Bancewicz
 */
public class CompanyTest {

	Company company;

	@Before
	public void setUp() throws Exception {
		company = new Company();
	}

	@Test
	public void testGetSoftware() {
		assertNull(company.getSoftware("Bryan"));
		company.lookupOrCreateSoftware("Bryan");
		assertNotNull(company.getSoftware("Bryan"));
	}

	@Test
	public void testLookupOrCreateCustomer() {
		Customer customerA = company.lookupOrCreateCustomer("Bryan");
		assertNotNull(customerA);
		Customer customerB = company.lookupOrCreateCustomer("Bryan");
		assertNotNull(customerB);
		assertEquals(customerA, customerB);
	}

	@Test
	public void testLookupOrCreateEmployee() {
		Employee employeeA = company.lookupOrCreateEmployee("Bryan");
		assertNotNull(employeeA);
		Employee employeeB = company.lookupOrCreateEmployee("Bryan");
		assertNotNull(employeeB);
		assertEquals(employeeA, employeeB);
	}

	@Test
	public void testLookupOrCreateSoftware() {
		Software softwareA = company.lookupOrCreateSoftware("Bryan");
		assertNotNull(softwareA);
		Software softwareB = company.lookupOrCreateSoftware("Bryan");
		assertNotNull(softwareB);
		assertEquals(softwareA, softwareB);
	}

}
