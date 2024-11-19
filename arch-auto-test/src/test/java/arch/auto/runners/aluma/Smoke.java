package arch.auto.runners.aluma;

import arch.auto.runners.SequentialBaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@Smoke" , "not @ignore"},
        features = "classpath:features/Aluma"
        // , dryRun=true
)
public class Smoke extends SequentialBaseRunner {}
