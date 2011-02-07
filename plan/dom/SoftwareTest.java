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

public class SoftwareTest {

	IMocksControl mockControl;
	Release releaseA;
	Release releaseB;
	Feature featureA;
	Feature featureB;
	Feature featureC;
	Feature featureD;

	@Before
	public void setUp() throws Exception {
		mockControl = createControl();
		featureA = FeatureMockUtil.createFeature(mockControl, Priority.high,
				0.0, 35);
		featureB = FeatureMockUtil.createFeature(mockControl, Priority.med,
				0.0, 6);
		featureC = FeatureMockUtil.createFeature(mockControl, Priority.med,
				3.0, 6);
		featureD = FeatureMockUtil.createFeature(mockControl, Priority.med,
				10.0, 6);

		releaseA = new Release();
		releaseA.addFeature(featureA);
		releaseB = new Release();
		releaseB.addFeature(featureA);
		releaseB.addFeature(featureC);
	}

	@Test
	public void testPlanRelease() {
		Software software = new Software("S1");
		assertTrue(software.planRelease(100).numFeatures(null) == 0);

		mockControl.replay();
		software.addFeature(featureA);
		assertTrue(software.planRelease(34).numFeatures(null) == 0);
		assertTrue(software.planRelease(35).numFeatures(null) == 1);
		mockControl.verify();
	}
}
