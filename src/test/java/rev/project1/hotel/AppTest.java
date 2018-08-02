package rev.project1.hotel;

import java.sql.Connection;

import org.junit.Test;

import com.revature.dao.InquiryDao;
import com.revature.util.ConnectionUtil;

//import junit.framework.Test;
import junit.framework.TestCase;

public class AppTest extends TestCase {
	@Test
	public void testConnection() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
		} catch (Exception ex) {
			assertTrue(false); // if any exception is caught, test fails
		} 
		System.out.print("connected");
		assertTrue(true);
	}
	
	@Test
	public void testGetInqId() {
		if(3 == InquiryDao.getInqByParts(1,  "dummy", "dummy")) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}
	
	public void testgetpendRes() {
		
	}
}
