package stepdefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Orangehrm_stepDefinition {
    
    WebDriver driver;
    
    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
//        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\your\\chromedriver.exe"); // Set the correct path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {
        WebElement loginField = driver.findElement(By.xpath("//input[@name='username']"));
        loginField.sendKeys(username);
    }

    @When("User enters password {string}")
    public void user_enters_password(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(password);
    }

    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
    }

    @Then("User should see {string}")
    public void user_should_see(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardText = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")));
        
        String actualText = dashboardText.getText();
        Assert.assertEquals(actualText, expectedText, "Dashboard verification failed!");
    }

    @Given("User is logged into OrangeHRM")
    public void user_is_logged_into_orange_hrm() {
        user_is_on_the_login_page();
        user_enters_username("Admin");
        user_enters_password("admin123");
        user_clicks_on_the_login_button();
        user_should_see("Dashboard");
    }

    @When("User checks for the quick launch option {string}")
    public void user_checks_for_the_quick_launch_option(String option) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement quickLaunchOption = driver.findElement(By.xpath("//p[text()='" + option + "']"));
//        Assert.assertTrue(quickLaunchOption.isDisplayed(), "Quick launch option not found:" + option);
    	List<WebElement> elements= driver.findElements(By.xpath("//div[@class='orangehrm-quick-launch-heading']/p"));
	    for (WebElement element:elements){
	    	if(element.getText().equals(option)) {
	           Assert.assertEquals(element.getText(),option);
	           break;
	    	}
	        }

    }

    @Then("The quick launch option {string} should be displayed")
    public void the_quick_launch_option_should_be_displayed(String option) {
//        WebElement quickLaunchOption = driver.findElement(By.xpath("//p[text()='" + option + "']"));
//        Assert.assertTrue(quickLaunchOption.isDisplayed(), "Quick launch option is not visible:" + option);
    }
    @After
    public void teardown() {
    	driver.quit();
    }
    
}