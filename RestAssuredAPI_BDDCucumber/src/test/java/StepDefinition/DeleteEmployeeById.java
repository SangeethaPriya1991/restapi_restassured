package StepDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DeleteEmployeeById {
    Response response;

    @When("I delete the employee detail by employee id to {string}")
    public void DeleteEmployee(String endpoint){
        response =given()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
    @Then("the response status code for deleted employee should be {int}")
    public void DeleteStatusCode(Integer statuscode){
        response.then().statusCode(statuscode);
    }
    @And("the response should match the deleted employee schema {string}")
    public void DeleteEmployeeSchema(String deleteschema){
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(deleteschema));
    }
    @And("the response should match the deleted employee expected json {string}")
    public void DeleteEmployeeExpectedJson(String deleteschema) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //Read the delete expected json file
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/" + deleteschema )));
        JsonNode expectednode = objectMapper.readTree(expected);

        //Read the actual json from API
        String actual = response.body().asString();
        JsonNode actualnode = objectMapper.readTree(actual);

        Assert.assertEquals("Json does not match", expectednode, actualnode);

    }




}
