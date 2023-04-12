package tests;

import manager.HelperBase;
import manager.HelperUser;
import manager.NGListener;
import manager.ProviderData;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//@Listeners(NGListener.class)
public class LoginTests extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test(invocationCount = 1,groups = {"smoke"})

    public void loginPositiveTest(User user){

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(10000);
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[@href='/contacts']")));
    }
    @Test
    public void loginPositiveTestConfig(){

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(app.getEmail(), app.getPassword());
        app.getUser().submitLogin();
        app.getUser().pause(10000);
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[@href='/contacts']")));
    }
    @Test(dataProvider = "loginModelDto",dataProviderClass = ProviderData.class )

    public void loginPositiveTestDataProvider(User user){

//        User user= User.builder()
//                .email("v762900819@gmail.com")
//                .password("Vova1234$")
//                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(10000);
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[@href='/contacts']")));
    }
    @Test(dataProvider = "loginRegistrationUserFromFile",dataProviderClass = ProviderData.class )

    public void loginPositiveTestFromFile(User user){

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(10000);
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[@href='/contacts']")));
    }
    @Test(groups = {"smoke","regress"})
    public void loginNegativeTestEmail(){
        User user= User.builder()
                .email("v762900819gmail.com")
                .password("Vova1234$")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password"));
    }
    @Test(groups = {"regress"})
    public void loginNegativeTestPassword(){
        User user= User.builder()
                .email("v762900819@gmail.com")
                .password("Vov")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password"));
    }
    @Test(groups = {"regress"})
    public void loginUnregisterUserTest(){
        User user= User.builder()
                .email("vov19@gmail.com")
                .password("Vov1234$")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password"));

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        //wd.quit();
    }


}
