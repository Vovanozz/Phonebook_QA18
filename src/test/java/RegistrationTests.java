import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegistrationTests {
    WebDriver wd;

    @BeforeMethod
    public void init(){
        wd=new ChromeDriver();
        wd.navigate().to("https://telranedu.web.app/home");
    }

    @Test
    public void regPositiveTest(){
        wd.findElement(By.xpath("//a[@href='/login']")).click();

        int i= (int) ((System.currentTimeMillis()/1000)%3600);

        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
       emailInput.click();
      emailInput.clear();
       emailInput.sendKeys("vov"+i+"@gmail.com");

        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
        passInput.click();
        passInput.clear();
        passInput.sendKeys("Vov12345$");
        wd.findElement(By.xpath("//button[2]")).click();
        //Assert.assertTrue(wd.findElement(By.xpath("//a[text='ADD']")).getText().equals("ADD"));

    }
    @Test
    public void WrongEmailRegistrationTest(){
       wd.findElement(By.xpath("//a[@href='/login']")).click();
         WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
         emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("vovgmailcom");

        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
        passInput.click();
        passInput.clear();
        passInput.sendKeys("Vov12345$");
        wd.findElement(By.xpath("//button[2]")).click();

    }
    @Test
    public void WrongPasswordRegistrationTest(){
        wd.findElement(By.xpath("//a[@href='/login']")).click();

        int i= (int) ((System.currentTimeMillis()/1000)%3600);

        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("vov"+i+"@gmail.com");

        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
        passInput.click();
        passInput.clear();
        passInput.sendKeys("vov");
        wd.findElement(By.xpath("//button[2]")).click();
    }




    @AfterMethod
    public void tearDown(){
        //wd.quit();
    }



}
