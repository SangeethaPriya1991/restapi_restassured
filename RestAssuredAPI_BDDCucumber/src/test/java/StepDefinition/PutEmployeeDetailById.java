package StepDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

public class PutEmployeeDetailById {
    Response response;
    String endpoint;

    @When("I update the employee detail by employee id  to {string}")
    public void UpdateEmployeeDetail(String path){
        this.endpoint = path;
    }

    @And("I send the updated request from Json file {string}")
    public void UpdateRequestfromJson(String updaterequestfile) throws IOException {
        String updatefile = new String(Files.readAllBytes(Paths.get("src/test/resources/" + updaterequestfile)));
        response = given()
                .header("Content-Type","application/json")
                .body(updatefile)
                .when()
                .put(endpoint);
    }
    @Then("the response status code for the updated employee should be {int}")
    public void UpdateStatusCode(Integer statuscode){
        response.then().statusCode(statuscode);
    }
    @And("the response should match the updated employee schema {string}")
    public void UpdateResponseSchema(String responseSchema){
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(responseSchema));
    }

    @And("the response should match the updated employee expected json {string}")
    public void UpdateResponseExpectedJson(String responseExpectedJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //Read the expected updated json file
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/" + responseExpectedJson)));
        JsonNode expectednode =  objectMapper.readTree(expected);

        //Read the actual updated from API
        String actual = response.asString();
        JsonNode actualnode =  objectMapper.readTree(actual);

        Assert.assertEquals("Json does not match",expectednode,actualnode);
    }

}
