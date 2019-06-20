package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {
	@Resource(name="collectionBean")
	private IocCollection iocCollection;
	
	@Test
	public void collectionListTest() {
		/***Given***/ 
		
		/***When***/
		List<String> list = iocCollection.getList();
		
		/***Then***/
		assertNotNull(list);
		assertEquals(3, list.size());
	}
	
	@Test
	public void collectionMapTest() {
		/***Given***/

		/***When***/
		Map<String, String> map = iocCollection.getMap();
		
		/***Then***/
		assertNotNull(map);
		assertEquals("brown", map.get("name"));
	}
	
	@Test
	public void collectionSetTest() {
		/***Given***/

		/***When***/
		Set<String> set = iocCollection.getSet();
		
		/***Then***/
		assertNotNull(set);
		assertTrue(set.contains("sally"));
	}
	
	@Test
	public void collectionPropertiesTest() {
		/***Given***/

		/***When***/
		Properties properties = iocCollection.getProperties();
		
		/***Then***/
		assertNotNull(properties);
		assertEquals("브라운", properties.get("name"));
	}
 
}
