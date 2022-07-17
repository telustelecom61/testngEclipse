package TestCases;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LambdaTestP1 {
   public String username = "telustelecom61";
  public String accesskey = "rx2K0iJeiDjGbg4lssbucGwtCnguC8fLLsR7WpAcETDt7ODGS2";
  public RemoteWebDriver driver = null;
  public String gridURL = "@hub.lambdatest.com/wd/hub";
  boolean status = false;

  @BeforeTest
  @org.testng.annotations.Parameters(value={"browser","version","platform"})
  public void setUp(String browser, String version, String platform) throws Exception {
     DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("browserName", browser);
      capabilities.setCapability("version", version);
      capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any available one
      capabilities.setCapability("build", "Selenium Testng Grid");
      capabilities.setCapability("name", "Test scenarios");
      capabilities.setCapability("network", true); // To enable network logs
      capabilities.setCapability("visual", true); // To enable step by step screenshot
      capabilities.setCapability("video", true); // To enable video recording
      capabilities.setCapability("console", true); // To capture console logs
      try {
          driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
      } catch (MalformedURLException e) {
          System.out.println("Invalid grid URL");
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }

  @Test(timeOut = 20000)
  public void TestScenario1() throws Exception {
     try {
            
      driver.get("https://www.lambdatest.com/selenium-playground");
   	  driver.findElement(By.linkText("Simple Form Demo")).click();
   	  driver.getCurrentUrl().contains("simple-form-demo");
   	  String Input = "Welcome to LambdaTest";
   	  driver.findElement(By.id("user-message")).sendKeys(Input);
   	  driver.findElement(By.id("showInput")).click();
   	  String actualValue = driver.findElement(By.id("message")).getText();
   	  Assert.assertEquals(actualValue, Input);
          
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }
  
  @Test(timeOut = 20000)
  public void TestScenario2() throws Exception {
     try {
            
      driver.get("https://www.lambdatest.com/selenium-playground");
   	  driver.findElement(By.partialLinkText("Drop Sliders")).click();
   	  WebElement slider = driver.findElement(By.xpath("//div[@id=\"slider3\"]/div/input"));
   	  Actions action = new Actions(driver);
   	  action.dragAndDropBy(slider, 118, 0).perform();
   	  String actualValue = driver.findElement(By.xpath("//div[@id=\"slider3\"]/div/output")).getText();
   	  Assert.assertEquals("95", actualValue);
          
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }
  
  @Test(timeOut = 20000)
  public void TestScenario3() throws Exception {
     try {
            
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
          
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }
  
  @AfterTest
  public void tearDown() throws Exception {
     if (driver != null) {
          ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
          driver.quit();
      }
  }
}