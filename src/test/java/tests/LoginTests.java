package tests;

import manager.HelperBase;
import manager.HelperUser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void loginPositiveTest(){
        String email="v762900819@gmail.com";
        String password="Vova1234$";
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitLogin();
        app.getUser().pause(10000);
        Assert.assertTrue(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[@href='/contacts']")));
    }
    @Test
    public void loginNegativeTestEmail(){
        String email="v762900819gmail.com";
        String password="Vova1234$";
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitLogin();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password"));
    }
    @Test
    public void loginNegativeTestPassword(){
        String email="v762900819@gmail.com";
        String password="Vov";
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitLogin();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password"));
    }
    @Test
    public void loginUnregisterUserTest(){
        String email="vov19@gmail.com";
        String password="Vov1234$";
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitLogin();
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isErrorMessageDisplayed("Wrong email or password"));

    }




}
