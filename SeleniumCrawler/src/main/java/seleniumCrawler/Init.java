package seleniumCrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Init {
	public static Set<String> pagesVisited = new HashSet<String>();
	public static List<String> pagesToVisit = new LinkedList<String>();
	public static WebDriver driver;
	public static void init() throws FileNotFoundException, UnsupportedEncodingException{
//		File inputFile = new File("labels.txt");
//		inputFile.delete();
		System.setProperty("webdriver.chrome.driver", "browser_files\\chromedriver1.exe");
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static void goToLandingPage() throws InterruptedException {
		driver.get("http://192.168.150.45");
		Thread.sleep(2000);
   	 	driver.findElement(By.xpath("//input[@type='email']")).sendKeys("flyops@flytxt.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("flytxt");
		driver.findElement(By.id("loginButton")).click();
	}
}
