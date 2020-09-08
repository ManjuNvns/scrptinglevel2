package scripitng2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ReusableMethods {
	/*******************************************************
	 * Method Name			: launchBrowser
	 * Purpose				: It launches the chrome, ie & ff browsers
	 * Return Type			: webDriver instance
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  ChromeDriver ch = launchBrowser("chrome");
	 ******************************************************
	 */
	public static WebDriver launchBrowser(String strBrowser)
	{
		WebDriver oDriver = null;
		try
		{
			switch(strBrowser.toLowerCase())
			{
			case "chrome":
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
						+"\\drivers\\chromedriver.exe");
				oDriver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
				oDriver = new FirefoxDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver", 
						System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
				oDriver = new InternetExplorerDriver();
				break;
			default: 
				System.out.println("invalid browser'"+strBrowser+"' name");
				
			}
			if(oDriver!=null)
			{
				System.out.println("the Browser '"+strBrowser+"' launched succefully");
				oDriver.manage().window().maximize();
				return oDriver;
			}
			else
			{
				System.out.println("the Browser '"+strBrowser+"' failed to belaunched ");
				return null;
			}
			
			
		}catch(Exception e)
		{
			System.out.println("Exception in the launchBrowser()Method"+e.getMessage());
			return null;
		}
		finally
		{
			oDriver = null;
		}
	}
	/*******************************************************
	 * Method Name			: navigateURL
	 * Purpose				: It is used to load the required URL
	 * Return Type			: void
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public static void navigateURL(WebDriver oDriver,String strURL)
	{
		try
		{
			oDriver.navigate().to(strURL);
			Thread.sleep(2000);
		}catch(Exception e)
		{
			System.out.println("EXception in the navigateURL() method "+e.getMessage());
			
		}
	}
	/*******************************************************
	 * Method Name			: loginToApp
	 * Purpose				: Login to the actiTime application
	 * Return Type			: void
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 * @return 
	 ******************************************************
	 */
	public static void loginToApp(WebDriver oDriver)
	{
		try
		{
			oDriver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
			oDriver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("manager");
			oDriver.findElement(By.xpath("//a[@id='loginButton']")).click();
			Thread.sleep(2000);
			
			if(oDriver.findElements(By.xpath("//div[@style='display: block;']")).size()>0)
			{
				oDriver.findElement(By.xpath("//div[@id='gettingStartedShortcutsMenuCloseId']")).click();
			}
			
			//verify login successful or not
			WebElement strtrack =oDriver.findElement(By.xpath("//td[text()='Enter Time-Track']"));
			if(strtrack.isDisplayed())System.out.println("Login was successfull");
			else System.out.println("Filed to login");
			
		}catch(Exception e)
		{
			System.out.println("Exception in the loginToApp () method"+e.getMessage());
		}
	}
		/*******************************************************
		 * Method Name			: createUser
		 * Purpose				: to create the user in the actiTime application
		 * Return Type			: void
		 * Date Created			: 
		 * Date modified		:
		 * Reviewed By			:
		 * Example				:  
		 ******************************************************
		 */
		public static String createUser(WebDriver oDriver)
		{
			String strUserName= null;
			try
			{
				oDriver.findElement(By.xpath("//div[text()='USERS']")).click();
				Thread.sleep(2000);
				oDriver.findElement(By.xpath("//div[text()='Add User']")).click();
				Thread.sleep(2000);
				oDriver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("test1");
				oDriver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("user1");
				oDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("test1.user1@sg.com");
				oDriver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("test1");
				oDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Mercury");
				oDriver.findElement(By.xpath("//input[@name='passwordCopy']")).sendKeys("Mercury");
				oDriver.findElement(By.xpath("//span[text()='Create User']")).click();
				Thread.sleep(2000);
				//verify new user is created
				 strUserName ="user1, test1";
				WebElement objuser1=oDriver.findElement(By.xpath("//span[text()='user1, test1']"));
				if(objuser1.isDisplayed())
				{
					System.out.println("the user is created");
					return strUserName;
				}
				else
				{
					return null;
				}
									
			}catch(Exception e)
			{
				System.out.println("Exception in the createUser()method"+e.getMessage());
				return null;
			}

						
		}
		
		/*******************************************************
		 * Method Name			: deleteUser
		 * Purpose				: to delete the user in the actiTime application
		 * Return Type			: void
		 * Date Created			: 
		 * Date modified		:
		 * Reviewed By			:
		 * Example				:  
		 ******************************************************
		 */
		public static void deleteUser(WebDriver oDriver , String userToBeDeleted)
		{
			try
			{
				oDriver.findElement(By.xpath("//span[text()="+"'"+userToBeDeleted+"'"+"]")).click();
				Thread.sleep(2000);
				oDriver.findElement(By.xpath("//button[@id='userDataLightBox_deleteBtn']")).click();
				Thread.sleep(2000);
				oDriver.switchTo().alert().accept();
				Thread.sleep(2000);
				//verify the user is deleted or not
				WebElement objuser2 = oDriver.findElement(By.xpath("//span[text()="+"'"+userToBeDeleted+"'"+"]"));
				if(objuser2.isDisplayed())System.out.println("The user is not deleted");
				else System.out.println("The user is deleted successfully");
			
			}catch(Exception e)
			{
				System.out.println("Exception in the deleteUser()method"+e.getMessage());
				
			}
		}
		
		/*******************************************************
		 * Method Name			: logoutFromApp()
		 * Purpose				: to logout from the actiTime application
		 * Return Type			: void
		 * Date Created			: 
		 * Date modified		:
		 * Reviewed By			:
		 * Example				:  
		 ******************************************************
		 */
		public static void logoutFromApp(WebDriver oDriver)
		{
			try
			{
				oDriver.findElement(By.xpath("//a[text()= 'Logout']")).click();
				Thread.sleep(2000);
			//verify the logout is successful or not
			WebElement tdtext = oDriver.findElement(By.xpath("//td[text()="
					+ " 'Please identify yourself']"));
			Thread.sleep(2000);
			if(tdtext.isDisplayed())System.out.println("Logout is Successful");
			else System.out.println("Failed to Logout from App");
			}catch(Exception e)
			{
				System.out.println("Exception in the logoutFromApp()Method"+e.getMessage());
			}
						
		}
		/*******************************************************
		 * Method Name			: closeBrowser()
		 * Purpose				: to close the browser & null the webdriver instance
		 * Return Type			: void
		 * Date Created			: 
		 * Date modified		:
		 * Reviewed By			:
		 * Example				:  
		 ******************************************************
		 */
		public static void closeBrowser(WebDriver oDriver)
		{
			try
			{
				oDriver.close();
			    Thread.sleep(2000);
			}catch(Exception e)
			{
				System.out.println("Exception in the closeBrowser() Method"+e.getMessage());
			}
			finally
			{
				oDriver = null;
			}
		}
}
	
