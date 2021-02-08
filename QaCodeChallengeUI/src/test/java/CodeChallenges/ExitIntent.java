package CodeChallenges;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExitIntent {
	
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
	public void popUpWindowTest() throws AWTException, InterruptedException {
		driver.get("https://the-internet.herokuapp.com/exit_intent");
		String currenturl = driver.getCurrentUrl();
		System.out.println("Exit Intent page url is: "+currenturl);

		Robot robot = new Robot();
		robot.mouseMove(400, 10);
		Thread.sleep(1000);
		String modalwin = driver.findElement(By.xpath("//h3[contains(text(),'This is a modal window')]")).getText();
		System.out.println("The Title of window is: "+modalwin);

		robot.mouseMove(685,595);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//p[contains(text(),'Close')]")).click();
		String exit = driver.findElement(By.xpath("//h3[contains(text(),'Exit Intent')]")).getText();
		System.out.println("The headline of exit intent page is: "+exit);
		if(exit.equals("Exit Intent")) {
				System.out.println("The headline of exit intent page is correct");
			}else{
				System.out.println("The headline is in-correct");
			}
		}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
