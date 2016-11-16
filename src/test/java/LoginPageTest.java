import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class LoginPageTest {
    
    private WebDriver driver;
    
    
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
    
    @BeforeMethod
    public void beforeMethod() {
        driver.get(Constants.URL);
    }
    
    @Test
    public void testSuccessfullLogin() {
        WebElement loginFiled = driver.findElement(By.cssSelector(Constants.CSS_LOGIN_FIELD));
        loginFiled.sendKeys(Constants.LOGIN);
        WebElement passwordField = driver.findElement(By.cssSelector("#txtPassword"));
        passwordField.sendKeys(Constants.PASSWORD);
        // Elements are overlapped, that is why we select element with text
        driver.findElement(By.cssSelector("#loginBtnSecText p")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("#menuBox"));
        Assert.assertTrue(elements.size() == 1);
    }
    
    @Test(dataProvider = "incorrectCredentials")
    public void testLoginWithInvalidCredentials(String login, String password) {
        WebElement loginFiled = driver.findElement(By.cssSelector(Constants.CSS_LOGIN_FIELD));
        loginFiled.sendKeys(login);
        WebElement passwordField = driver.findElement(By.cssSelector("#txtPassword"));
        passwordField.sendKeys(password);
        // Elements are overlapped, that is why we select element with text
        driver.findElement(By.cssSelector("#loginBtnSecText p")).click();
        String errorMessage = driver.findElement(By.cssSelector("#alertBox center")).getText();
        Assert.assertEquals(errorMessage, Constants.ERROR_MESSAGE_TEXT);
    }
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    
    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }
    
    @DataProvider
    public Object[][] incorrectCredentials() {
        return new Object[][]{
                {"incorrectLogin", Constants.PASSWORD},
                {Constants.LOGIN, "incorrectPassword"}
        };
    }

}
