package CodeChallenges;


import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NewBrowserTab{
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUpTest(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		}

	@Test
	public void newWindowTest()throws InterruptedException{
		driver.get("https://the-internet.herokuapp.com/windows");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
		Thread.sleep(2000);
		
		Set<String> winhandle = driver.getWindowHandles();
		Iterator<String> iter = winhandle.iterator();
		String mainWindowId = iter.next();
		
		System.out.println("Parent window handle is: "+mainWindowId);
		String newWindowId = iter.next();
		System.out.println("Child window handle is: "+newWindowId);
	
		driver.switchTo().window(newWindowId);
		String newWintitle = driver.getTitle();
		System.out.println("New window title is: "+newWintitle);
		driver.close();
		Thread.sleep(1000);
		
		driver.switchTo().window(mainWindowId);
		String mainWintitle = driver.getTitle();
		System.out.println("Main window title is: "+mainWintitle);
		}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
