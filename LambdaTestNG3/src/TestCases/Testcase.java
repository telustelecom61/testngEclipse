package TestCases;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Testcase {
	private String UserName = "your name";
	private String AccessKey = "your key";
	public WebDriver driver = null;
	
	@BeforeTest
	@Parameters(value={"browser", "version", "os"})
	public void Setup(String browser, String version, String os) throws Exception {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("BrowserName", browser);
		capability.setCapability("Version", version);
		capability.setCapability("os", os);
		capability.setCapability("build", "SeleniumTestGrid");
	      capability.setCapability("name", "TestScenarios using Testng");
	      capability.setCapability("network", true);
	      capability.setCapability("visual", true);
	      capability.setCapability("video", true);
	      capability.setCapability("console", true);
		//driver = new RemoteWebDriver(new URL("https://"+UserName+":"+AccessKey+"@hub.lambdatest.com/wd/hub"), capability);
		try {
	          driver = new RemoteWebDriver(new URL("https://" + UserName + ":" + AccessKey + "@hub.lambdatest.com/wd/hub"), capability);
	      } catch (MalformedURLException e) {
	          System.out.println("Invalid grid URL");
	      } catch (Exception e) {
	          System.out.println(e.getMessage());
	      }
	}
	
  @Test
  public void TestScenario1() throws InterruptedException {
	  driver.get("https://www.lambdatest.com/selenium-playground");
	  driver.findElement(By.linkText("Simple Form Demo")).click();
	  driver.getCurrentUrl().contains("simple-form-demo");
	  String Input = "Welcome to LambdaTest";
	  driver.findElement(By.id("user-message")).sendKeys(Input);
	  driver.findElement(By.id("showInput")).click();
	  String actualValue = driver.findElement(By.id("message")).getText();
	  Assert.assertEquals(actualValue, Input);
  }
  @Test
  public void TestScenario2() {
	  driver.get("https://www.lambdatest.com/selenium-playground");
	  driver.findElement(By.partialLinkText("Drop Sliders")).click();
	  WebElement slider = driver.findElement(By.xpath("//div[@id=\"slider3\"]/div/input"));
	  Actions action = new Actions(driver);
	  action.dragAndDropBy(slider, 118, 0).perform();
	  String actualValue = driver.findElement(By.xpath("//div[@id=\"slider3\"]/div/output")).getText();
	  Assert.assertEquals("95", actualValue);
  }
  @Test
  public void TestScenario3() throws Exception{
	  driver.get("https://www.lambdatest.com/selenium-playground");
	  driver.findElement(By.linkText("Input Form Submit")).click();
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  driver.findElement(By.name("name")).sendKeys("Test");
	  driver.findElement(By.name("email")).sendKeys("Test@a.ca");
	  driver.findElement(By.name("password")).sendKeys("Test123");
	  driver.findElement(By.name("company")).sendKeys("LambdaTest");
	  driver.findElement(By.name("website")).sendKeys("https://www.lambdatest.com");
	  WebElement country = driver.findElement(By.name("country"));
	  Select select = new Select(country);
	  select.selectByVisibleText("United States");
	  driver.findElement(By.name("city")).sendKeys("New York");
	  driver.findElement(By.name("address_line1")).sendKeys("D.No: 1-23-A, Test street");
	  driver.findElement(By.name("address_line2")).sendKeys("Test City, Test District");
	  driver.findElement(By.id("inputState")).sendKeys("Test State");
	  driver.findElement(By.id("inputZip")).sendKeys("452369");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  String actualValue = driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
	  Assert.assertEquals(actualValue, "Thanks for contacting us, we will get back to you shortly.");	  
  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
