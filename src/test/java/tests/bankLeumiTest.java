package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class bankLeumiTest {
    WebDriver wd;
    @BeforeMethod
    public void preCondition(){
        wd=new ChromeDriver();
        wd.manage().window().maximize();
        wd.navigate().to("https://www.leumi.co.il/");
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test

    public void findElementInHerbew (){


        WebElement element = wd.findElement(By.xpath("(//li[@class='first'])[1]"));
        System.out.println(element.getText());


    }
    @AfterMethod
    public void postCondition(){
        wd.quit();
    }
}
