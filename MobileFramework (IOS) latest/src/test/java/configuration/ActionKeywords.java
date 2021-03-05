package configuration;

import static executionEngine.TestMain.OR;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utility.ReportGenerator;


public class ActionKeywords {

	public static AndroidDriver<AndroidElement> driver;
	public static IOSDriver<IOSElement> iosDriver;
	public static Logger Log=LogManager.getLogger(ActionKeywords.class);
	AppiumDriverLocalService service;     
	public static void openApp(String object, String Value) throws Exception {
		
				
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\utility\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String DeviceName = (String) prop.get("DeviceName");
		String PlatformName = (String) prop.get("PlatformName");
		String AUTOMATION_NAME = (String) prop.get("AUTOMATION_NAME");
		String udid = (String) prop.get("UDID");
		String PACKAGE = (String) prop.get("PACKAGE");
		String ACTIVITY = (String) prop.get("ACTIVITY");
	    //String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo5MDc0NTc1LCJ4cC5wIjo5MDc0NTc0LCJ4cC5tIjoxNTk3MTUwNDM5NjUxLCJleHAiOjE5MTI2NTU4MDYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.85mTNMqmwim3TKGqPC-AV9iTVyUZJoP1bWzKzrVdJXM";
		
		DesiredCapabilities cap = new DesiredCapabilities();
		Log.info("ObjectProperty : " + object + " " + Value);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		System.out.println("--------- Connecting to the Android device ----------");
		cap.setCapability(MobileCapabilityType.UDID, udid);// Samsung 9
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, PlatformName);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
		//cap.setCapability("accessKey", accessKey);
	    //cap.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
		cap.setCapability("autoAcceptAlerts", true);
		//cap.setCapability("noReset",true);
		
		// Disabling screen keyboard
		cap.setCapability("unicodeKeyboard", true);
		

		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PACKAGE);

		// new national APP_ACTIVITY-
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,ACTIVITY);

		System.out.println("------ Initializing Appium Settings -------");
		
		
		try {

			System.out.println("************* Opening Application ************");

			driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
			//driver = new AndroidDriver<AndroidElement>(new URL("https://cloud.seetest.io/wd/hub"), cap);
			TestSupport.test_timeout(10);

		} catch (Exception e) {
			Log.error("############## Failed to Open App ##############");
			Constants.TestCaseStatus = false;
			 //e.printStackTrace();
		}

		return;

	}
	public static void openApp_IOS(String object, String Value) throws Exception {
		
		
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\utility\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		//String DeviceName = (String) prop.get("DeviceName");
		//String PlatformName = (String) prop.get("PlatformName");
		//String AUTOMATION_NAME = (String) prop.get("AUTOMATION_NAME");
		//String udid = (String) prop.get("UDID");
		//String PACKAGE = (String) prop.get("PACKAGE");
		//String ACTIVITY = (String) prop.get("ACTIVITY");
	    String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo5MDg5MjM4LCJ4cC5wIjo5MDg5MjM3LCJ4cC5tIjoxNTk3MzE5Njg3MzY5LCJleHAiOjE5MTI3ODgzODYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.AkVT7JqdAKGVNmSWzpnzBanRJtrUNSVnZwbbTTgMupA";
		
		DesiredCapabilities cap = new DesiredCapabilities();
		Log.info("ObjectProperty : " + object + " " + Value);
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		System.out.println("--------- Connecting to the Android device ----------");
		//cap.setCapability(MobileCapabilityType.UDID, udid);// Samsung 9
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		cap.setCapability("accessKey", accessKey);
		cap.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
		cap.setCapability(MobileCapabilityType.APP, "cloud:com.aaa.iphone.discounts.enterprise");
        cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.aaa.iphone.discounts.enterprise");
		cap.setCapability("autoAcceptAlerts", true);
		
		// Disabling screen keyboard
		cap.setCapability("unicodeKeyboard", true);

		//cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PACKAGE);

		// new national APP_ACTIVITY-
		//cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,ACTIVITY);

		System.out.println("------ Initializing Appium Settings -------");
		
		
		try {

			System.out.println("************* Opening Application ************");

			//driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
			//driver = new AndroidDriver<AndroidElement>(new URL("https://cloud.seetest.io/wd/hub"), cap);
			iosDriver = new IOSDriver<IOSElement>(new URL("https://cloud.seetest.io/wd/hub"), cap);
			//TestSupport.test_timeout(10);
			Thread.sleep(5000);

		} catch (Exception e) {
			Log.error("############## Failed to Open App ##############");
			Constants.TestCaseStatus = false;
			 e.printStackTrace();
		}

		return;

	}
	//check which context you have
	
	public static void print_context(String Object, String Value) {
		

		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		    System.out.println(contextName); 
		    //prints out something like NATIVE_APP \n WEBVIEW_1
		}
		
	}
	//method- for changing web view to Native view
	public static boolean switch_NativeContext(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(8000);
     		driver.context("NATIVE_APP");
			System.out.println("-------Switched to native view--------" );
			Thread.sleep( 3000);
			}
		 catch (Exception e) {
			Log.error("### Failed to switch in native view ###");
		}
		
		return stepSuccessful;
	}
	
	//method- for changing native view to  web view
	public static boolean switch_Webcontext(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(10000);

		driver.context("WEBVIEW_com.aaa.android.discounts");
			System.out.println("-------Switched to web view--------" );
			Thread.sleep( 3000);
			
			}
		 catch (Exception e) {
			Log.error("### Failed to switch in web view ###");
		}
	 return stepSuccessful;
	}

 
	//method- use to click on android elements
	public static boolean click(String Object, String Value) throws InterruptedException {

		boolean stepSuccessful = false;
		try{	
			Thread.sleep(3000);
			Log.info("----------- Clicking on " + Object + " button/link ------------");
			AndroidElement ele = driver.findElement(By.xpath(OR.getProperty(Object)));
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();  
			}
		 catch (Exception e) {
			Log.error("############ Failed to click :" + Object + "############");
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	//method- use to click on android elements
		public static boolean click_Afterwait(String Object, String Value) throws InterruptedException {

			boolean stepSuccessful = false;
			try{	
				Thread.sleep(12000);
				Log.info("----------- Clicking on " + Object + " button/link ------------");
				AndroidElement ele = driver.findElement(By.xpath(OR.getProperty(Object)));
				WebDriverWait wait = new WebDriverWait(driver,60);
				wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
				Thread.sleep(1000);
				}
			 catch (Exception e) {
				Log.error("############ Failed to click :" + Object + "############");
				Constants.TestCaseStatus = false;
			}
			return stepSuccessful;
		}
	//method- use to click on android elements
		public static boolean click_ifDisplayed(String Object, String Value) throws InterruptedException {

			boolean stepSuccessful = false;
			try{	
				Thread.sleep(2000);
				Log.info("----------- Clicking if " + Object + " is visible ------------");
				AndroidElement ele = driver.findElement(By.xpath(OR.getProperty(Object)));
				WebDriverWait wait = new WebDriverWait(driver,60);
				wait.until(ExpectedConditions.visibilityOf(ele)).click();  
				}
			 catch (Exception e) {
				Log.error("----- Elemnt not present :" + Object + "------");
				Constants.TestCaseStatus = true;
			}
			return stepSuccessful;
		}
	
	
	//method- use to click on web elements
	public static boolean click_on_webElement(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(20000);
			
			WebElement element = driver.findElement(By.xpath(OR.getProperty(Object)));
			element.click();
			System.out.println("clicked on the continue button");
			
			Thread.sleep( 3000);
				
			}
		 catch (Exception e) {
			Log.error("##### Click Failed ######");
		}
		Thread.sleep(3000);
		return stepSuccessful;
	}

	//method- added additional time to wait before click
	public static boolean clickAndWait(String Object, String Value) throws InterruptedException {

		boolean stepSuccessful = false;
		try {
			
		    Thread.sleep(5000);
			Log.info("----------- Clicked on " + Object + " button/link ------------");
			AndroidElement ele = driver.findElement(By.xpath("//*[@resource-id='continue' and @text='CONTINUE']"));
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
			} catch (Exception e) {
			Log.error("############ Failed to click :" + Object + "############");
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	

	// method-scroll and click on link	
	public static boolean scrollAndClick(String Object, String Value) {

		boolean stepSuccessful = false;
		try {
			Thread.sleep(3000);
			Log.info("------------ Scrolling down and click on " + Object + "------------------");

			MobileElement element = driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ Object + "\").instance(0))");
			element.click();
			Thread.sleep(3000);

		} catch (Exception e) {
			Log.error("########### Failed to scroll and click ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;

		}
		return stepSuccessful;
	}

	// method for swap down of the screen
	public static boolean swapDown(String Object, String Value) {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(3000);
			Log.info("---------- Swap down " + Object + "-------------");
			new TouchAction(driver).press(PointOption.point(1250, 2600)).waitAction().moveTo(PointOption.point(550, 10))
					.release().perform();
		} catch (Exception e) {
			Log.error("########### Failed to swap Down ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	
	
	public static boolean swapDown_LG(String Object, String Value) {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(3000);
			Log.info("---------- Swap down " + Object + "-------------");
			new TouchAction(driver).press(PointOption.point(850, 1975)).waitAction().moveTo(PointOption.point(850, 320))
					.release().perform();
		} catch (Exception e) {
			Log.error("########### Failed to swap Down ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	
	public static boolean swapDown_NOKIA(String Object, String Value) {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(3000);
			Log.info("---------- Swap down " + Object + "-------------");
			TouchAction  action =new TouchAction(driver);	
			Dimension size	=driver.manage().window().getSize();
			int width=size.width;
			int height=size.height;				
			int middleOfX=width/2;
			int startYCoordinate= (int)(height*.7);
			int endYCoordinate= (int)(height*.2);
			action.press(PointOption.point(middleOfX, startYCoordinate)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(middleOfX, endYCoordinate))
				.release().perform();
		} catch (Exception e) {
			Log.error("########### Failed to swap Down ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	//Scroll down to a particular word
	public void scrollToText(String Object, String Value)
	{
	     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+Value+"\"));");
	}

	// method for swap left of the screen
	public static boolean Swap_left(String Object, String Value) {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(2000);
			Log.info("---------- Swap to left  " + Object + "-------------");
			new TouchAction(driver).press(PointOption.point(950, 1350)).waitAction().moveTo(PointOption.point(10, 1350))
					.release().perform();

		} catch (Exception e) {
			Log.error("########### Failed to swap left ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;

		}
		return stepSuccessful;
	}
	// method for swap left of the screen
		public static boolean Swap_left_NOKIA(String Object, String Value) {
			boolean stepSuccessful = false;
			try {
				Thread.sleep(2000);
				Log.info("---------- Swap to left  " + Object + "-------------");
				new TouchAction(driver).press(PointOption.point(696, 1001)).waitAction().moveTo(PointOption.point(10, 1001))
						.release().perform();

			} catch (Exception e) {
				Log.error("########### Failed to swap left ############# " + Object);
				// e.printStackTrace();
				Constants.TestCaseStatus = false;

			}
			return stepSuccessful;
		}
		public static boolean Swap_left_LG_V20(String Object, String Value) {
			boolean stepSuccessful = false;
			try {
				Thread.sleep(2000);
				Log.info("---------- Swap to left  " + Object + "-------------");
				new TouchAction(driver).press(PointOption.point(500, 900)).waitAction().moveTo(PointOption.point(164, 900))
						.release().perform();

			} catch (Exception e) {
				Log.error("########### Failed to swap left ############# " + Object);
				// e.printStackTrace();
				Constants.TestCaseStatus = false;

			}
			return stepSuccessful;
		}
	//method- special method for swap sub menu of discount page
	public static boolean swapleftdicountSubMenu(String Object, String Value) {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(3000);
			Log.info("---------- Swap to left  " + Object + "-------------");
			
			new TouchAction(driver).press(PointOption.point(1020, 420)).waitAction().moveTo(PointOption.point(50, 420))
					.release().perform();

			Thread.sleep(2000);

		} catch (Exception e) {
			Log.error("########### Failed to swap left ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;

		}
		return stepSuccessful;
	}
	
	//in this method, extra wait time added during typing. Only used for type used id during login
	public static boolean TypeUserID(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			Log.info("-------- Sending keys " + Object + " ---------");
			TestSupport.test_timeout(180);
		    //Thread.sleep(12000);
			AndroidElement ele = driver.findElement(By.xpath(OR.getProperty(Object)));
			ele.sendKeys(Value);
						
		} catch (Exception e) {
			Log.error("############ Failed in Send Keys ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	
	//not in use
	public static boolean wait(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			Log.info("-------- waiting for the element " + Object + " ---------");
			ActionKeywords.driver.manage().timeouts().implicitlyWait(Integer.parseInt(Value), TimeUnit.SECONDS);
						
		} catch (Exception e) {
			Log.error("############ Failed to wait ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}

    //Type 
	public static boolean sendKeys(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			Log.info("-------- Sending keys " + Object + " ---------");
			TestSupport.test_timeout(10);
			AndroidElement ele = driver.findElement(By.xpath(OR.getProperty(Object)));
			WebDriverWait wait = new WebDriverWait(driver,300);
			wait.until(ExpectedConditions.visibilityOf(ele)).sendKeys(Value);
			
		} catch (Exception e) {
			Log.error("############ Failed in Send Keys ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}

	//tap
	public static void tap(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  " + Object + " element ------------");

			AndroidElement ele = driver.findElement(By.xpath(OR.getProperty(Object)));
			TouchActions action = new TouchActions(driver);
			action.singleTap(ele);
			action.perform();
			
		} catch (Exception e) {
			Log.error("############ tap on :" + Object + "############");
			Constants.TestCaseStatus = false;
		}
	}
	

	public static void tap_AllowAllTheTime(String Object, String Value) throws InterruptedException {
 
		try {
			Log.info("----------- tap on  checkBox element ------------");
		  TestSupport.test_timeout(10);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(710,2125)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Allow All The Time ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_AllowAllTheTime_LG(String Object, String Value) throws InterruptedException {
		 
		try {
			Log.info("----------- tap on  checkBox element ------------");
		  TestSupport.test_timeout(10);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(520,1173)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Allow All The Time ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_AllowAllTheTime_NOKIA(String Object, String Value) throws InterruptedException {
		 
		try {
			Log.info("----------- tap on  checkBox element ------------");
		  TestSupport.test_timeout(10);
		  WebElement e = driver.findElementByXPath(".//*[@text='ALLOW ALL THE TIME']");
		  int x=e.getLocation().x;
		  int y=e.getLocation().y;
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(x,y)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Allow All The Time ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Agree_CheckBox(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Agree checkBox element ------------");
			Thread.sleep(3000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(188,2324)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Agree element ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_Agree_CheckBox_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Agree checkBox element ------------");
			Thread.sleep(3000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(122,1918)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Agree element ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Agree_CheckBox_LG_V20(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Agree checkBox element ------------");
			Thread.sleep(3000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(152,1928)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Agree element ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Agree_CheckBox_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Agree checkBox element ------------");
			Thread.sleep(5000);
			WebElement e = driver.findElementByXPath(".//*[@text='I agree with TERMS & CONDITIONS']");
			int x=e.getLocation().x;
			int y=e.getLocation().y;
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(x,y)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Agree element ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_OK_InvalidLogin(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on OK( Invalid Login) ------------");
			Thread.sleep(2000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1131,1740)).perform();
          

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Invalid Login ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_OK_InvalidLogin_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on OK( Invalid Login) ------------");
			Thread.sleep(2000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(815,1352)).perform();
          

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Invalid Login ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_OK_InvalidLogin_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on OK( Invalid Login) ------------");
			Thread.sleep(2000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(564,902)).perform();
          

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Invalid Login ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_OK_VerifyID(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Invalid Login ------------");
			Thread.sleep(2000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1131,1633)).perform();
          

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Invalid Login ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_AAADrive(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Change Zip Code Menu ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(295,2276)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Change Zip Code Menut ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_AAADrive_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Change Zip Code Menu ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(250,785)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Change Zip Code Menut ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_AAADrive_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Change Zip Code Menu ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(250,599)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Change Zip Code Menut ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_ChangeZipCode(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Change Zip Code Menu ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(295,2276)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Change Zip Code Menut ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Cross_ViewCard(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Dashboard Tile ------------");
	      
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1285,135)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on Dashboard Tile  ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_Cross_ViewCard_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Dashboard Tile ------------");
	      
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(980,84)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on Dashboard Tile  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Cross_ViewCard_LG_V20(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Dashboard Tile ------------");
	      
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1228,72)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on Dashboard Tile  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_OK_DebugInfo(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Debug Info ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1082,1749)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on OK Debug Info  ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_OK_DebugInfo_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Debug Info ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(779,1364)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on OK Debug Info  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_OK_DebugInfo_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Debug Info ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(560,940)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on OK Debug Info  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_OK_LogOut(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Debug Info ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1100,1600)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on OK Debug Info  ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	
	public static void tap_More_BTN(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on More Button ------------");
			Thread.sleep(2000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1225,215)).perform();
          Thread.sleep(1000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on More Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_AAADrive_Hamburger(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on AAADrive_Hamburger Button------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1314,222)).perform();
          Thread.sleep(1000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on AAADrive_Hamburger ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_AAADrive_Hamburger_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on AAADrive_Hamburger Button------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1008,151)).perform();
          Thread.sleep(1000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on AAADrive_Hamburger ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_AAADrive_Hamburger_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on AAADrive_Hamburger Button------------");
			Thread.sleep(5000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(660,117)).perform();
          Thread.sleep(1000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on AAADrive_Hamburger ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_FlatTire(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  tap_FlatTire ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(960,1150)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap_FlatTire ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_FlatTire_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  tap_FlatTire ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(760,815)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap_FlatTire ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_FlatTire_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  tap_FlatTire ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(479,540)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap_FlatTire ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_SpareTire_No(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  tap_SpareTire ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(190,1500)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap_SpareTire ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_SpareTire_No_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  tap_SpareTire ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(125,1032)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap_SpareTire ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_SpareTire_No_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  tap_SpareTire ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(90,745)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap_SpareTire ############");
			Constants.TestCaseStatus = false;
		}
	}

	public static void tap_CrossOnRoadSide(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Change Zip Code Menu ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1350,200)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on checkBox element ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_CrossOnRoadSide_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Change Zip Code Menu ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1022,126)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on checkBox element ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_BackButton(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Back Button ------------");
			
		  Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(150,190)).perform();
          Thread.sleep(2000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_BackButton_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Back Button ------------");
			
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(100,120)).perform();
          Thread.sleep(2000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_BackButton_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Back Button ------------");
			
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(90,122)).perform();
          Thread.sleep(2000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_NearMe_Dropdown(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Near Me Drop Down Menu ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1314,532)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on checkBox element ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_NearMe_Dropdown_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Near Me Drop Down Menu ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(997,351)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on checkBox element ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_NearMe_Dropdown_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Near Me Drop Down Menu ------------");
			WebElement e = driver.findElementByXPath(".//*[@text='active-near-me-icon']");
			int x=e.getLocation().x;
			int y=e.getLocation().y;
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(x,y)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on checkBox element ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_Hotel(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Back Button ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(350,830)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_Hotel_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Back Button ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(374,552)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_CheapestGas(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Cheap Gas  ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(365,1490)).perform();
          Thread.sleep(2000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_CheapestGas_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Cheap Gas  ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(393,983)).perform();
          Thread.sleep(2000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_CrossIcon(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Cross Icon ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1290,217)).perform();
          Thread.sleep(1000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_CrossIcon_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Cross Icon ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(994,144)).perform();
          Thread.sleep(1000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_CrossIcon_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Cross Icon ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(994,144)).perform();
          Thread.sleep(1000);

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Cross_SacnCode(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Cross Icon ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1200,535)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void tap_Cross_SacnCode_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Cross Icon ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(925,550)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Cross_SacnCode_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Cross Icon ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(615,344)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Back Button ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void Tap_Allow_Push_Notifications(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Allow_Push_Notifications ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1270,644)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Allow_Push_Notifications ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void Tap_Setting_Enable_FaceID(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Setting_Enable ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1285,2387)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Setting_Enable ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void Tap_Setting_Enable_FaceID_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Setting_Enable ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(1000,1770)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Setting_Enable ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void Tap_Setting_Enable_FaceID_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Setting_Enable ------------");
			
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(650,1287)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Setting_Enable ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void Tap_AAADrive(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  AAADrive ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(203,1218)).perform();
          
			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap on  AAADrive  ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void Tap_AAADrive_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  AAADrive ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(270,780)).perform();
          
			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap on  AAADrive  ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	public static void Tap_AAADrive_Allow_LG(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  AAADrive Allow ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(550,1245)).perform();
          
			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap on  AAADrive Allow  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_AAADrive_Allow_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  AAADrive Allow ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(423,824)).perform();
          
			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap on  AAADrive Allow  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void Tap_About_AAADrive(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Setting_Enable ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(387,1817)).perform();
          
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Setting_Enable ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void Tap_About_AAADrive_NOKIA(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  Setting_Enable ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(285,1001)).perform();
          
			
		} catch (Exception e) {
			Log.error("############ unable to tap on Setting_Enable ############");
			Constants.TestCaseStatus = false;
		}
	}	

	//double click on the elements
	public static boolean doubleClick(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		try {
			Log.info("----------- Clicking " + Object + " button/link ------------");
			AndroidElement ele = driver.findElement(By.xpath(OR.getProperty(Object)));
			ele.click();
			ele.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			Log.error("############ Failed to click :" + Object + "############");
			Constants.TestCaseStatus = false;
		}
		
		return stepSuccessful;
	}
	
	
	public static void Tap_ViewBack(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  ViewBack ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(203,1218)).perform();
          
			
		} catch (Exception e) {
			Log.error("############ unable to tap on tap on  ViewBack  ############");
			Constants.TestCaseStatus = false;
		}
	}
	
	//methos- use to return back on previous page

	public static boolean back_android(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		Log.info("------------- Back Android Action ----------------");
		try {
			Thread.sleep(10000);
			driver.navigate().back();
			Thread.sleep(7000);
			} catch (Exception e) {
			Log.error("######### Failed to click android back ###########");
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	


	//Type zip code, this method will be merged with send key
	public static void click_changeZip(String Object, String Value) throws Exception {

		try {
			Log.info("---------Clicking changezipcodebtn Btn---------------");
			driver.findElementByXPath(".//*[@class='android.view.View' and @text='Change ZIP code']").click();
			
		} catch (Exception e) {
			Log.error("changecode Btn - Click Failed");
			Constants.TestCaseStatus = false;
		}

	}

	//method- for long press
	public static boolean longPress(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		try {
			WebElement ele = driver.findElement(By.xpath(OR.getProperty(Object)));
			// TouchAction touchAction=new TouchAction(driver);
			TouchAction action = new TouchAction(driver)
					.longPress(longPressOptions().withElement(element(ele)).withDuration(Duration.ofMillis(10000)))
					.release().perform();
			Thread.sleep(5000);
			Log.info("----------- longPress " + Object + " element ------------");
			Thread.sleep(3000);
		} catch (Exception e) {
			Log.error("############ Failed to longPress  :" + Object + "############");
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}

	//validate text 
	public static boolean getText(String Object, String Value) throws Exception {

		boolean stepSuccessful = false;

		try {
			Thread.sleep(3000);
			Log.info("----------- Getting Text -----------");

			String text = null;
			text = driver.findElementByXPath(OR.getProperty(Object)).getText().trim();
			Log.info("Actual Text: " + text);
			if (text.equals(Value)) {
				Log.info("************ Given Value Matched - Expected : " + Value + " Actual : " + text
						+ "************");
			} else {
				// Constants.TestCaseStatus = false;
				Log.error("############# Given Value doesnt match - Expected : " + Value + " Actual : " + text
						+ "##############");
				Constants.TestCaseStatus = false;
			}
		}

		catch (Exception e) {
			Log.error("########### Failed to get Text ##########" + Object);
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}

	public static void verifyerrmsg(String Object, String Value) throws InterruptedException {
		Log.info("Verifying Error message");
		Log.info("ObjectProperty : " + Object + "Value - " + Value);
		Thread.sleep(3000);
		try {
			if (driver.findElementByXPath("//android.view.View[@index='0']").isDisplayed()) {

				Log.info("Login failed pop up displayed");
				driver.findElementByXPath("//android.widget.Button[@index='2']").click();
				// System.out.println("PASS");
			}
		} catch (Exception e) {
			Log.error("Error message not displayed");
			Constants.TestCaseStatus = false;

		}
		// Thread.sleep(3000);

	}

	public static boolean clicksubmit(String Object, String Value) throws InterruptedException {

		boolean stepSuccessful = false;
		Log.info("Clicking Submit button");
		Log.info("ObjectProperty : " + Object + "Value - " + Value);
		Thread.sleep(3000);
		try {
			driver.findElementByClassName("android.widget.Button").click();
		} catch (Exception e) {
			Log.error("Failed to click :" + Object);
			Constants.TestCaseStatus = false;

		}
		Thread.sleep(3000);
		return stepSuccessful;
	}

	public static boolean clickMonitor(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(3000);
			Log.info("ObjectProperty : " + Object + "Value - " + Value);
			java.util.List<AndroidElement> elementsTextContainsTO = driver
					.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"Monitor Your Driving\")");

			for (AndroidElement element : elementsTextContainsTO) {
							Log.info("Found clickable object Monitor your Driving");
				element.click();
				
			
			}
		} catch (Exception e) {
			Log.info("Not found Link Monitor Your Driving");
		}
		return stepSuccessful;

	}
	
	public static boolean clickAAADrive(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			
			Log.info("ObjectProperty : " + Object + "Value - " + Value);
			AndroidElement element =  (AndroidElement) driver
					.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"AAADrive\")");

				element.click();
								
			
		} catch (Exception e) {
			Log.info("Unable to Click on AAA Drive");
		}
		return stepSuccessful;

	}


	public static boolean verifylogo(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		AndroidElement mdriver;
		Log.info("----------- Finding" + Object + " option ----------");
		
		try {
			Thread.sleep(1000);
			mdriver = driver.findElementById("com.aaa.android.discounts:id/toolbar_right_logo");
			if (mdriver.isDisplayed()) {
				Log.info("****** AAA logo is available********* ");
			}
		} catch (Exception e) {
			Log.error("######### Missing AAA logo on Membership toolbar ######### ");
			Constants.TestCaseStatus = true;
		}
		Thread.sleep(6000);
		return stepSuccessful;
	}
	
	
	public static boolean verifyNewlogo(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		AndroidElement mdriver;
		Log.info("----------- Finding" + Object + " option ----------");
		
		try {
			mdriver = driver.findElementByXPath(".//*[@class='android.widget.Image' and @text='acgdeal_ic_aaa_logo']");
			if (mdriver.isDisplayed()) {
				Log.info("****** AAA logo is available********* ");
			}
		} catch (Exception e) {
			Log.error("######### Missing AAA logo on Membership toolbar ######### ");
			Constants.TestCaseStatus = true;
		}
		Thread.sleep(6000);
		return stepSuccessful;
	}

	public static boolean verifyIfPresent(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		Log.info("----------Verifying if " + Object + "is present---------");
		// Log.info("ObjectProperty : "+Object+"Value - "+Value);
		Thread.sleep(3000);
		try {
			if (driver.findElementById(OR.getProperty(Object)).isDisplayed()) {
				Log.info(Object + " is Present");
			}
		} catch (Exception e) {
			Log.error("######## Failed to verify" + Object + "#########");
			Constants.TestCaseStatus = false;
		}
		
	  return stepSuccessful;
	}

	public static boolean verifySettingstoolbar(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		AndroidElement msettings;
		Log.info("Finding " + Object + " option");
		try {
			msettings = driver.findElementByAndroidUIAutomator("text(\"Settings\")");

		} catch (Exception e) {
			Log.error("Missing " + Object + "toolbar ");
			Constants.TestCaseStatus = false;
		}
		Thread.sleep(5000);
		return stepSuccessful;
	}

	public static boolean clickPushNotifications(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			driver.findElementByAndroidUIAutomator("text(\"Push Notifications\")").click();
		} catch (Exception e) {
			Log.error("Click failed for " + Object);
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}

	public static boolean toggleOnOff(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			driver.findElementById(OR.getProperty(Object)).click();
			Log.info("Toggle OnOff clicked for" + Object);
		} catch (Exception e) {
			Log.error("Toggle OnOff failed for " + Object);
			Constants.TestCaseStatus = false;
		}
		Thread.sleep(5000);
		return stepSuccessful;
	}

	public static boolean click_guest(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		String textobj = "new UiSelector().textContains(\"" + Object + "\")";
		// Log.info("Text Sending to Search : "+text2);
		Thread.sleep(6000);
		boolean flag = false;
		try {
			java.util.List<AndroidElement> elementsTextContainsTO = driver.findElementsByAndroidUIAutomator(textobj);
			flag = true;

			if (flag) {
				Log.info("Given Information Displayed on Page");
			}
		} catch (Exception e) {
			Log.error("Given Information not Displayed on Page");
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}

	public static boolean clickViewcard(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		Log.info("Click on Inside ViewCard ");
		try {
			java.util.List<AndroidElement> elementsTextContainsTO = driver
					.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"View\")");

			for (AndroidElement element : elementsTextContainsTO) {
				element.click();
				break;
				// }
			}
		} catch (Exception e) {
			Log.error("View card - Click Failed");
		}
		Thread.sleep(5000);
		return stepSuccessful;

	}

	//method- use in my card component
	public static boolean clickViewBack(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		Log.info("Inside ViewBack ");
		
		try {
			java.util.List<AndroidElement> elementsTextContainsTO = driver
					.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"View\")");
			for (AndroidElement element : elementsTextContainsTO) {
				System.out.println("Element - " + element.getText());
				// if (element.getText() == "View card >"){
				// Log.info("Clicking View card");
				element.click();
				break;
				// }
			}
		} catch (Exception e) {
			Log.error("View Back - Click Failed");
		}
		Thread.sleep(5000);
		return stepSuccessful;
	}

	//method- use in my card component
	public static boolean clickViewFront(String Object, String Value) throws InterruptedException {
		boolean stepSuccessful = false;
		Log.info("Inside ViewBack ");
		try {
			java.util.List<AndroidElement> elementsTextContainsTO = driver
					.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"Front\")");
			for (AndroidElement element : elementsTextContainsTO) {
				System.out.println("Element - " + element.getText());
				// if (element.getText() == "View card >"){
				// Log.info("Clicking View card");
				element.click();
				break;
				// }
			}
		} catch (Exception e) {
			Log.error("View Back - Click Failed");
		}
		return stepSuccessful;
	}

	//method- use to check build number
	public static boolean tap_fivetimes(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			driver.findElementByXPath(OR.getProperty(Object)).click();
			driver.findElementByXPath(OR.getProperty(Object)).click();
			driver.findElementByXPath(OR.getProperty(Object)).click();
			driver.findElementByXPath(OR.getProperty(Object)).click();
			driver.findElementByXPath(OR.getProperty(Object)).click();
		
		}

		catch (Exception e) {
			Log.error("########### Failed to get Text" + Object + "#############");
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}

	//method- take screen shot
	public static String takeScreenShot(String reportDirectory) throws IOException {

		String filePath = "";

		try {

			String dateTimeStamp = TestSupport.getCurrentDateTime(true, true);
			dateTimeStamp = dateTimeStamp.replace("/", "-");
			dateTimeStamp = dateTimeStamp.replace(":", "-");
//	        filePath =  Constants.getCurrentDirectoryPath() + "\\" + dataValue + " " + dateTimeStamp + ".png";
			filePath = reportDirectory + "\\" + "Screenshots" + "\\" + dateTimeStamp + ".png";
			

			File screenshot = ((TakesScreenshot) iosDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(filePath));

		} catch (Exception e) {
			System.out.println("---> Could not take screenshot.");
		}

		return filePath;
	}
	
	
	public static void closeApp(String Object, String Value) throws InterruptedException {

		Log.info("********* Closing Application *************");
		try {
			
			driver.closeApp();
			driver.quit();
						
		} catch (Exception e) {
			Log.error("########### Failed to close Application ##############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_advertisement(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on  advertisement ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(502,769)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on advertisement ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_View_dealOnline(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on View_dealOnline ------------");
			Thread.sleep(1000);
   		  TouchAction touchAction = new TouchAction(driver);
          touchAction.tap(PointOption.point(540,650)).perform();
          Thread.sleep(2000);
			
		} catch (Exception e) {
			Log.error("############ unable to tap on View_dealOnlinee ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static boolean click_ios(String Object, String Value) throws InterruptedException {

		boolean stepSuccessful = false;
		try{	
			Thread.sleep(2000);
			Log.info("----------- Clicking on " + Object + " button/link ------------");
			IOSElement ele = iosDriver.findElement(By.xpath(OR.getProperty(Object)));
			WebDriverWait wait = new WebDriverWait(iosDriver,60);
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();  
			}
		 catch (Exception e) {
			Log.error("############ Failed to click :" + Object + "############");
			e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	public static boolean clickAndWait_ios(String Object, String Value) throws InterruptedException {

		boolean stepSuccessful = false;
		try {
			
		    Thread.sleep(5000);
			Log.info("----------- Clicked on " + Object + " button/link ------------");
			IOSElement ele = iosDriver.findElement(By.xpath(OR.getProperty(Object)));
			WebDriverWait wait = new WebDriverWait(iosDriver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
			Thread.sleep(5000);
			} catch (Exception e) {
			Log.error("############ Failed to click :" + Object + "############");
			Constants.TestCaseStatus = false;
			e.printStackTrace();
		}
		return stepSuccessful;
	}
	public static boolean sendKeys_ios(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			Log.info("-------- Sending keys " + Object + " ---------");
			//TestSupport.test_timeout(10);
			Thread.sleep(8000);
			IOSElement ele = iosDriver.findElement(By.xpath(OR.getProperty(Object)));
			WebDriverWait wait = new WebDriverWait(iosDriver,300);
			wait.until(ExpectedConditions.visibilityOf(ele)).sendKeys(Value);
			iosDriver.hideKeyboard();
			
		} catch (Exception e) {
			Log.error("############ Failed in Send Keys ############# " + Object);
			 e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	public static void tap_Agree_CheckBox_ios(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Agree checkBox element ------------");
   		  TouchAction touchAction = new TouchAction(iosDriver);
          touchAction.tap(PointOption.point(123,2100)).perform();

			
		} catch (Exception e) {
			Log.error("############ unable to tap on Agree element ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Cross_ViewCard_ios(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Dashboard Tile ------------");
	      
			  TouchAction touchAction = new TouchAction(iosDriver);
	      touchAction.tap(PointOption.point(993,294)).perform();
	      Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on Dashboard Tile  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static boolean Swap_left_ios(String Object, String Value) {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(2000);
			Log.info("---------- Swap to left  " + Object + "-------------");
			new TouchAction(iosDriver).press(PointOption.point(918, 1514)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(100, 1514)).release().perform();

		} catch (Exception e) {
			Log.error("########### Failed to swap left ############# " + Object);
			 e.printStackTrace();
			Constants.TestCaseStatus = false;

		}
		return stepSuccessful;
	}
	public static boolean tap_fivetimes_ios(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		try {
			iosDriver.findElementByXPath(OR.getProperty(Object)).click();
			iosDriver.findElementByXPath(OR.getProperty(Object)).click();
			iosDriver.findElementByXPath(OR.getProperty(Object)).click();
			iosDriver.findElementByXPath(OR.getProperty(Object)).click();
			iosDriver.findElementByXPath(OR.getProperty(Object)).click();
		
		}

		catch (Exception e) {
			Log.error("########### Failed to get Text" + Object + "#############");
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	public static void tap_OK_DebugInfo_ios(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Debug Info ------------");
			
   		  TouchAction touchAction = new TouchAction(iosDriver);
          touchAction.tap(PointOption.point(186,1524)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on OK Debug Info  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static void tap_Nearme_FilterX_ios(String Object, String Value) throws InterruptedException {

		try {
			Log.info("----------- tap on Debug Info ------------");
			
   		  TouchAction touchAction = new TouchAction(iosDriver);
          touchAction.tap(PointOption.point(981,258)).perform();
          Thread.sleep(1000);
			
		} catch (Exception e) {
			Log.error("############ tap on OK Debug Info  ############");
			Constants.TestCaseStatus = false;
		}
	}
	public static boolean verifylogo_ios(String Object, String Value) throws Exception {
		boolean stepSuccessful = false;
		IOSElement mdriver;
		Log.info("----------- Finding" + Object + " option ----------");
		
		try {
			//Thread.sleep(1000);
			mdriver = iosDriver.findElementByXPath(OR.getProperty(Object));
			if (mdriver.isDisplayed()) {
				Log.info("****** AAA logo is available********* ");
			}
		} catch (Exception e) {
			Log.error("######### Missing AAA logo on Membership toolbar ######### ");
			Constants.TestCaseStatus = true;
		}
		Thread.sleep(6000);
		return stepSuccessful;
	}
	public static boolean swapDown_ios(String Object, String Value) {
		boolean stepSuccessful = false;
		try {
			Thread.sleep(3000);
			Log.info("---------- Swap down " + Object + "-------------");
			TouchAction  action =new TouchAction(iosDriver);	
			Dimension size	=iosDriver.manage().window().getSize();
			int width=size.width;
			int height=size.height;				
			int middleOfX=width/2;
			int startYCoordinate= (int)(height*.7);
			int endYCoordinate= (int)(height*.2);
			action.press(PointOption.point(middleOfX, startYCoordinate)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(middleOfX, endYCoordinate))
				.release().perform();
		} catch (Exception e) {
			Log.error("########### Failed to swap Down ############# " + Object);
			// e.printStackTrace();
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	public static boolean click_ifDisplayed_ios(String Object, String Value) throws InterruptedException {

		boolean stepSuccessful = false;
		try{	
			Thread.sleep(2000);
			Log.info("----------- Clicking if " + Object + " is visible ------------");
			IOSElement ele = iosDriver.findElement(By.xpath(OR.getProperty(Object)));
			if(ele.isDisplayed()) {
				WebDriverWait wait = new WebDriverWait(iosDriver,60);
				wait.until(ExpectedConditions.visibilityOf(ele)).click();
			}
			  
		}
		 catch (Exception e) {
			Log.error("----- Elemnt not present :" + Object + "------");
			Constants.TestCaseStatus = true;
		}
		return stepSuccessful;
	}
	public static boolean getText_ios(String Object, String Value) throws Exception {

		boolean stepSuccessful = false;

		try {
			Thread.sleep(3000);
			Log.info("----------- Getting Text -----------");

			String text = null;
			text = iosDriver.findElementByXPath(OR.getProperty(Object)).getText().trim();
			Log.info("Actual Text: " + text);
			if (text.equals(Value)) {
				Log.info("************ Given Value Matched - Expected : " + Value + " Actual : " + text
						+ "************");
			} else {
				// Constants.TestCaseStatus = false;
				Log.error("############# Given Value doesnt match - Expected : " + Value + " Actual : " + text
						+ "##############");
				Constants.TestCaseStatus = false;
			}
		}

		catch (Exception e) {
			Log.error("########### Failed to get Text ##########" + Object);
			Constants.TestCaseStatus = false;
		}
		return stepSuccessful;
	}
	public void scrollToText_ios(String Object, String Value)
	{
		JavascriptExecutor js = (JavascriptExecutor) iosDriver;
        HashMap scrollObject = new HashMap();
        scrollObject.put("predicateString", "label == '" + Value + "'");
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
	}
	public void scrollToTextByName_ios(String Object, String Value)
	{
		RemoteWebElement element = (RemoteWebElement)iosDriver.findElement(By.name(Object));
		String elementID = element.getId();
		HashMap scrollObject = new HashMap();
		scrollObject.put("element", elementID);
		scrollObject.put("toVisible", "not an empty string");
		iosDriver.executeScript("mobile:scroll", scrollObject);
	}
	public static void closeApp_ios(String Object, String Value) throws InterruptedException {

		Log.info("********* Closing Application *************");
		try {
			
			iosDriver.closeApp();
			iosDriver.quit();
						
		} catch (Exception e) {
			Log.error("########### Failed to close Application ##############");
			Constants.TestCaseStatus = false;
		}
	}
	
}

