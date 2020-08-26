package smarte.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import smarte.pagefactory.web.SalesforceCustomFields;

public class WebpageFactory {
	
	public static SalesforceCustomFields salesforceCustom;
	private static boolean initializedPages=false;
	
	public static void initializePageObjects(WebDriver driver)
	{
		if(initializedPages == false)
		{
			salesforceCustom = PageFactory.initElements(driver, SalesforceCustomFields.class);
		}
	}

}
