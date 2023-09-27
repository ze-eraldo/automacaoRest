
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseTest {
    private RequestSpecification request;

    @BeforeTest
    public void beforeTest(){
        RestAssured.baseURI = "http://localhost:3000/";
        request = given();
    }
    public Response carregaListaViculos(){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("veiculos/");
        return response;
    }
    public Response carregaUmVeiculo(String placa){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("veiculos?placa="+placa);
        return response;
    }
    public void insereUmVeiculo(JSONObject veiculo){
        given()
                .relaxedHTTPSValidation()
                .header("Content-Type","application/json")
                .body(veiculo)
                .post("/veiculos/")
                .then()
                .extract().response();
    }
    public Response editaUmVeiculo(JSONObject carro){
        Response response = given()
                .relaxedHTTPSValidation()
                .header("Content-Type","application/json")
                .body(carro)
                .put("/veiculos/")
                .then()
                .extract().response();
        return response;

    }
    public Response insereManutencao(JSONObject manutencao){
        Response response = given()
                .relaxedHTTPSValidation()
                .header("Content-Type","application/json")
                .body(manutencao)
                .post("/revisao")
                .then()
                .extract().response();
        return response;

    }
    public Response editaManutencao(JSONObject manutencao){
        Response response = given()
                .relaxedHTTPSValidation()
                .header("Content-Type","application/json")
                .body(manutencao)
                .put("/revisao")
                .then()
                .extract().response();
        return response;

    }



}
