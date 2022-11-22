import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestsWithSelenium {

    private static WebDriver driver;

    @Before
    public void setWebDriver() {
        System.setProperty("chromedriver.exe" , "src\\driver\\chromedriver.exe");
        TestsWithSelenium.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }

    @After
    public void closeDriver() {
        TestsWithSelenium.driver.close();
        TestsWithSelenium.driver.quit();
    }


    @Test
    public void checkTitlePageFalsetest() {
        String src = driver.getTitle();
        Assert.assertFalse(src == "Google");
    }

    @Test
    public void openAmazonSiteTest() {
    	Assert.assertEquals("https://www.amazon.com/", driver.getCurrentUrl());
    }
    
    @Test
    public void searchExpectedOnAmazonPageTest() {
    	try {
    		String searchTerm = "alexa";
            driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys(searchTerm, Keys.ENTER);
            String resultText = driver
                    .findElement(
                            By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[1]/div/span/div/div"))
                    .getText().toLowerCase();
            
            String resultsList = driver
                    .findElement(
                            By.xpath("//*[@id=\"search\"]/div[1]/div[1]"))
                    .getText().toLowerCase();

            
            boolean resultTextFlag = false;

            if (resultText.contains("results"))
            	resultTextFlag = true;

            boolean searchResultsHasAlexaKeyword = false;
            if (resultsList.contains("alexa"))
            	searchResultsHasAlexaKeyword = true;

            
            Assert.assertTrue(resultTextFlag);
            Assert.assertTrue(searchResultsHasAlexaKeyword);
            takePrint();
    	} catch (Exception e){
            System.out.print("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
    		takePrint();
    	}
        
        
    }
    
    
    public void takePrint() {
    	String errorsFilePath = "src\\errorsScreenshoot\\";
    	UUID uuid = UUID.randomUUID();
    	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try {
			OutputStream out = new FileOutputStream(errorsFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    

}
