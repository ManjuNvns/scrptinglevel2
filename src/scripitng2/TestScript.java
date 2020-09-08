package scripitng2;

import org.openqa.selenium.WebDriver;

public class TestScript extends ReusableMethods {
public static WebDriver oBrowser =null;
	public static void main(String[] args) {
		try
		{   
			launchBrowser("oBrowser");
			navigateURL(oBrowser, "http://localhost/login.do");
			loginToApp(oBrowser);
			String strUsercreated = createUser(oBrowser);
			deleteUser(oBrowser, strUsercreated);
			logoutFromApp(oBrowser);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			oBrowser = null;
		}

	}

}
