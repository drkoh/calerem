package com.calerem.tests.test;

import android.test.suitebuilder.TestSuiteBuilder;
import junit.framework.Test;
/**
 * To run the suite from command line:
 * adb shell am instrument -w -e class com.calerem.test.AllTests  
 *              com.calerem.test/android.test.InstrumentationTestRunner
 *  Make sure emulator is running
 */
public class AllTests {
	public static Test suite() {
		return new TestSuiteBuilder(AllTests.class)
			.includeAllPackagesUnderHere()
			.build();
	}
}