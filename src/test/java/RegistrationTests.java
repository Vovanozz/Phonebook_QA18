import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class RegistrationTests extends TestBase{
    WebDriver wd;
   @BeforeMethod
   public void preCondition(){
       if(isLogged()){
           logout();
       }
   }

    @Test
    public void regPositiveTest(){

        openLoginRegistrationForm();
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
       String email="vov"+i+"@gmail.com";
       String password="Vov12345$";
        fillLoginRegistrationForm(email,password);
        submitRegistration();
        pause(5000);
        Assert.assertTrue(isLogged());
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));

    }

    @Test
    public void WrongEmailRegistration(){
       openLoginRegistrationForm();
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        String email="vov"+i+"gmail.com";
        String password="Vov12345$";
        fillLoginRegistrationForm(email,password);
        submitRegistration();
        pause(5000);
        Assert.assertFalse(isLogged());
        Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 400']")));



    }
    @Test
    public void WrongPasswordRegistration(){
        openLoginRegistrationForm();
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        String email="vov"+i+"gmail.com";
        String password="vov";
        fillLoginRegistrationForm(email,password);
        submitRegistration();
        pause(5000);
        Assert.assertFalse(isLogged());
        Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 400']")));
    }
    @Test
    public void registrationUserAlreadyExists(){
        openLoginRegistrationForm();

        String email="v762900819@gmail.com";
        String password="Vova1234$";
        fillLoginRegistrationForm(email,password);
        submitRegistration();
        pause(5000);
        Assert.assertFalse(isLogged());
        Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }


//    @AfterMethod
//    public void tearDown(){
//        //wd.quit();
//    }



}
