import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileSharingTest {
    private WebDriver driver;
    
    @BeforeTest
    public void testSuccessfulyLogin() {
        driver = new FirefoxDriver();
        driver.get(Constants.URL);
        driver.manage().window().maximize();
        WebElement loginFiled = driver.findElement(By.cssSelector(Constants.CSS_LOGIN_FIELD));
        loginFiled.sendKeys(Constants.LOGIN);
        WebElement passwordField = driver.findElement(By.cssSelector("#txtPassword"));
        passwordField.sendKeys(Constants.PASSWORD);
        // Elements are overlapped, that is why we select element with text
        driver.findElement(By.cssSelector("#loginBtnSecText p")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("#menuBox"));
        Assert.assertTrue(elements.size() == 1);
    }
    @Test
    public void testFileSharingExist(){
    
String  str =  driver
            .findElement(By.xpath("//div[@id='bizFileListingWrapper']"))
            .findElement(By.id("bizFileListing"))
            .findElement(By.id("bizFileListingDirEntry-1841854"))
            .getAttribute("bdg-name");
      /*
    // List<WebElement> str =  driver.findElement(By.xpath("//div[@id='bizFileListingWrapper']")).findElements(By.xpath("/div[@id='bizFileListing']/li[@id='bizFileListingDirEntry-1841854']"));
             //fi.getAttribute("bdg-name");ndElement(By.xpath("[@id=bizFileListingDirEntry-184185]")).getAttribute("bdg-name") ;
     String   str =  driver.findElement(By.xpath("//div[@id='bizFileListingWrapper']")).findElements(By.xpath("/div[@id='bizFileListing']"));
    
        */
        Assert.assertEquals("File Sharing", str);
           
    }
    
    
}




