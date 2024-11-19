package arch.auto.runners;

import arch.auto.utils.reporting.TextReport;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;


@CucumberOptions(
        plugin = {"arch.auto.utils.reporting.adapter.ExtentCucumberAdapter:", "arch.auto.utils.eventHandler.TestEventHandlerPlugin"},
        glue = {"steps", "hooks"}
)

public class SequentialBaseRunner extends RunnerClassSequential {

    //TestNG after hook
    @AfterTest
    public void teardown() {
        TextReport tr = new TextReport();
        tr.createReport(true);
    }
}