package br.com.dcm.awt;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author Danilo Mendes
 * @date 27/07/2017
 * @class RobotUtil
 */
public class RobotUtil extends Robot {

	static {
		System.setProperty("java.awt.headless", "false");
	}

	public RobotUtil() throws AWTException {
		super();
		this.setAutoDelay(40);
		this.setAutoWaitForIdle(true);
		this.delay(1000);
	}

	/**
	 * @param phrase
	 * @description key the value of variable phrase
	 */
	public void type(String phrase) {
		type(phrase, 40);
	}

	public void type(String phrase, int delay) {
		byte[] bytes = phrase.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123) {
				code = code - 32;
			}
			this.delay(delay);
			this.keyPress(code);
			this.keyRelease(code);
		}
	}

	/**
	 * @param phrase
	 * @description key the value of variable phrase
	 */
	public void type(String phrase, boolean keyPressEnter) {
		byte[] bytes = phrase.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123) {
				code = code - 32;
			}
			this.delay(40);
			this.keyPress(code);
			this.keyRelease(code);
		}
		if (keyPressEnter) {
			this.keyEnter();
		}
	}

	/**
	 * @param i
	 * @description key the value of variable i
	 */
	public void type(int i) {
		this.delay(40);
		this.keyPress(i);
		this.keyRelease(i);
	}

	/**
	 * @param milliseconds
	 * @description waiting for milliseconds passed by parameter
	 */
	public void sleep(int milliseconds) {
		this.delay(milliseconds);
	}

	/**
	 * @param minutes
	 * @description waiting for minutes passed by parameter
	 */
	public void sleepInMinutes(int minutes) {
		try {
			Thread.sleep(minutes * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param x
	 * @param y
	 * @description move mouse to position x and y in pixel
	 */
	public void moveMouse(int x, int y) {
		this.mouseMove(x, y);
		sleep(1000);
	}

	/**
	 * @param x
	 * @param y
	 * @param mask
	 * @description move mouse to position x and y in pixel where mask is the button
	 *              mouse clicked
	 */
	public void click(int x, int y, int mask) {
		this.mouseMove(x, y);
		this.delay(1000);
		this.mousePress(mask);
		this.mouseRelease(mask);
	}

	/**
	 * @description double click with buttton left of mouse
	 */
	public void doubleClick() {
		this.mousePress(InputEvent.BUTTON1_MASK);
		this.delay(10);
		this.mouseRelease(InputEvent.BUTTON1_MASK);
		this.delay(10);
		this.mousePress(InputEvent.BUTTON1_MASK);
		this.delay(10);
		this.mouseRelease(InputEvent.BUTTON1_MASK);
		this.delay(10);
	}

	/**
	 * @description click with buttton left of mouse
	 */
	public void leftClick() {
		this.mousePress(InputEvent.BUTTON1_MASK);
		this.delay(200);
		this.mouseRelease(InputEvent.BUTTON1_MASK);
		this.delay(200);
	}

	/**
	 * @description click with buttton right of mouse
	 */
	public void rightClick() {
		this.mousePress(InputEvent.BUTTON3_MASK);
		this.delay(200);
		this.mouseRelease(InputEvent.BUTTON3_MASK);
		this.delay(200);
	}

	/**
	 * @description clouse the window active
	 */
	public void closeWindow() {
		this.keyPress(KeyEvent.VK_ALT);
		this.keyPress(KeyEvent.VK_F4);
		this.keyRelease(KeyEvent.VK_F4);
		this.keyRelease(KeyEvent.VK_ALT);
		this.sleep(500);
	}

	/**
	 * @description minimize all windows active
	 */
	public void minimizeAllWindows() {
		this.keyPress(KeyEvent.VK_WINDOWS);
		this.keyPress(KeyEvent.VK_M);
		this.keyRelease(KeyEvent.VK_M);
		this.keyRelease(KeyEvent.VK_WINDOWS);
	}

	/**
	 * @description minimize all windows active
	 */
	public void commandSave() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_S);
		this.keyRelease(KeyEvent.VK_S);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+E
	 */
	public void commandExecute() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_E);
		this.keyRelease(KeyEvent.VK_E);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description maxmize the window active
	 */
	public void maximizeWindow() {
		this.keyPress(KeyEvent.VK_WINDOWS);
		this.sleep(1000);
		this.keyPress(KeyEvent.VK_UP);
		this.keyRelease(KeyEvent.VK_UP);
		this.keyRelease(KeyEvent.VK_WINDOWS);
	}
	
	/**
	 * @description key Y+7
	 */
	public void keyYAnd7() {
		this.keyPress(KeyEvent.VK_Y);
		this.keyPress(KeyEvent.VK_7);
		this.keyRelease(KeyEvent.VK_Y);
		this.keyRelease(KeyEvent.VK_7);
	}
	
	/**
	 * @description key Y+8
	 */
	public void keyYAnd8() {
		this.keyPress(KeyEvent.VK_Y);
		this.keyPress(KeyEvent.VK_8);
		this.keyRelease(KeyEvent.VK_Y);
		this.keyRelease(KeyEvent.VK_8);
	}

	/**
	 * @param count
	 * @param keyEventCode
	 */
	public void loopKeyPress(int count, int keyEventCode) {
		int i = 0;
		while (i < count) {
			this.sleep(10);
			this.type(keyEventCode);
			i++;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @description Move mouse to position x and y in pixel and click button left of
	 *              mouse
	 */
	public void moveMouseAndClickLeft(int x, int y) {
		this.mouseMove(x, y);
		sleep(400);
		this.leftClick();
	}

	/**
	 * @param x
	 * @param y
	 * @description Move mouse to position x and y in pixel and click button right
	 *              of mouse
	 */
	public void moveMouseAndClickRight(int x, int y) {
		this.mouseMove(x, y);
		sleep(400);
		this.rightClick();
	}

	/**
	 * @param x
	 * @param y
	 * @description Move mouse to position x y in pixel and use double click in left
	 *              button of mouse
	 */
	public void moveMouseAndDoubleClick(int x, int y) {
		this.mouseMove(x, y);
		sleep(400);
		this.doubleClick();
	}

	/**
	 * @description key Enter
	 */
	public void keyEnter() {
		this.type(KeyEvent.VK_ENTER);
	}

	/**
	 * @description key LEFT
	 */
	public void keyLeft() {
		this.type(KeyEvent.VK_LEFT);
	}

	/**
	 * @description key RIGHT
	 */
	public void keyRight() {
		this.type(KeyEvent.VK_RIGHT);
	}

	/**
	 * @description key UP
	 */
	public void keyUP() {
		this.type(KeyEvent.VK_UP);
	}

	/**
	 * @description key DOWN
	 */
	public void keyDown() {
		this.type(KeyEvent.VK_DOWN);
	}

	/**
	 * @description key Enter
	 */
	public void keyEsc() {
		this.type(KeyEvent.VK_ESCAPE);
	}
	
	/**
	 * @description execute command Shift+TAB
	 */
	public void keyShiftTab() {
		this.keyPress(KeyEvent.VK_SHIFT);
		this.keyPress(KeyEvent.VK_TAB);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_TAB);
		this.keyRelease(KeyEvent.VK_SHIFT);
	}

	/**
	 * @description key TAB
	 */
	public void keyTab() {
		this.type(KeyEvent.VK_TAB);
	}
	
	/**
	 * @description execute command Shift+3 = #
	 */
	public void shiftVk_3() {
		this.keyPress(KeyEvent.VK_SHIFT);
		this.keyPress(KeyEvent.VK_3);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_3);
		this.keyRelease(KeyEvent.VK_SHIFT);
	}

	/**
	 * @description execute command Ctrl+Shift+E
	 */
	public void ctrlShiftVk_3() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_SHIFT);
		this.keyPress(KeyEvent.VK_3);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_3);
		this.keyRelease(KeyEvent.VK_SHIFT);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	/**
	 * @description execute command Ctrl+VK_HOME
	 */
	public void ctrlHome() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_HOME);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_HOME);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	/**
	 * @description execute command VK_PAGE_DOWNE
	 */
	public void ctrlPageDown() {
		this.keyPress(KeyEvent.VK_PAGE_DOWN);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}
	
	/**
	 * @description execute command VK_PAGE_UP
	 */
	public void ctrlPageUp() {
		this.keyPress(KeyEvent.VK_PAGE_UP);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_PAGE_UP);
	}

	/**
	 * @description execute command Ctrl+SHIFT+6
	 */
	public void ctrlShiftVk_6() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_SHIFT);
		this.keyPress(KeyEvent.VK_6);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_6);
		this.keyRelease(KeyEvent.VK_SHIFT);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+Shift+I
	 */
	public void ctrlShiftVk_I() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_SHIFT);
		this.keyPress(KeyEvent.VK_I);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_I);
		this.keyRelease(KeyEvent.VK_SHIFT);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+Shift+S
	 */
	public void ctrlShiftVk_S() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_SHIFT);
		this.keyPress(KeyEvent.VK_S);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_S);
		this.keyRelease(KeyEvent.VK_SHIFT);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+A
	 */
	public void ctrl_A() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_A);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_A);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+C
	 */
	public void ctrl_C() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_C);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_C);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	/**
	 * @description execute command Ctrl+O
	 */
	public void ctrl_O() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_O);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_O);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	/**
	 * @description execute command Ctrl+T
	 */
	public void ctrl_T() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_T);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_T);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+P
	 */
	public void ctrl_P() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_P);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_P);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+I
	 */
	public void ctrl_I() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_I);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_I);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+U
	 */
	public void ctrl_U() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_U);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_U);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+E
	 */
	public void ctrl_E() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_E);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_E);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+ESC
	 */
	public void ctrl_ESC() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_ESCAPE);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_ESCAPE);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+V
	 */
	public void ctrl_V() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_V);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_V);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Ctrl+S
	 */
	public void ctrl_S() {
		this.keyPress(KeyEvent.VK_CONTROL);
		this.keyPress(KeyEvent.VK_S);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_S);
		this.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * @description execute command Alt+A
	 */
	public void alt_A() {
		this.keyPress(KeyEvent.VK_ALT);
		this.keyPress(KeyEvent.VK_A);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_A);
		this.keyRelease(KeyEvent.VK_ALT);
	}
	
	/**
	 * @description execute command Alt
	 */
	public void alt() {
		this.keyPress(KeyEvent.VK_ALT);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_ALT);
	}

	/**
	 * @description execute command windows
	 */
	public void keyWindow() {
		this.keyPress(KeyEvent.VK_WINDOWS);
		this.keyRelease(KeyEvent.VK_WINDOWS);
	}

	/**
	 * @description click key home
	 */
	public void keyHome() {
		this.keyPress(KeyEvent.VK_HOME);
		this.sleep(100);
		this.keyRelease(KeyEvent.VK_HOME);
	}

	/**
	 * @description click VK_BACK_SPACE
	 */
	public void keyBackspace() {
		this.keyPress(KeyEvent.VK_BACK_SPACE);
		this.sleep(100);
		this.keyRelease(KeyEvent.VK_BACK_SPACE);
	}
	
	/**
	 * @description click VK_DELETE
	 */
	public void keyDelete() {
		this.keyPress(KeyEvent.VK_DELETE);
		this.sleep(100);
		this.keyRelease(KeyEvent.VK_DELETE);
	}

	/**
	 * @description execute command Ctrl+ESC
	 */
	public void shift_N() {
		this.keyPress(KeyEvent.VK_SHIFT);
		this.keyPress(KeyEvent.VK_N);
		this.sleep(600);
		this.keyRelease(KeyEvent.VK_N);
		this.keyRelease(KeyEvent.VK_SHIFT);
	}
	
	public void copyAndPast(String value) {
		// put path to your image in a clipboard
		StringSelection ss = new StringSelection(value);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		this.sleep(3000);
		this.ctrl_V();
		this.sleep(1000);
	}
	
	/**
	 * @description click VK_F
	 */
	public void keyF() {
		this.keyPress(KeyEvent.VK_F);
		this.sleep(100);
		this.keyRelease(KeyEvent.VK_F);
	}
	
	/**
	 * @description click VK_Y
	 */
	public void keyY() {
		this.keyPress(KeyEvent.VK_Y);
		this.sleep(100);
		this.keyRelease(KeyEvent.VK_Y);
	}
	
	/**
	 * @description click VK_8
	 */
	public void key8() {
		this.keyPress(KeyEvent.VK_8);
		this.sleep(100);
		this.keyRelease(KeyEvent.VK_8);
	}

}