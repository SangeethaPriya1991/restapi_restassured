package StepDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

public class PostNewEmploeeDetail {
    Response response;
    String endpoint;

    @Given("the API is available")
    public void LaunchAPI() {
        RestAssured.baseURI = "http://localhost:8080/";
    }

    @When("I post the new employee details to {string}")
    public void PostNewEmployeeDetail(String path) {
        this.endpoint = path;
    }
    @And("I send the request body from Json file {string}")
    public void RequestFromJsonFile(String createRequestfile) {
        File file = new File("src/test/resources/" +createRequestfile);
        response = given()
                .header("Content-Type","application/json")
                .body(file)
                .when()
                .post(endpoint);

    }
    @Then("the response status code for new employee should be {int}")
    public void NewEmployeeStatusCodeValidation(Integer statusCode) {
        response.then().statusCode(statusCode);
    }
    @And("the response should match the new employee schema {string}")
    public void NewEmployeeSchemaValidation(String JsonSchema) {
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JsonSchema));
    }
    @And("the response should match the new employee expected json {string}")
    public void ExpectedNewEmployeeDataValidation(String ExpectedJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //Read the expected created data Json file
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/" +ExpectedJson)));
        JsonNode expectedNode  = mapper.readTree(expected);

        //Read the actual created data body from API
        String actual = response.body().asString();
        JsonNode actualNode = mapper.readTree(actual);

        Assert.assertEquals("Json does not match", expectedNode,actualNode);

    }
}
