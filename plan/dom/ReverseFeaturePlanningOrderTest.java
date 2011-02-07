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

import static org.easymock.classextension.EasyMock.createControl;
import static org.junit.Assert.*;

import java.util.Comparator;

import org.easymock.classextension.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Assignment2
 * 
 * @author Arwid Bancewicz
 */
public class ReverseFeaturePlanningOrderTest {

	Comparator<Feature> comparator;
	Feature featureA;
	Feature featureB;
	Feature featureC;
	Feature featureD;
	IMocksControl mockControl;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		mockControl = createControl();
		comparator = ReverseFeaturePlanningOrder.get();
		featureA = FeatureMockUtil.createFeature(mockControl, Priority.high,
				5.0, 1.0);
		featureB = FeatureMockUtil.createFeature(mockControl, Priority.med,
				5.0, 2.0);
		featureC = FeatureMockUtil.createFeature(mockControl, Priority.med,
				4.0, 4.0);
		featureD = FeatureMockUtil.createFeature(mockControl, Priority.med,
				5.0, 4.0);
		mockControl.replay();
	}

	@After
	public void tearDown() throws Exception {
		mockControl.verify();
	}

	@Test
	public void testCompare() {
		assertTrue(comparator.compare(featureA, featureA) == 0);
		assertTrue(comparator.compare(featureA, featureB) == -1);
		assertTrue(comparator.compare(featureB, featureC) == -1);
		assertTrue(comparator.compare(featureB, featureD) == -1);
	}

	@Test
	public void testEquals() {
		assertTrue(((ReverseFeaturePlanningOrder) comparator).equals(featureA,
				featureA));
		assertTrue(!((ReverseFeaturePlanningOrder) comparator).equals(featureA,
				featureB));
	}

}
