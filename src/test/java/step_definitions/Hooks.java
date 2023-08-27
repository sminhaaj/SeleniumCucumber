package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;

public class Hooks {

    private static Logger LOGGER = LogManager.getLogger(Hooks.class);
    public static WebDriver driver;

    @Before
    public void initialize(Scenario scenario){
        LOGGER.info("--------Scenario: %s----------", scenario.getName());
        driver = DriverFactory.getInstance().getDriver();
    }

    @After
    public void cleanup(Scenario scenario){
        DriverFactory.getInstance().removeDriver();
        LOGGER.info("--------End Scenario: %s-------", scenario.getName());
    }

}
