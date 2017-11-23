package Testng;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.jetty7.util.log.Log;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;


import Testng.ExcelReader;
import baseClass.PageEleManage;

import java.util.Map;
public class NewTestng   {
 
	 private static final Logger logger = Logger.getLogger(NewTestng.class);
	

@Test
  public void f() {
	System.setProperty("webdriver.firefox.bin", "D:/浏览器/firefox.exe");
	 ExcelReader data = new ExcelReader("F:\\TianYuan\\SeLEnium\\Selenium\\Seleniumday-1-2\\Selenium+java\\day01\\testng.xlsx","Sheet1");
	 logger.error("open IE");
	PageEleManage page= new PageEleManage();
	 // page.driver.get("http://www.baidu.com");
	  //百度文本框输入name列第一行数据，李新鹏
	  page.getElement("baidu_text").sendKeys(data.getCellData(1,"name"));
	  logger.info("input succssful");
	  Reporter.log("f() succssful!");
	  
	//File filead= new File("C:\\Program Files\\Internet Explorer\\IEDriverServer_x64.exe");
	//System.setProperty("webdriver.ie.driver", filead.getAbsolutePath());
  //WebDriver  my_driver= new FirefoxDriver();
//	WebDriver  my_driver= new InternetExplorerDriver();
//  my_driver.get("http://www.baidu.com");
   //my_driver.findElement(By.id("kw")).sendKeys("123");
  
  //my_driver.close();
	 page.driver.close();

}

  @BeforeMethod
  public void beforeMethod() {
	  
	 
    //  System.out.println(eh.getCellData(1, 1));
    //  System.out.println(eh.getCellData(1, "name"));
	  
  }


  @DataProvider
  public Object[][] dp() {
	  
	  ExcelReader ex= new ExcelReader("F:\\天远\\SeLEnium\\Selenium\\Seleniumday-1-2\\Selenium+java\\day01\\testng.xlsx","Sheet1");
	  ex.getCellDataRow(1);
	  //定义
	  Object[][] files = new Object[ex.getCellDataRow(1).size()][];
	  files[0]= new Object[]{ ex.getCellDataRow(1).get(0)};
	 // new Object[]{result.get(i)}
    return files;
  }
}
