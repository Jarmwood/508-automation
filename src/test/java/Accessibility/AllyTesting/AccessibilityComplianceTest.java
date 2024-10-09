package Accessibility.AllyTesting;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AccessibilityComplianceTest {
    static WebDriver driver;
    String url;

    public void pageUnder508Test(String targetUrl) {
        url = targetUrl;

        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        checkAccessibility();
    }

    @AfterMethod
    public void cleanBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void checkAccessibility() {
        SoftAssert sf = new SoftAssert();
        AxeBuilder axeBuilder = new AxeBuilder();

        System.out.println("*********************************");
        System.out.println("******** START 508 TEST *********");
        System.out.println("*********************************");

        Results results = axeBuilder.analyze(driver);
        if (!results.violationFree()) {
            System.out.println("Test came back with at least: " + results.getViolations().size() + ": 508 violation/s");
            List<Rule> violations = results.getViolations();
            System.out.println(violations);
            System.out.println("Incomplete list size: " + results.getIncomplete().size());
            for (Rule r : results.getIncomplete()) {
                System.out.println("Description = " + r.getDescription());
                System.out.println("Number of nodes = " + r.getNodes().size());
            }
            System.out.println("*********************************");
            System.out.println("******** END 508 TEST *********");
            System.out.println("*********************************");
            Assert.fail();
        }
    }

    @Test
    public void test508Target() {
        String accessUrl;
        accessUrl = "https://www.target.com/";
        pageUnder508Test(accessUrl);
    }
}
