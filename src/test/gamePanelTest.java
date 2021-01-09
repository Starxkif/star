package test;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

public class gamePanelTest {
	Robot robot=null;
	@Test
	public void test() {
		try {
			robot=new Robot();
			robot.mouseMove(300, 300);  
	        //按下和释放鼠标左键，选定工程  
	        robot.mousePress(KeyEvent.BUTTON1_MASK);  
	        robot.mouseRelease(KeyEvent.BUTTON1_MASK);  
	        Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyPress(KeyEvent.VK_RIGHT);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
