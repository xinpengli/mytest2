package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;
import org.ini4j.Config;
import org.ini4j.Registry.Key;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

public class PageEleManage {
	 
    public WebDriver driver;
 
    private String yamlFile;
 
    public PageEleManage() {
     //   driver = DriverInstance.getInstance();   
    	System.setProperty("webdriver.firefox.bin", "D:/浏览器/firefox.exe");
    	   driver= new FirefoxDriver();
    	   driver.get("http://www.baidu.com");
        yamlFile = "demo";
        this.getYamlFile();
    }
 
    private HashMap<String, HashMap<String, String>> ml;
     
    private HashMap<String, HashMap<String, String>> extendLocator;
 
    @SuppressWarnings("unchecked")
    protected void getYamlFile() {
    	Yaml yaml= new Yaml();
       // File f = new File("locator/" + yamlFile + ".yaml");
    	
        URL url=PageEleManage.class.getClassLoader().getResource(yamlFile+".yaml");
      //  System.out.println(url);
        try {
             ml =( HashMap<String, HashMap<String, String>>)yaml.load(new FileInputStream(url.getFile()));
             //System.out.println(ml);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    /*@SuppressWarnings("unchecked")
    public void loadExtendLocator(String fileName){
        File f = new File("locator/" + fileName + ".yaml");
        try {
            extendLocator = Yaml.loadType(new FileInputStream(f.getAbsolutePath()),
                    HashMap.class);
            ml.putAll(extendLocator);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/
     
    public void setLocatorVariableValue(String variable, String value){
        Set<String> keys = ml.keySet();
        for(String key:keys){
             String v = ml.get(key).get("value").replaceAll("%"+variable+"%", value);
             ml.get(key).put("value",v);
        }
    }
 
    private String getLocatorString(String locatorString, String[] ss) {
        for (String s : ss) {
            locatorString = locatorString.replaceFirst("%s", s);
        }
        return locatorString;
    }
 
    private By getBy(String type, String value) {
        By by = null;
        if (type.equals("id")) {
            by = By.id(value);
        }
        if (type.equals("name")) {
            by = By.name(value);
        }
        if (type.equals("xpath")) {
            by = By.xpath(value);
        }
        if (type.equals("className")) {
            by = By.className(value);
        }
        if (type.equals("linkText")) {
            by = By.linkText(value);
        }
        return by;
    }
 
    private WebElement watiForElement(final By by) {
        WebElement element = null;
        int waitTime = 10;//Integer.parseInt(Config.getConfig("waitTime"));
        try {
            element = new WebDriverWait(driver, waitTime)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return d.findElement(by);
                        }
                    });
        } catch (Exception e) {
            System.out.println(by.toString() + " is not exist until " + waitTime);
        }
        return element;
    }
 
    private boolean waitElementToBeDisplayed(final WebElement element) {
        boolean wait = false;
        if (element == null)
            return wait;
        try {
            wait = new WebDriverWait(driver, 10)
                    .until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return element.isDisplayed();
                        }
                    });
        } catch (Exception e) {
            System.out.println(element.toString() + " is not displayed");
        }
        return wait;
    }
 
    public boolean waitElementToBeNonDisplayed(final WebElement element) {
        boolean wait = false;
        if (element == null)
            return wait;
        try {
            wait = new WebDriverWait(driver, 10)
                    .until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return !element.isDisplayed();
                        }
                    });
        } catch (Exception e) {
            System.out.println("Locator [" + element.toString()
                    + "] is also displayed");
        }
        return wait;
    }
 
    private WebElement getLocator(String key, String[] replace, boolean wait) {
        WebElement element = null;
       // System.out.println(ml);
        if (ml.containsKey(key)) {
            HashMap<String, String> m = ml.get(key);
            String type = m.get("type");
            String value = m.get("value");
            if (replace != null)
                value = this.getLocatorString(value, replace);
            By by = this.getBy(type, value);
            if (wait) {
                element = this.watiForElement(by);
                boolean flag = this.waitElementToBeDisplayed(element);
                if (!flag)
                    element = null;
            } else {
                try {
                    element = driver.findElement(by);
                } catch (Exception e) {
                    element = null;
                }
            }
        } else
            System.out.println("Locator " + key + " is not exist in " + yamlFile
                    + ".yaml");
        return element;
    }
 
    public WebElement getElement(String key) {
        return this.getLocator(key, null, true);
    }
 
    public WebElement getElementNoWait(String key) {
        return this.getLocator(key, null, false);
    }
 
    public WebElement getElement(String key, String[] replace) {
        return this.getLocator(key, replace, true);
    }
 
    public WebElement getElementNoWait(String key, String[] replace) {
        return this.getLocator(key, replace, false);
    }
    //方法体内定义的变量必须初始化，否则 写到外面
  //  private String s; 
    
    private static String config(String configname) {  
    	 // Map mapConf = null;  
    	//  mapConf = new HashMap();  
    	String s=null;
        try {  
            Properties p = new Properties();  
            p.load(PageEleManage.class.getClassLoader().getResourceAsStream("Confige.properties"));
              s= (String)p.getProperty(configname);
            //初始化迭代器
         //   Iterator it = p.keySet().iterator();  
            //迭代
           // while (it.hasNext()) {  
              //  String key = (String) it.next();  
                //String value = p.getProperty(key);  
              //  mapConf.put(key, value);  
           // }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		//return configname;  
		return s;
    }  
  
   
    public static void main(String[] args) {
    	
	
    	PageEleManage page= new PageEleManage();
		//page.getElement("baidu_text").sendKeys("123");
   System.out.println(page.config("IE"));
	}
}

