package arch.auto.utils.reporting;

import arch.auto.utils.reporting.service.ExtentService;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import arch.auto.utils.reporting.adapter.ExtentCucumberAdapter;
import arch.auto.utils.Constants;
import arch.auto.utils.helper.PropertyHelper;
import arch.auto.utils.selenium.Screenshot;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class houses few utilities required for the report
 */
public class Reporter {
    private static Map<String, Boolean> systemInfoKeyMap = new HashMap<>();

    private Reporter() {
        // Defeat instantiation
    }

    /**
     * Gets the {@link ExtentHtmlReporter} instance created through listener
     *
     * @return The {@link ExtentHtmlReporter} instance
     */
    public static ExtentHtmlReporter getExtentHtmlReport() {

        return ExtentService.getHTML();
    }

    /**
     * Gets the {@link ExtentReports} instance created through the listener
     *
     * @return The {@link ExtentReports} instance
     */
    public static ExtentReports getExtentReport() {

        return  ExtentService.getInstance();
//        return ExtentCucumberAdapter.getExtentReport();
    }

    /**
     * Loads the XML config file
     *
     * @param xmlPath The xml path in string
     */
    public static void loadXMLConfig(String xmlPath) {
        getExtentHtmlReport().loadXMLConfig(xmlPath);
    }

    /**
     * Loads the XML config file
     *
     * @param file The file path of the XML
     */
/*    public static void loadXMLConfig(File file) {
        getExtentHtmlReport().loadXMLConfig(file);
    }*/

    /**
     * Adds an info message to the current step
     *
     * @param message The message to be logged to the current step
     */
    public static void addStepLog(String message) {
        getCurrentStep().info(message);
    }

    /**
     * Adds an info message to the current scenario
     *
     * @param message The message to be logged to the current scenario
     */
    public static void addScenarioLog(String message) {
        getCurrentScenario().info(message);
    }

    /**
     * Adds the screenshot from the given path to the current step
     *
     * @param imagePath The image path
     */
    public static void addScreenCaptureFromPath(String imagePath) {
        try{
            getCurrentStep().addScreenCaptureFromPath(imagePath);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds the screenshot from the given path with the given title to the current step
     *
     * @param imagePath The image path
     * @param title     The title for the image
     * @throws IOException Exception if imagePath is erroneous
     */
    public static void addScreenCaptureFromPath(String imagePath, String title) throws IOException {
        getCurrentStep().addScreenCaptureFromPath(imagePath, title);
    }

    /**
     * Adds the screen cast from the given path to the current step
     *
     * @param screenCastPath The screen cast path
     * @throws IOException Exception if imagePath is erroneous
     */
    public static void addScreenCast(String screenCastPath) throws IOException {
        getCurrentStep().addScreencastFromPath(screenCastPath);
    }

    /**
     * Sets the system information with the given key value pair
     *
     * @param key   The name of the key
     * @param value The value of the given key
     */
    public static void setSystemInfo(String key, String value) {
        if (systemInfoKeyMap.isEmpty() || !systemInfoKeyMap.containsKey(key)) {
            systemInfoKeyMap.put(key, false);
        }
        if (systemInfoKeyMap.get(key)) {
            return;
        }
        getExtentReport().setSystemInfo(key, value);
        systemInfoKeyMap.put(key, true);
    }

    /**
     * Sets the test runner output with the given list of strings
     *
     * @param log The list of string messages
     */
    public static void setTestRunnerOutput(List<String> log) {
        getExtentReport().setTestRunnerOutput(log);
    }

    /**
     * Sets the test runner output with the given string
     *
     * @param outputMessage The message to be shown in the test runner output screen
     */
    public static void setTestRunnerOutput(String outputMessage) {
        getExtentReport().setTestRunnerOutput(outputMessage);
    }

    /**
     * Sets the author name for the current scenario
     * @param authorName The author name of the current scenario
     */
    public static void assignAuthor(String... authorName) {
        getCurrentScenario().assignAuthor(authorName);
    }

    public static ExtentTest getCurrentStep() {								//[scol] amended
        return ExtentCucumberAdapter.stepTestThreadLocal.get();
    }

    public static ExtentTest getCurrentScenario() {							//[scol] amended
        return ExtentCucumberAdapter.scenarioThreadLocal.get();
    }


    //[scol] added
    public static String getReportPath(){
        String defaultReportPath = Constants.DEFAULTREPORTPATH;
        String reportPath = PropertyHelper.getVariable("reportPath");
        return (reportPath==null?defaultReportPath:reportPath);
    }

    //[scol] added
    public static String getScreenshotPath() {
        String screenshotPath =  Constants.SCREENSHOTPATH;
        if(!new File(screenshotPath).exists()){
            new File(screenshotPath).mkdir();
        }
        return screenshotPath;
    }

    //[scol] added
    public static String getErrorMsg(){
        return ExtentCucumberAdapter.errorMsgThreadLocal.get();
    }

    public static void addScreenshotToReport(){
        Reporter.addScreenCaptureFromPath(new Screenshot().saveScreenshot(new Screenshot().grabScreenshot(), Reporter.getScreenshotPath()).getAbsolutePath());
    }
}