package StepDefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetAllEmployeeDetails {
    Response response;


    @When("I send the get request {string}")
    public void GetALLEmployeeDetails(String endpoint) {
      response =  given().when().get(endpoint);
    }
    @Then("the response status code should be {int}")
    public void StatusCode(Integer statusCode) {
      response.then().statusCode(statusCode).log().all();
    }
   @And("the response should match the schema {string}")
    public void SchemaValidation(String schemaFile){
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFile));
    }
    @And("the response should match the expected JSON {string}")
    public void ExpectedJSONValidation(String expectedJsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //Read the Expected Json file
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/" +expectedJsonFile)));
        JsonNode expectedNode = mapper.readTree(expected);

        //Read the Actual Body from API
        String actual =response.getBody().asString();
        JsonNode actualNode = mapper.readTree(actual);
        Assert.assertEquals("JSON does not match!",expectedNode,actualNode);
    }
}
