/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.BeforeClass;
import org.junit.*;
import static org.junit.Assert.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

/**
 *
 * @author OpieOP
 */
public class SeleniumCarTest {

    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
    }

    @AfterClass
    public static void afterClass() {
        driver.get("http://localhost:3000/reset");
        
        driver.quit();

    }

    //Using simple test names to make sure tests happens in the right sequence
    
    //Check to see, if their in fact is 5 cars. 
    @Test
    public void Test1() throws Exception {
        //Have to wait for elements to be loaded before the test works
        WebDriverWait wait = new WebDriverWait(driver, 4000);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("id"))));

        WebElement tbody = driver.findElement(By.id("tbodycars"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        assertThat(rows.size(), is(5));
    }

    //Expected to find 2 cars upon filtering for 2002
    @Test
    public void Test2() {
        WebDriverWait wait = new WebDriverWait(driver, 4000);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("filter"))));

        WebElement filterInput = driver.findElement(By.id("filter"));
        filterInput.sendKeys("2002");
        WebElement tbody = driver.findElement(By.id("tbodycars"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        assertThat(rows.size(), is(2));
    }

    //Clearing Filter to see if there's 5 elements again.
    @Test
    public void Test3() {
        WebElement element = driver.findElement(By.id("filter"));
        element.sendKeys(Keys.CONTROL + "a",Keys.BACK_SPACE);
        WebElement tbody = driver.findElement(By.id("tbodycars"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        assertThat(rows.size(), is(5));
    }

    //Sorting them expecting 938 to be ontop 
    @Test
    public void Test4(){
        WebElement sorting = driver.findElement(By.id("h_year"));
        sorting.click();
        WebElement tbody = driver.findElement(By.id("tbodycars"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        WebElement topEntry = rows.get(0);
        assertThat(topEntry.findElements(By.tagName("td")).get(0).getText(), is("938"));
    }
    
}
