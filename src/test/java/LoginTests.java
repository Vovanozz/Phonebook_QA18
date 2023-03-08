import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void loginPositiveTest(){
        String email="v762900819@gmail.com";
        String password="Vova1234$";
        openLoginRegistrationForm();
        fillLoginRegistrationForm(email,password);
        submitLogin();
        pause(5000);
        Assert.assertTrue(isLogged());
        Assert.assertTrue(isElementPresent(By.xpath("//a[@href='/contacts']")));
    }
    @Test
    public void loginNegativeTestEmail(){
        String email="v762900819gmail.com";
        String password="Vova1234$";
        openLoginRegistrationForm();
        fillLoginRegistrationForm(email,password);
        submitLogin();
        Assert.assertFalse(isLogged());
        Assert.assertTrue(isErrorMessageDisplayed("Wrong email or password"));
    }
    @Test
    public void loginNegativeTestPassword(){
        String email="v762900819@gmail.com";
        String password="Vov";
        openLoginRegistrationForm();
        fillLoginRegistrationForm(email,password);
        submitLogin();
        Assert.assertFalse(isLogged());
        Assert.assertTrue(isErrorMessageDisplayed("Wrong email or password"));
    }
    @Test
    public void loginUnregisterUserTest(){
        String email="vov19@gmail.com";
        String password="Vov1234$";
        openLoginRegistrationForm();
        fillLoginRegistrationForm(email,password);
        submitLogin();
        Assert.assertFalse(isLogged());
        Assert.assertTrue(isErrorMessageDisplayed("Wrong email or password"));

    }




}
