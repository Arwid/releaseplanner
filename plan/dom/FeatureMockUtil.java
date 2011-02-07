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

import static org.easymock.EasyMock.expect;

import org.easymock.classextension.IMocksControl;

/**
 * Assignment2
 * 
 * @author Arwid Bancewicz
 */
public class FeatureMockUtil {

	public static Feature createFeature(IMocksControl mockControl, Priority p) {
		return createFeature(mockControl, p, 1.0);
	}

	public static Feature createFeature(IMocksControl mockControl, Priority p,
			double s) {
		return createFeature(mockControl, p, 1.0, s);
	}

	public static Feature createFeature(IMocksControl mockControl, Priority p,
			double d, double s) {
		Feature feature = mockControl.createMock(Feature.class);

		expect(feature.getPriority()).andReturn(p).anyTimes();
		expect(feature.customerDesirability()).andReturn(d).anyTimes();
		expect(feature.getSizing()).andReturn(s).anyTimes();

		return feature;
	}
}
