package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import utilities.ConfigReader;

public class JphStepdefinitions {

    String endpoint="";
    Response actualResponse;
    JsonPath actualResponseJsonpath;

    @Given("kullanici {string} kullanir")
    public void kullanici_kullanir(String configBaseUrl) {

        endpoint += ConfigReader.getProperty(configBaseUrl);
    }
    @Then("kullanici path parametreleri olarak {string} ekler")
    public void kullanici_path_parametreleri_olarak_ekler(String pathParams) {
        endpoint = endpoint + "/" + pathParams;
    }

    @Then("kullanici actual response status kodunun {int} test eder")
    public void kullanici_actual_response_status_kodunun_test_eder(Integer expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode,actualResponse.statusCode());
    }
    @Then("kullanici content type degerinin {string} eder")
    public void kullanici_content_type_degerinin_eder(String expectedContentType) {
        Assertions.assertEquals(expectedContentType,actualResponse.contentType());
    }
    @Then("kullanici response degerlerini jSonPath olarak kaydeder")
    public void kullanici_response_degerlerini_j_son_path_olarak_kaydeder() {
        actualResponse.prettyPrint();
        actualResponseJsonpath = actualResponse.jsonPath();

    }
    @Then("kullanici response boyddeki id degerinin {int} oldugunu test eder")
    public void kullanici_response_boyddeki_id_degerinin_oldugunu_test_eder(Integer expectedId) {
        Assertions.assertEquals(expectedId,actualResponseJsonpath.getInt("id"));
    }
    @Then("kullanici response bodydeki {string} degerinin String {string} test eder")
    public void kullanici_response_bodydeki_degerinin_string_test_eder(String attribute, String expectedAttributeStringValue) {

        Assertions.assertEquals(
                expectedAttributeStringValue,
                actualResponseJsonpath.getString(attribute)
        );


    }


    @And("kullanici GET request gonderip donen actual response i kaydeder")
    public void kullaniciGETRequestGonderipDonenActualResponseIKaydeder() {

        actualResponse = RestAssured
                                .given()
                                .when()
                                .get(endpoint);
    }
}
