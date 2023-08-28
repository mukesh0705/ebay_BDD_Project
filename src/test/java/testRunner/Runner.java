package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
/*@CucumberOptions(features= {"featureFile"},monochrome=true ,glue= {"stepDefinition"}, tags= {"@Test2"},
plugin= {"pretty","html:target/cucumber-report",
		"json:target/cucumber-report/json-report.json"} )*/
@CucumberOptions(features= {"featureFile"},monochrome=true ,glue= {"stepDefinition"},
plugin= {"pretty","html:target/cucumber-report",
		"json:target/cucumber-report/json-report.json"} )
public class Runner {

}