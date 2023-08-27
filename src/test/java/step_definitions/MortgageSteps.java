package step_definitions;

import command_providers.ActOn;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page_objects.Home;
import utilities.DateUtils;
import utilities.ReadConfigFiles;

import java.util.List;
import java.util.Map;

public class MortgageSteps {

    private static Logger LOGGER = LogManager.getLogger(MortgageSteps.class);

    WebDriver driver = Hooks.driver;

    @Given("User is in the mortgage calculator home page")
    public void navigateToMortgageCalculatorHomePage(){
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("MortgageAppUrl"));
        LOGGER.info("User is landed on the Mortgage Calculator Home page");
    }

    @When("User enters data and clicks calculate button")
    public void enterDataAndClickCalculate(DataTable table){
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for(Map<String, String> cells: data){

            String[] date = DateUtils.returnNextMonth();

            new Home(driver)
                    .typeHomePrice(cells.get("HomePrice"))
                    .clickDownPaymentInDollar()
                    .typeDownPayment(cells.get("DownPmt"))
                    .typeLoanAmount(cells.get("LoanAmt"))
                    .typeInterestRate(cells.get("IntRate"))
                    .typeLoanTermYears(cells.get("LoanTermYrs"))
                    .selectMonth(date[0])
                    .typeYear(date[1])
                    .typePropertyTax(cells.get("PptyTax"))
                    .typePmi(cells.get("Pmi"))
                    .typeHomeOwnerInsurance(cells.get("HOI"))
                    .typeMonthlyHoa(cells.get("Hoa"))
                    .selectLoanType(cells.get("LoanType"))
                    .selectBuyOrRefi(cells.get("BuyRefi"))
                    .clickCalculateButton();

        }

    }

    @Then("^Monthly Payment rate is \"(.+?)\"$")
    public void validateMonthlyPayment(String monthlyPayment){
        new Home(driver)
                .validateTotalMonthlyPayment(monthlyPayment);
        LOGGER.info("Monthly Payment is validated");
    }
}
