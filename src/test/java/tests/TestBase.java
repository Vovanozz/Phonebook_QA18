package tests;

import manager.ApplicationManager;
import manager.NGListener;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
@Listeners(NGListener.class)
public class TestBase {

//WebDriver wd;
    public static ApplicationManager app=new ApplicationManager(
            System.getProperty("browser", BrowserType.CHROME));

    Logger logger= LoggerFactory.getLogger(TestBase.class);
    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();

    }

    @AfterSuite
    public void tearDown(){
        //wd.quit();
        //app.stop();
    }
    @BeforeMethod
    public void started(Method method){
        logger.info("   #### Start test "+ method.getName());

    }
    @AfterMethod(alwaysRun = true)
    public void finished(){
        logger.info("   ####  Finished ####");

}}
