package arch.auto.hooks;

import arch.auto.utils.Constants;
import arch.auto.utils.helper.PropertyHelper;
import arch.auto.utils.reporting.Reporter;
import arch.auto.utils.selenium.DriverContext;
import arch.auto.utils.selenium.DriverFactory;
import arch.auto.utils.selenium.Screenshot;
import arch.auto.utils.selenium.TestParameters;
import cucumber.api.PickleStepTestStep;
import gherkin.ast.Step;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import io.cucumber.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class Hooks implements En{

	protected Logger log = LogManager.getLogger(this.getClass().getName());

	public Hooks() {
		Before(10, (Scenario scenario) -> {
			String[] tab = scenario.getId().split("/");
			int rawFeatureNameLength = tab.length;
			String featureName = tab[rawFeatureNameLength - 1].split(":")[0];
			TestParameters.getInstance().putFrameworkData("featureName", featureName);
			String scenarioName = scenario.getName();
			TestParameters.getInstance().putFrameworkData("scenarioName", scenarioName);
			TestParameters.getInstance().putFrameworkData("fullScenarioName", featureName + "-" + scenarioName);
			TestParameters.getInstance().putFrameworkData("cucumberTest", "true");
			log.info("**********************************************************");
			log.info(featureName + " : " + scenarioName);
			log.info("**********************************************************");
		});
	}

	@After(order = 30)
	public void checkScenarioStatus(Scenario scenario) {
		try {
			String absolutePath = "";
			if (DriverContext.getInstance().getBrowserName() != null) {
					if (scenario.isFailed()) {
						boolean errorScreenShotTaken = false;
						String screenshotOnFailure = PropertyHelper.getVariable("screenshotOnFailure") != null
								? PropertyHelper.getVariable("screenshotOnFailure")
								: PropertyHelper.getDefaultProperty("screenshotOnFailure");

						if (Boolean.parseBoolean(screenshotOnFailure)) {
							try {
								File img = new Screenshot().grabScreenshot();
								if (img != null) {
									File file = new Screenshot().saveScreenshot(img, Reporter.getScreenshotPath());
									String relativePath = "." + File.separator + "Screenshots_" + Constants.DEFAULTTIMESTAMP + File.separator + file.getName();
									absolutePath = file.getAbsolutePath();
									TestParameters.getInstance().putFrameworkData("screenshotRelativePath", relativePath);
									TestParameters.getInstance().putFrameworkData("screenshotAbsolutePath", absolutePath);
									errorScreenShotTaken = true;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (!errorScreenShotTaken) log.debug("no screenshot taken");
						}
					}

					DriverFactory.getInstance().driverManager().updateResults(scenario.isFailed() ? "failed" : "passed");
					TestParameters.getInstance().setTestData("PreviousScenarioStatus", scenario.isFailed() ? "failed" : "passed");
					if (!DriverContext.getInstance().getKeepBrowserOpen() || scenario.isFailed()) {
						DriverFactory.getInstance().quitAll();
						TestParameters.getInstance().setTestData("DriverTerminated","yes");
						DriverContext.getInstance().setBrowserInstanceKilledAtHooks(true);
						TestParameters.getInstance().deleteTestData("deviceActivated", "true");
						DriverContext.getInstance().setKeepBrowserOpen(false);
					}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@After(value= "@end")
	public void endItAll() {
		DriverFactory.getInstance().quitAll();
	}

}
