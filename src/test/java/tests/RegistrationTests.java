package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegistrationTests extends TestBase{
    WebDriver wd;
   @BeforeMethod
   public void preCondition(){
       if(app.getUser().isLogged()){
           app.getUser().logout();
       }
   }

    @Test
    public void regPositiveTest(){

        app.getUser().openLoginRegistrationForm();
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
       String email="vov"+i+"@gmail.com";
       String password="Vov12345$";
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitRegistration();
        app.getUser().pause(5000);
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[.='Sign Out']")));

    }

    @Test
    public void WrongEmailRegistration(){
       app.getUser().openLoginRegistrationForm();
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        String email="vov"+i+"gmail.com";
        String password="Vov12345$";
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitRegistration();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password format"));



    }
    @Test
    public void WrongPasswordRegistration(){
        app.getUser().openLoginRegistrationForm();
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        String email="vov"+i+"gmail.com";
        String password="vov";
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitRegistration();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password format"));
    }
    @Test
    public void registrationUserAlreadyExists(){
        app.getUser().openLoginRegistrationForm();

        String email="v762900819@gmail.com";
        String password="Vova1234$";
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitRegistration();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("User already exist"));
    }


//    @AfterMethod
//    public void tearDown(){
//        //wd.quit();
//    }



}
