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

import org.easymock.classextension.IMocksControl;
import org.junit.Before;
import org.junit.Test;

public class ReleaseTest {

	IMocksControl mockControl;
	Release release;

	@Before
	public void setUp() throws Exception {
		release = new Release();
		mockControl = createControl();
	}

	@Test
	public void testNumFeatures() {
		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.high));
		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.high));
		int expectedNumFeaturesHigh = 2;

		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med));
		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med));
		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med));
		int expectedNumFeaturesMed = 3;

		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.low));
		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.low));
		int expectedNumFeaturesLow = 2;

		int expectedNumFeaturesAll = 7;

		mockControl.replay();
		assertTrue(release.numFeatures(Priority.high) == expectedNumFeaturesHigh);
		assertTrue(release.numFeatures(Priority.med) == expectedNumFeaturesMed);
		assertTrue(release.numFeatures(Priority.low) == expectedNumFeaturesLow);
		assertTrue(release.numFeatures(null) == expectedNumFeaturesAll);
		mockControl.verify();
	}

	@Test
	public void testNumFeaturesEmpty() {
		assertTrue(release.numFeatures(Priority.high) == 0);
		assertTrue(release.numFeatures(Priority.med) == 0);
		assertTrue(release.numFeatures(Priority.low) == 0);
		assertTrue(release.numFeatures(null) == 0);
	}

	@Test
	public void testTotalSizingOfFeaturesEmpty() {
		assertTrue(release.totalSizingOfFeatures(Priority.high) == 0.0);
		assertTrue(release.totalSizingOfFeatures(Priority.med) == 0.0);
		assertTrue(release.totalSizingOfFeatures(Priority.low) == 0.0);
		assertTrue(release.totalSizingOfFeatures(null) == 0.0);
	}

	@Test
	public void testTotalSizingOfFeaturesMany() {

		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.high, 5.0));
		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.high, 22.0));
		double expectedSizingHigh = 27.0;

		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med, 1.0));
		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.med, 3.0));
		double expectedSizingMed = 4.0;

		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.low, 33.0));
		release.addFeature(FeatureMockUtil.createFeature(mockControl,
				Priority.low, 4.0));
		double expectedSizingLow = 37.0;

		double expectedSizingAll = expectedSizingHigh + expectedSizingMed
				+ expectedSizingLow;

		mockControl.replay();
		assertTrue(release.totalSizingOfFeatures(Priority.high) == expectedSizingHigh);
		assertTrue(release.totalSizingOfFeatures(Priority.med) == expectedSizingMed);
		assertTrue(release.totalSizingOfFeatures(Priority.low) == expectedSizingLow);
		assertTrue(release.totalSizingOfFeatures(null) == expectedSizingAll);
		mockControl.verify();
	}
}
