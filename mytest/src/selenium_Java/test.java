package selenium_Java;



import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class test {
	
public static void main(String[] args) {
	System.setProperty("webdriver.firefox.bin", "D:/浏览器/firefox.exe");
	//File filead= new File("C:\\Program Files\\Internet Explorer\\IEDriverServer_x64.exe");
	//System.setProperty("webdriver.ie.driver", filead.getAbsolutePath());
  WebDriver  my_driver= new FirefoxDriver();
//	WebDriver  my_driver= new InternetExplorerDriver();
   my_driver.get("http://www.baidu.com");
   my_driver.findElement(By.id("kw")).sendKeys("11");
}}
