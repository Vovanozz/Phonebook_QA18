package tests;

import manager.ProviderData;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegistrationTests extends TestBase{
    WebDriver wd;
   @BeforeMethod(alwaysRun = true)
   public void preCondition(){
       if(app.getUser().isLogged()){
           app.getUser().logout();
       }
   }

    @Test(groups = {"smoke"})
    public void regPositiveTest(){

        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        User user= User.builder()
                .email("vov"+i+"@gmail.com")
                .password("Vov12345$")
                .build();
        logger.info("registrationPositiveTest starts with: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        app.getUser().pause(5000);
        logger.info("registrationPositiveTest completed");
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[.='Sign Out']")));

    }
    @Test(dataProvider = "loginRegistrationUserFromFile",dataProviderClass = ProviderData.class)
    public void regPositiveTestCSV(User user){

        logger.info("registrationPositiveTest starts with: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        app.getUser().pause(5000);
        logger.info("registrationPositiveTest completed");
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[.='Sign Out']")));

    }

    @Test(groups = {"regress"})
    public void WrongEmailRegistration(){
        int i= (int) ((System.currentTimeMillis()/1000)%3600);
        User user= User.builder()
                .email("vov"+i+"gmail.com")
                .password("Vov12345$")
                .build();
       app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertFalse(app.getUser().isLogged());
       // Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password format"));
        Assert.assertTrue(app.getUser().isErrorFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());


    }
    @Test(groups = {"regress"})
    public void WrongPasswordRegistration(){
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
    @Test(groups = {"regress"})
    public void registrationUserAlreadyExists(){
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



    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        //wd.quit();
    }



}
