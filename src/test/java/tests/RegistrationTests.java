package tests;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

       // String email="vov"+i+"@gmail.com";
     // String password="Vov12345$";
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        User user= User.builder()
                .email("vov"+i+"@gmail.com")
                .password("Vov12345$")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        app.getUser().pause(5000);
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[.='Sign Out']")));

    }

    @Test
    public void WrongEmailRegistration(){
//        String email="vov"+i+"gmail.com";
//        String password="Vov12345$";
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        User user= User.builder()
                .email("vov"+i+"gmail.com")
                .password("Vov12345$")
                .build();
       app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password format"));



    }
    @Test
    public void WrongPasswordRegistration(){
//        String email="vov"+i+"gmail.com";
//        String password="vov";
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        User user= User.builder()
                .email("vov"+i+"gmail.com")
                .password("vov")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password format"));
    }
    @Test
    public void registrationUserAlreadyExists(){
//        String email="v762900819@gmail.com";
//        String password="Vova1234$";
       User user= User.builder()
               .email("v762900819@gmail.com")
               .password("Vova1234$")
               .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("User already exist"));
    }



//    @AfterMethod
//    public void tearDown(){
//        //wd.quit();
//    }



}
