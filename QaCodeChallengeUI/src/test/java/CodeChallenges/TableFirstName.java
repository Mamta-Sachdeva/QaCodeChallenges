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

public class TableFirstName {
	
static WebDriver driver;
	
	@BeforeMethod
	public void setUpTest(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		}

	@Test
	public void firstNameSortingTest()throws InterruptedException{

		driver.get("https://the-internet.herokuapp.com/tables");
	List<WebElement> firstnames = driver.findElements(By.xpath("//table[@id='table2']/tbody/tr/td[@class ='first-name']"));
	String[] beforeSort = new String[firstnames.size()];
	for(int i=0; i<firstnames.size(); i++){
		beforeSort[i] = firstnames.get(i).getText().trim();
		}
	System.out.println("*****original sort in the table*****");
	printName(beforeSort);
	Arrays.sort(beforeSort);

	driver.findElement(By.xpath("//*[@id=\"table2\"]/thead/tr/th[2]/span")).click();
	firstnames = driver.findElements(By.xpath("//table[@id='table2']/tbody/tr/td[@class ='first-name']"));
	String[] afterSort = new String[firstnames.size()];
	for(int i=0; i<firstnames.size(); i++){
		afterSort[i] = firstnames.get(i).getText().trim();
	}
	System.out.println("*****sorting firstnames A to Z alphabetic order by clicking FirstName*****");
	printName(afterSort);
	Assert.assertEquals(afterSort, beforeSort);
	System.out.println("sorted correctly");
	Thread.sleep(1000);

	driver.findElement(By.xpath("//*[@id=\"table2\"]/thead/tr/th[2]/span")).click();
	firstnames = driver.findElements(By.xpath("//table[@id='table2']/tbody/tr/td[@class ='first-name']"));
	String[] afterSort1 = new String[firstnames.size()];
	for(int i=0; i<firstnames.size(); i++){
		afterSort1[i] = firstnames.get(i).getText().trim();
	}
	System.out.println("*****sorted Z to A alphabetic order by Re-clicking FirstName*****");
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