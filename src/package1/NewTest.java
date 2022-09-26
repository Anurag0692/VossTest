package package1;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest 
{
	String URL = "https://www.ultimateqa.com/automation/";
	String title = "Automation Practice - Ultimate QA";
	WebDriver chromedriver;
	WebDriver firefoxdriver;
	String name = "";
	String timeStamp = "";

	
@BeforeClass
public void Initialize()
{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\91707\\Downloads\\chromedriver_win32\\chromedriver.exe");
	chromedriver = new ChromeDriver();
	chromedriver.get(URL);

}
  @Test (priority = 1)
  public void TitleOfPage() throws InterruptedException, IOException 
  {
	  String pageTitle = chromedriver.getTitle();
	  if(pageTitle.equalsIgnoreCase(title))
	  {
		  System.out.println("Page title is " + title);
	  }
	  
  }
  
  @Test(priority = 2)
  public void Screenshot() throws IOException
  {
	  String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
      File src= ((TakesScreenshot)chromedriver).getScreenshotAs(OutputType.FILE);  
      FileUtils.copyFile(src, new File("D:\\Selenium\\Voss\\"+fileName+".png"));
  }
  
  @Test(priority = 3)
  public void Maximize()
  {
	  chromedriver.manage().window().maximize();
  }

  
  @Test(priority = 4)
  public void LoginAndLogout() throws InterruptedException
  {
	  chromedriver.findElement(By.xpath("//*[@id=\"post-507\"]/div/div[1]/div/div[2]/div/div[1]/div/div/div/div/ul/li[6]/a")).click();
	  chromedriver.findElement(By.id("user[email]")).sendKeys("anuragsinhab24@gmail.com");
	  chromedriver.findElement(By.id("user[password]")).sendKeys("Alpha@lpha007");
      Thread.sleep(3000);
      chromedriver.findElement(By.xpath("/html/body/main/div/div/article/form/div[4]/input")).click();
      Thread.sleep(10000);
      chromedriver.findElement(By.xpath("/html/body/header/div/div/section[1]/ul/li/a/img")).click();
      Thread.sleep(3000);
      chromedriver.findElement(By.xpath("/html/body/header/div/div/section[1]/ul/li/ul/li[4]/a")).click();
      Thread.sleep(3000);
  }
  
  @Test(priority = 5)
  public void FillForm() throws InterruptedException
  {
	  chromedriver.get(URL);
      Thread.sleep(2000);
      chromedriver.findElement(By.xpath("//*[@id=\"post-507\"]/div/div[1]/div/div[2]/div/div[1]/div/div/div/div/ul/li[4]/a")).click();
      chromedriver.findElement(By.id("et_pb_contact_name_0")).sendKeys("Anurag");
      chromedriver.findElement(By.id("et_pb_contact_message_0")).sendKeys("Hello!");
      chromedriver.findElement(By.name("et_builder_submit_button")).click();
      chromedriver.findElement(By.id("et_pb_contact_name_1")).sendKeys("Sinha");
      chromedriver.findElement(By.name("et_pb_contact_message_1")).sendKeys("Hi!");
      String capta = chromedriver.findElement(By.xpath("//*[@id=\"et_pb_contact_form_1\"]/div[2]/form/div/div/p/span")).getText();
      System.out.println(capta);
      String[] part = capta.split("\\+");
      String part1 = part[0];
      String part2 = part[1];
      part1 = part1.replaceAll("\\s+","");
      part2 = part2.replaceAll("\\s+","");
      Integer value1 = Integer.parseInt(part1);
      Integer value2 = Integer.parseInt(part2);
      Integer value = value1 + value2;
      System.out.println(value);
      chromedriver.findElement(By.xpath("//*[@id=\"et_pb_contact_form_1\"]/div[2]/form/div/div/p/input")).sendKeys(String.valueOf(value));
  }
  
  @Test(priority = 6)
  public void FakePricingPage() throws InterruptedException
  {
	  chromedriver.findElement(By.name("et_builder_submit_button")).click();
      Thread.sleep(2000);
      chromedriver.get(URL);
      Thread.sleep(5000);
      chromedriver.findElement(By.xpath("//*[@id=\"post-507\"]/div/div[1]/div/div[2]/div/div[1]/div/div/div/div/ul/li[3]/a")).click();
      Thread.sleep(3000);
      chromedriver.findElement(By.xpath("//*[@id=\"post-5050\"]/div/div[1]/div/div[1]/div[2]/div[2]/div/div/div/div[4]/a")).click();
  }
  
  @AfterClass
  public void Close()
  {
	  chromedriver.close();
	  chromedriver.quit();
	  
  }
}
