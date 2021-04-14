package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestHtml2ApplicationTests {

	private static WebDriver driver;
	
	@BeforeEach
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prisc\\Documents\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void givenAClient_WhenEnteringAutomationPractice_ThenPageTitleIsCorrect() throws Exception{
		//When
		driver.get ( " http://automationpractice.com/index.php " ) ; 
		String title = driver.getTitle();
		
		//Then
		assertEquals("My Store", title);
		}
	
	@Test
	public void givenAclient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed() throws Exception{
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("priscyruiz@hotmail.com");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("12345");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		String title = driver.getTitle();
		
		// Then
		assertEquals("My account - My Store" , title );
	}
	
	@Test 
	public void givenAclient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed() throws Exception{
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("priscyruiz@hotmail.com");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("12348");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		String title = driver.getTitle();
		
		// Then
		assertEquals("Login - My Store" , title );	
		} 
	
	@Test
	public void givenAclient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("priscyruiz@hotmail.com");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("12348");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		WebElement errorMesage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		
		// Then
		assertEquals("Authentication failed." , errorMesage.getText() );	
		}
	
	@Test
	public void givenAclient_whenSearchingNonExistingProduct_thenResultsNotDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php?controller=my-account");
		WebElement searchField = driver.findElement(By.id("search_query_top"));
		searchField.sendKeys("yoyo");
		driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
		WebElement resultsMessage = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span"));
		
		// Then
		assertEquals("0 results have been found." , resultsMessage.getText() );	
		}
	
	@Test
	public void givenAclient_whenSigningOut_thenAuthenticationPageIsDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("priscyruiz@hotmail.com");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("12345");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		driver.get("http://automationpractice.com/index.php?controller=my-account");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
		String title = driver.getTitle();

		// Then
		assertEquals("Login - My Store" , title );	
	}
	
}
