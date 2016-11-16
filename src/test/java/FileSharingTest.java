import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class FileSharingTest {
    private WebDriver driver;
    
    @BeforeTest
    public void LoginBeforTest() {
        driver = new FirefoxDriver();
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
    public void FileSharingFolderExist(){
        //WebElement sharingFolder = driver.findElement(By.cssSelector();
        
        
    }
    
    
}




