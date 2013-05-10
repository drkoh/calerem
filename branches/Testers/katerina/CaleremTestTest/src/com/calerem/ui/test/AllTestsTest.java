/**
 * To run the suite from command line:
 * adb shell am instrument -w -e class com.calerem.test.AllTestsTest  
 *              com.calerem.test/android.test.InstrumentationTestRunner
 *  Make sure emulator is running
 */

package com.calerem.ui.test;

import junit.framework.Test;
import android.test.suitebuilder.TestSuiteBuilder; 

public class AllTestsTest {
	public static Test suite() {
		return new TestSuiteBuilder(AllTestsTest.class)
			.includeAllPackagesUnderHere()
			.build();
	}

}
