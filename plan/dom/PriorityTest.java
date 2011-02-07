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

public class PriorityTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCompareTo() {
		assertTrue(Priority.high.compareTo(Priority.high) == 0);
		assertTrue(Priority.high.compareTo(Priority.med) == 1);
		assertTrue(Priority.high.compareTo(Priority.low) == 1);

		assertTrue(Priority.med.compareTo(Priority.high) == -1);
		assertTrue(Priority.med.compareTo(Priority.med) == 0);
		assertTrue(Priority.med.compareTo(Priority.low) == 1);

		assertTrue(Priority.low.compareTo(Priority.high) == -1);
		assertTrue(Priority.low.compareTo(Priority.med) == -1);
		assertTrue(Priority.low.compareTo(Priority.low) == 0);
	}

	@Test
	public void testEqualsObject() {
		// assertTrue(!Priority.high.equals(null));
		assertTrue(Priority.high.equals(Priority.high));
		assertTrue(!Priority.high.equals(Priority.low));
		assertTrue(!Priority.high.equals(Priority.med));
		assertTrue(!Priority.med.equals(Priority.low));
	}

	@Test
	public void testToString() {
		assertTrue(Priority.high.toString().equals("high"));
		assertTrue(Priority.med.toString().equals("med"));
		assertTrue(Priority.low.toString().equals("low"));
	}

	@Test
	public void testGet() {
		Priority.get("high").equals(Priority.high);
		Priority.get("med").equals(Priority.med);
		Priority.get("low").equals(Priority.low);

		assertNull(Priority.get("zero"));
	}

}
