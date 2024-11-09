package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import utilities.ConfigReader;

public class JphStepdefinitions {

    String endpoint="";
    Response actualResponse;
    JsonPath actualResponseJsonpath;
    JSONObject requestBodyJsonObject;

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

    @Then("Put request icin {string},{string},{int} {int} bilgileri ile request body olusturur")
    public void putt_request_icin_bilgileri_ile_request_body_olusturur(String title, String body, Integer userId, Integer id) {

        requestBodyJsonObject = new JSONObject();
        requestBodyJsonObject.put("userId",userId);
        requestBodyJsonObject.put("id",id);
        requestBodyJsonObject.put("title",title);
        requestBodyJsonObject.put("body",body);

    }
    @Then("kullanici Put request gonderip donen actual response i kaydeder")
    public void kullanici_put_request_gonderip_donen_actual_response_i_kaydeder() {

        actualResponse = RestAssured
                                .given().contentType(ContentType.JSON)
                                .when().body(requestBodyJsonObject.toString())
                                .put(endpoint);

    }
    @Then("actual response daki {string} header degerinin {string}")
    public void actual_response_daki_header_degerinin(String headerismi, String expectedHeaderDegeri) {

        Assertions.assertEquals(expectedHeaderDegeri,actualResponse.getHeader(headerismi));
    }
    @Then("actual response attribute degerlerinin {string},{string},{int} {int} oldugunu test eder")
    public void actual_response_attribute_degerlerinin_oldugunu_test_eder(String expectedTitle, String expectedBody, Integer expectedUserId, Integer expectedId) {

        // expected degerler (parametre) <==> Response

        JsonPath actualResponseJsonpath = actualResponse.jsonPath();

        // expected degerler (parametre) <==> actualResponseJsonpath

        Assertions.assertEquals(expectedTitle,actualResponseJsonpath.getString("title"));
        Assertions.assertEquals(expectedBody,actualResponseJsonpath.getString("body"));
        Assertions.assertEquals(expectedId,actualResponseJsonpath.getInt("id"));
        Assertions.assertEquals(expectedUserId,actualResponseJsonpath.getInt("userId"));

    }
}
