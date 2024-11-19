package arch.auto.runners.aluma;

import arch.auto.runners.SequentialBaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@Demo"},
        features = "classpath:features/Aluma"
        // , dryRun=true
)
public class Aluma extends SequentialBaseRunner {}
