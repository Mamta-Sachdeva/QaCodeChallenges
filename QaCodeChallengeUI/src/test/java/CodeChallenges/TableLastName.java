package CodeChallenges;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TableLastName {
	
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
	public void lastNameSortingTest()throws InterruptedException{

	driver.get("https://the-internet.herokuapp.com/tables");
	List<WebElement> lastnames = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[1]"));
	String[] beforeSort = new String[lastnames.size()];
	for(int i=0; i<lastnames.size(); i++){
		beforeSort[i] = lastnames.get(i).getText().trim();
		}
	System.out.println("*****original sort in the table*****");
	printName(beforeSort);
	Arrays.sort(beforeSort);

	driver.findElement(By.xpath("//table[@id='table1']/thead/tr/th[1]/span")).click();
	lastnames = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[1]"));
	String[] afterSort = new String[lastnames.size()];
	for(int i=0; i<lastnames.size(); i++){
		afterSort[i] = lastnames.get(i).getText().trim();
	}
	System.out.println("*****sorting lastnames A to Z alphabetic order by clicking LastName*****");
	printName(afterSort);
	Assert.assertEquals(afterSort, beforeSort);
	System.out.println("sorted correctly");
	Thread.sleep(1000);

	driver.findElement(By.xpath("//table[@id='table1']/thead/tr/th[1]/span")).click();
	lastnames = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[1]"));
	String[] afterSort1 = new String[lastnames.size()];
	for(int i=0; i<lastnames.size(); i++){
		afterSort1[i] = lastnames.get(i).getText().trim();
	}
	System.out.println("*****sorted Z to A alphabetic order by clicking LastName*****");
	printName(afterSort1);
	}

	public static void printName(String[]ar) throws InterruptedException {
		for(int i=0; i<ar.length; ++i) {
			System.out.println(ar[i]);
		}
		Thread.sleep(1000);	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
