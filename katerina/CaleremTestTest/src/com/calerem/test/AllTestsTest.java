/**
 * To run the suite from command line:
 * adb shell am instrument -w -e class com.calerem.test.AllTestsTest  
 *              com.calerem.test/android.test.InstrumentationTestRunne
 */

//XXX Still cant figure out how to pass extras.Works without
                    //String data = extras.getString("Data") 
	               //from ShowContactsHavingNamday.class

package com.calerem.test;

import junit.framework.Test;
import android.test.suitebuilder.TestSuiteBuilder; 

public class AllTestsTest {
	public static Test suite() {
		return new TestSuiteBuilder(AllTestsTest.class)
			.includeAllPackagesUnderHere()
			.build();
	}

}
