package stepDefinition;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/VerifyGetMethod.feature",
		glue = "stepDefinition",
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true
		//tags = ("@datadriven")
			)


public class TestRunner {
	

	
}