package stepDefinition;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EbayTest {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;

	@Given("^user Launch URL in browser \"([^\"]*)\"$")
	public void user_Launch_URL_in_browser(String URL)  {
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL);
	}

	@When("^user Click on Shop by category$")
	public void user_Click_on_Shop_by_category()  {
		driver.findElement(By.xpath("//button[@id='gh-shop-a']")).click();

	}

	@When("^user Navigate and Click to Cell Phones & Accessories$")
	public void user_Navigate_and_Click_to_Cell_Phones_Accessories()  {
		driver.findElement(By.xpath("//a[normalize-space()='Cell phones & accessories']")).click();

	}

	@When("^user click Cell Phones & Smartphones$")
	public void user_click_Cell_Phones_Smartphones()  {
		driver.findElement(By.xpath("//a[contains(text(),'Cell Phones & Smartphones')]")).click();

	}

	@When("^user click See All$")
	public void user_click_See_All() {
		wait=new WebDriverWait(driver,110);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='All Filters']")));
		driver.findElement(By.xpath("//button[normalize-space()='All Filters']")).click();

	}

	@When("^user Add three filters Screen Size,Price and Item location$")
	public void user_Add_three_filters_Screen_Size_Price_and_Item_location() throws Throwable {
		wait=new WebDriverWait(driver,110);


		driver.findElement(By.xpath("//span[normalize-space()='Screen Size']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@role='checkbox'])[4]")));
		driver.findElement(By.xpath("(//input[@role='checkbox'])[4]")).click();

		js=(JavascriptExecutor)driver;

		js.executeScript("window.scrollBy(0,600)");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Price')]")));
		js.executeScript("document.querySelector(\"div[id='c3-mainPanel-price'] span[class='x-overlay-aspect__label']\").click()");

		driver.findElement(By.xpath("//input[@aria-label='Minimum Value, US Dollar']")).sendKeys("100");
		driver.findElement(By.xpath("//input[@aria-label='Maximum Value, US Dollar']")).sendKeys("500");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Item Location']")));
		driver.findElement(By.xpath("//span[normalize-space()='Item Location']")).click();


		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@name='location'])[2]")));
		driver.findElement(By.xpath("(//input[@name='location'])[2   ]")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Apply']")));
		driver.findElement(By.xpath("//button[@aria-label='Apply']")).click();


	}

	@Then("^user Verify filters tags are applied$")
	public void user_Verify_filters_tags_are_applied() {
		String actualFilter=driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[2]/h1[1]/span[1]")).getText();
		String expectedFilter="5.5 - 5.9 Inch Cell Phones";
		if (actualFilter.toLowerCase().contains(expectedFilter.toLowerCase())) {
			Reporter.log("Filter Tags are Applied successfully:: "+"Expected Filter::  "+expectedFilter+"\n\tActual Filters::  "+actualFilter+
					"\n\tActual Filters Contains Expected",true);

		} else {
			Reporter.log("Filter Tags are Not Applied:: "+"  "+expectedFilter+" \n\t"+actualFilter,true);

		}


	}

	@Then("^user Close the browser$")
	public void user_Close_the_browser() {
		driver.close();

	}
	
	@When("^user Enter HP Laptop in search box \"([^\"]*)\"$")
	public void user_Enter_HP_Laptop_in_search_box(String searchValue)  {
		
		driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys(searchValue);
	}

	@When("^user Change the search category$")
	public void user_Change_the_search_category()  {
		Select cat=new Select(driver.findElement(By.xpath("//select[@id='gh-cat']")));
		cat.selectByIndex(11);

	}

	@When("^user Click on search button$")
	public void user_Click_on_search_button()  {
		driver.findElement(By.xpath("//input[@id='gh-btn']")).click();

	}

	@Then("^user Verify page load Completely$")
	public void user_Verify_page_load_Completely()  {
		
		 try{
	         // Javascript Executor for Page load verification 
	         js = (JavascriptExecutor)driver;
	         if (js.executeScript("return document.readyState").toString().equals("complete")){
	            System.out.println("Page loaded completely and in ready state::Verified"); }
	         } catch(Exception e) {
	         
	            System.out.println("Page not loaded comletely");
	         }
		 
	}

	@Then("^user Verify first result name match with the search string$")
	public void user_Verify_first_result_name_match_with_the_search_string()  {
		String expectedString="HP Laptop";
		String actualString=driver.getTitle();
		if (actualString.toLowerCase().contains(expectedString.toLowerCase())) {
			Reporter.log("Result string is matched with searched string:: "+"\n\t"+expectedString+"\n\t"+actualString,true);
			System.out.println("Search string matching::Verified");
		} else {
			Reporter.log("Result string is NOT matched with searched string:: "+"\n\t"+expectedString+"\n\t"+actualString);
			System.out.println("Search string matching::NOT Verified");
		}

	}

}
