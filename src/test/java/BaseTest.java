import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.utils.JSONHandler;
import org.utils.LoggerSingleton;
import org.utils.driver.DriverFactory;

import static org.testng.AssertJUnit.assertTrue;

public class BaseTest {
    public static JSONHandler jsonConfigHandler = new JSONHandler("src/main/java/org/utils/config.json");
    protected final Logger logger = LoggerSingleton.getInstance().getLogger();


    @BeforeMethod
    public void setup() {
        logger.info("setting driver up");
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(System.getProperty("startPage"));
    }
    
    @Test
    public void testTest(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(true);

    }

    @AfterTest
    public void teardown() {
        logger.info("tearing driver down");
        DriverFactory.getDriver().quit();
    }
}
