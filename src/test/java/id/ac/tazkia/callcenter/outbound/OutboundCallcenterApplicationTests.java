package id.ac.tazkia.callcenter.outbound;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutboundCallcenterApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void encodePassword() {
		System.out.println("123 : ["+passwordEncoder.encode("123")+"]");
		System.out.println("1234 : ["+passwordEncoder.encode("123")+"]");
	}

}
