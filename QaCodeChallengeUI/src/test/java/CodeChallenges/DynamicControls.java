package CodeChallenges;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DynamicControls{
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}


	@Test
	public void enableAndDiasableTest() throws InterruptedException {

		driver.get("https://the-internet.herokuapp.com/dynamic_controls");
		
		//clicking on enable button
		boolean enb = driver.findElement(By.xpath("//button[contains(text(),'Enable')]")).isEnabled();
		System.out.println("Button is displayed and enabled: "+enb);
		Assert.assertEquals(enb, true);
		driver.findElement(By.xpath("//button[contains(text(),'Enable')]")).click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean element = wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//form[@id='input-example']//div[@id='loading']"))));
		System.out.println(element);
		Thread.sleep(1000);
		
		//entering Input in text field
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("String of my choice");
		Thread.sleep(1000);
		
		//clicking on disable button
		driver.findElement(By.xpath("//button[contains(text(),'Disable')]")).click();
		Thread.sleep(1000);
	
		WebDriverWait newwait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Boolean elem = newwait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//form[@id='input-example']//div[@id='loading']"))));
		System.out.println("Button is displayed and disabled: "+elem);
		Thread.sleep(1000);
		
		}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
