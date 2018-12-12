package com.dtb.saesc.api.security.config;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PasswordEncoderTest {
	@Autowired
	PasswordEncoder encoder;
	private static final String ENCODED = "";
	private static final String PLAIN_PASSWD = "qwe123";
	
	@Test
	public void testEncode() {
		String encoded = encoder.encode(PLAIN_PASSWD);
		System.out.println(encoded);
		assertTrue(encoder.matches(PLAIN_PASSWD, encoded));
	}
}
