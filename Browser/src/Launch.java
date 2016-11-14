


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
    import org.openqa.selenium.remote.DesiredCapabilities;

	//import junit.framework.Assert;

	public class Launch {
		
		static String driverPath = "D:\\sas\\";
		public WebDriver driver;
		
		public void setUp()
		{
			
			System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		
		public void testGooglePageTitleInIEBrowser() {
			driver.navigate().to("http://www.google.com");
			String strPageTitle = driver.getTitle();
			System.out.println("Page title: - "+strPageTitle);
			//Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"));
		}
		
		public void tearDown() {
			if(driver!=null) {
				System.out.println("Closing IE browser");
				driver.quit();
			}	
	}

		public static void main(String args[])
		{
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			
			Launch l=new Launch();
			l.setUp();
			l.testGooglePageTitleInIEBrowser();
			l.tearDown();
		}
	}

