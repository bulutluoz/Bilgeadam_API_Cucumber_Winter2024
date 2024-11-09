package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class ReqressStepdefinitions {

    String baseUrl = "https://reqres.in/api";
    String endpoint;
    Response actualResponse;
    JsonPath actualResponseJsonpath;

    @Then("kullanici Reqress path parametreleri olarak {string} ekler")
    public void kullanici_reqress_path_parametreleri_olarak_ekler(String pathParams) {
        endpoint = baseUrl + "/"+ pathParams;
    }
    @Then("kullanici Reqress GET request gonderip donen actual response i kaydeder")
    public void kullanici_reqress_get_request_gonderip_donen_actual_response_i_kaydeder() {
        actualResponse = RestAssured
                                .given()
                                .when()
                                .get(endpoint);

    }
    @Then("kullanici Reqress actual response status kodunun {int} test eder")
    public void kullanici_reqress_actual_response_status_kodunun_test_eder(Integer expectedStatusKod) {
        Assertions.assertEquals(expectedStatusKod,actualResponse.statusCode());
    }
    @Then("kullanici Reqress actual response {string} header degerinin {string} oldugunu test eder")
    public void kullanici_reqress_actual_response_header_degerinin_oldugunu_test_eder(String headerIsmi, String expectedHeaderDegeri) {

        Assertions.assertEquals(expectedHeaderDegeri, actualResponse.getHeader(headerIsmi));
    }
    @Then("kullanici Reqress response i jSonPath olarak kaydeder")
    public void kullanici_reqress_response_i_j_son_path_olarak_kaydeder() {

        actualResponseJsonpath = actualResponse.jsonPath();
    }
    @Then("{string} altindaki {string} degerinin {string} oldugunu test eder")
    public void altindaki_degerinin_oldugunu_test_eder(String anaAttribute, String attribute, String expectedAttributeValue) {


        Assertions.assertEquals(expectedAttributeValue ,
                                actualResponseJsonpath.getString(anaAttribute+"."+attribute));



    }


}
