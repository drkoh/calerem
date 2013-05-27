/**
 * 
 */
package com.calerem.tests.api;

import com.calerem.api.SMSAPI;

import junit.framework.TestCase;

/**
 * SMSAPI Tests in JUnit 3.
 * @author DarkParadise
 */
public class SMSAPITest extends TestCase {

	/**
	 * Test method for {@link com.calerem.api.SMSAPI#sendSMS(java.lang.String, java.lang.String)}.
	 */
	public void testSendSMS() {
		assertTrue(new SMSAPI().sendSMS("6948579123", "My Text"));
	}

}
