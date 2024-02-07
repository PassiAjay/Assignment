package Demo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Assignmnet {
	static ArrayList<Integer> al = new ArrayList<Integer>();

	
	public static void main(String args[]) throws InterruptedException {
		//set the path of Chrome driver
		System.setProperty("Webdriver.chrome.driver",
				"C:\\\\Users\\\\Ajay.Passi\\\\Downloads\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//used to specify options for the Chrome browser that Selenium will launch.
		ChromeOptions options = new ChromeOptions();
		//do not want the DevTools to be enabled
		options.setExperimentalOption("devtools", false);
		
        //To navigate the URL
		driver.get("https://stackoverflow.com");
		//For window maximize
		driver.manage().window().maximize();
		//For waiting to execute next step
		Thread.sleep(2000);
		//For Cookies Web elements
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		//For Login Button
		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		//To pass main and password and click on submit button
		driver.findElement(By.id("email")).sendKeys("passiajay306@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Abc@1234");
		driver.findElement(By.id("submit-button")).click();
		Thread.sleep(2000);
		//For click on question icon
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
	    driver.findElement(By.id("nav-questions")).click();
	    //For click on users icon
		driver.findElement(By.id("nav-users")).click();
		//To click on editor box using css selector
		driver.findElement(By.cssSelector("a[data-value=\"editors\"]")).click();
		//To click on second page (pagination).
		driver.findElement(By.xpath("(//a[@title=\"Go to page 2\"])[1]")).click();
		
		
        //to find the elements from webpage using CSS selector and store in variable using collection
		List<WebElement> ls = driver.findElements(By.cssSelector("[class='user-tags']"));
        
		//length of list
		int len = ls.size();
       
		// Loop through the list of WebElements 'ls' containing the edit values
		for (int i = 0; i < len; i++) {
            // Get the text of the i-th element, which represents the edit value
			String str1 = ls.get(i).getText();
			// Split the edit value string by space to separate the number and other text
			String[] str2 = str1.split(" ");
			// Parse the first part of the split string as an integer, representing the edit count
			int a = Integer.parseInt(str2[0]);
            // Add the parsed edit count to the ArrayList 'al'
			al.add(a);

		}
		// Sort the ArrayList 'al' in ascending order
		Collections.sort(al);
		// Get the maximum edit value from the sorted list
		int maxEditValue = al.get(al.size() - 1);
        // Construct dynamic XPath expressions to find elements related to the user with the maximum edit value
		String xp = "//div[contains(text(),'" + maxEditValue + " edits')]";
		
        //Parent and child xpath combination and store in xp1
		String xp1 = xp + "/parent::div/div[2]/a";
		
       //Dynamic Xpath of User location
		String xp2 = xp + "/parent::div//span[@class=\"user-location\"]";
		
		//To find elements matching the XPath expressions
		List<WebElement> ls1 = driver.findElements(By.xpath(xp1));
		List<WebElement> ls2 = driver.findElements(By.xpath(xp2));
        //To print the size ls1  
		System.out.println("ls1.size()>>" + ls1.size());
		
		// Loop through the elements in ls1 (and ls2, assuming they have the same size)
		
		for (int i = 0; i < ls1.size(); i++) {
			// Print the name, edit count, and city of the user
			System.out.println(
					"name : " + ls1.get(i).getText() + ", Count: " + maxEditValue + ", City : " + ls2.get(i).getText());

		}
		//Used to close all the web browser.
          driver.quit();
	}
}
