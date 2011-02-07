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

import org.easymock.classextension.IMocksControl;
import org.junit.Before;
import org.junit.Test;

/**
 * Assignment2
 * 
 * @author Arwid Bancewicz
 */
public class DefaultFeatureListImplementationTest {

	IMocksControl mockControl;
	DefaultFeatureListImplementation featureListImpl;

	private class testFeatureListImpl extends DefaultFeatureListImplementation {
		@Override
		public String getLabel() {
			return null;
		}
	}

	@Before
	public void setUp() throws Exception {
		mockControl = createControl();
		featureListImpl = new testFeatureListImpl();
	}

	@Test
	public void testNumFeatures() {
		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.high));
		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.high));
		int expectedNumFeaturesHigh = 2;

		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med));
		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med));
		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med));
		int expectedNumFeaturesMed = 3;

		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.low));
		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.low));
		int expectedNumFeaturesLow = 2;

		int expectedNumFeaturesAll = 7;

		mockControl.replay();
		assertTrue(featureListImpl.numFeatures(Priority.high) == expectedNumFeaturesHigh);
		assertTrue(featureListImpl.numFeatures(Priority.med) == expectedNumFeaturesMed);
		assertTrue(featureListImpl.numFeatures(Priority.low) == expectedNumFeaturesLow);
		assertTrue(featureListImpl.numFeatures(null) == expectedNumFeaturesAll);
		mockControl.verify();
	}

	@Test
	public void testNumFeaturesEmpty() {
		assertTrue(featureListImpl.numFeatures(Priority.high) == 0);
		assertTrue(featureListImpl.numFeatures(Priority.med) == 0);
		assertTrue(featureListImpl.numFeatures(Priority.low) == 0);
		assertTrue(featureListImpl.numFeatures(null) == 0);
	}

	@Test
	public void testTotalSizingOfFeaturesEmpty() {
		assertTrue(featureListImpl.totalSizingOfFeatures(Priority.high) == 0.0);
		assertTrue(featureListImpl.totalSizingOfFeatures(Priority.med) == 0.0);
		assertTrue(featureListImpl.totalSizingOfFeatures(Priority.low) == 0.0);
		assertTrue(featureListImpl.totalSizingOfFeatures(null) == 0.0);
	}

	@Test
	public void testTotalSizingOfFeaturesMany() {

		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.high, 5.0));
		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.high, 22.0));
		double expectedSizingHigh = 27.0;

		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med, 1.0));
		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med, 3.0));
		double expectedSizingMed = 4.0;

		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.low, 33.0));
		featureListImpl.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.low, 4.0));
		double expectedSizingLow = 37.0;

		double expectedSizingAll = expectedSizingHigh + expectedSizingMed
				+ expectedSizingLow;

		mockControl.replay();
		assertTrue(featureListImpl.totalSizingOfFeatures(Priority.high) == expectedSizingHigh);
		assertTrue(featureListImpl.totalSizingOfFeatures(Priority.med) == expectedSizingMed);
		assertTrue(featureListImpl.totalSizingOfFeatures(Priority.low) == expectedSizingLow);
		assertTrue(featureListImpl.totalSizingOfFeatures(null) == expectedSizingAll);
		mockControl.verify();
	}
}
