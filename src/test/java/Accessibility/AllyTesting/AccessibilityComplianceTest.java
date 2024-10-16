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

import java.io.*;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.FileHandler;

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

        log("*********************************");
        log("******** START 508 TEST *********");
        log("*********************************");

        Results results = axeBuilder.analyze(driver);
        if (!results.violationFree()) {
            log("Test came back with at least: " + results.getViolations().size() + ": 508 violation/s");
            List<Rule> violations = results.getViolations();
            log(violations.toString());
            log("Incomplete list size: " + results.getIncomplete().size());
            for (Rule r : results.getIncomplete()) {
                log("Description = " + r.getDescription());
                log("Number of nodes = " + r.getNodes().size());
            }
            log("*********************************");
            log("******** END 508 TEST *********");
            log("*********************************");
            Assert.fail();
        }
    }

    public static void log(String message){
        String fileName = "508_logs.txt";

        try{
            PrintWriter out = new PrintWriter(new FileWriter(fileName, true),true);
            out.println(message);
            out.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error during the reading or writing process of the log files");
        }
    }
    @Test
    public void test508Target() {
        String accessUrl;
        accessUrl = "https://www.target.com/";
        pageUnder508Test(accessUrl);
    }
}
