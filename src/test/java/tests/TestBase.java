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

    @AfterSuite
    public void tearDown(){
        //wd.quit();
        //app.stop();
    }


}
