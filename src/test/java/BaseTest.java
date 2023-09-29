
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

    }
    public Response carregaListaViculos(){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("vehicles/");
        return response;
    }
    public Response carregaUmVeiculo(String placa){
        RequestSpecification requestSpecification;
        requestSpecification = RestAssured.given();
        Response response = requestSpecification
                .contentType(ContentType.JSON)
                .when()
                .get("vehicles/"+placa)
                .then()
                .extract().response();
        return response;
    }
    public Response insereUmVeiculo(JSONObject veiculo){
        RequestSpecification requestSpecification;
        requestSpecification = RestAssured.given();
        Response response = requestSpecification
                .contentType(ContentType.JSON)
                .when()
                .body(veiculo.toString())
                .post("vehicles/")
                .then()
                .extract().response();
        return response;

    }
    public Response editaUmVeiculo(JSONObject carro){
        RequestSpecification requestSpecification;
        requestSpecification = RestAssured.given();
        Response response = requestSpecification
                .contentType(ContentType.JSON)
                .when()
                .body(carro.toString())
                .put("vehicles/")
                .then()
                .extract().response();
        return response;

    }
    public Response insereManutencao(JSONObject manutencao){
        RequestSpecification requestSpecification;
        requestSpecification = RestAssured.given();
        Response response = requestSpecification
                .contentType(ContentType.JSON)
                .when()
                .body(manutencao.toString())
                .post("maintenances/")
                .then()
                .extract().response();
        return response;


    }

}
