package wxrobot.https.test;

import org.junit.Test;

import com.xll.util.PicBootstrap;

public class PicTest {
	
	@Test
	public void picTest(){
		Thread t = new Thread(new PicBootstrap());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
