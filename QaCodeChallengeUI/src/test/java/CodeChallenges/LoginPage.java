package CodeChallenges;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {
	
	WebDriver driver;

	@BeforeTest
	public void setUpTest(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		}
	
	@Test(priority = 1)
	public void loginSuccessTest() throws Exception{
	driver.get("https://the-internet.herokuapp.com/login");
	String title = driver.getTitle();
	System.out.println("Title of loging page is : "+title);

	driver.findElement(By.xpath("//input[@id='username']")).clear();
	driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
	driver.findElement(By.xpath("//input[@id='password']")).clear();
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
	driver.findElement(By.xpath("//i[contains(text(),'Login')]")).click();
	Thread.sleep(2000);
	
	String currenturl = driver.getCurrentUrl();
	System.out.println("The current successpage url is : "+currenturl);
	Assert.assertEquals(currenturl, "https://the-internet.herokuapp.com/secure", "Current Url does not match");
	boolean successmsg = driver.findElement(By.xpath("//div[@id='flash']")).isDisplayed();
	System.out.println("The success message in displayed in green: "+successmsg);
	}
	
	@Test(priority = 2)
	public void loginErrorTest() throws InterruptedException{
	driver.get("https://the-internet.herokuapp.com/login");
	System.out.println(driver.getTitle());
	
	driver.findElement(By.xpath("//input[@id='username']")).clear();
	driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
	driver.findElement(By.xpath("//input[@id='password']")).clear();
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword");
	driver.findElement(By.xpath("//i[contains(text(),'Login')]")).click();
	
	String currenturl = driver.getCurrentUrl();
	System.out.println("The current errorpage url is :" +currenturl);
	Assert.assertEquals(currenturl, "https://the-internet.herokuapp.com/login");
	boolean errormsg = driver.findElement(By.xpath("//div[@id='flash']")).isDisplayed();
	System.out.println("The error messgae is displayed in red: "+errormsg);
	
	driver.close();
	}	
	
	@AfterTest
	public void tearDownTest() {
		driver.quit();
	}

}
