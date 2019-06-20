package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
public class SpringPlaceHolderTest {

	@Resource(name="dbInfo")
	private DbInfo dbInfo;
	
	@Test
	public void dbInfoTest() {
		/***Given***/

		/***When***/
		String driver = dbInfo.getDriver();
		String url = dbInfo.getUrl();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPassword();
		
		/***Then***/
		assertNotNull(driver);
		assertNotNull(url);
		assertNotNull(username);
		assertNotNull(password);
		
		assertEquals("oracle.jdbc.driver.OracleDriver", driver);
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", url);
		assertEquals("PC23", username);
		assertEquals("java", password);
	}

}
