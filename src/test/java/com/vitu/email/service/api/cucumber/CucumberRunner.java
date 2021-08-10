package com.vitu.email.service.api.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty", "html:target/cucumber", "summary"}, snippets = CAMELCASE)
public class CucumberRunner {
}
