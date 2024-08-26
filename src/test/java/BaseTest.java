import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.utils.JSONHandler;
import org.utils.LoggerSingleton;
import org.utils.driver.DriverFactory;


public class BaseTest {
    public static JSONHandler jsonConfigHandler = new JSONHandler("src/main/java/org/utils/config.json");
    protected final Logger logger = LoggerSingleton.getInstance().getLogger();


    @BeforeMethod
    public void setup() {
        logger.info("setting driver up");
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(jsonConfigHandler.getValue("start page"));
    }

    @AfterTest
    public void teardown() {
        logger.info("tearing driver down");
        DriverFactory.getDriver().quit();
    }
}
