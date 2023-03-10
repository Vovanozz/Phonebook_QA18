package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

//WebDriver wd;
    public static ApplicationManager app=new ApplicationManager();
    @BeforeSuite
    public void setUp(){
        app.init();

    }
//    public void init(){
//        wd=new ChromeDriver();
//        wd.navigate().to("https://telranedu.web.app/home");
//        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//    }
    @AfterSuite
    public void tearDown(){
        //wd.quit();
        app.stop();
    }





//    public boolean isErrorMessageDisplayed(String message){
//        Alert alert=wd.switchTo().alert();
//        String text=alert.getText();
//        System.out.println(text);
//        alert.accept();
//        return text.contains(message);
//    }
}
