
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
        Response response = httpRequest.get("veiculos/");
        return response;
    }
    public Response carregaUmVeiculo(String placa){
        RequestSpecification requestSpecification;
        requestSpecification = RestAssured.given();
        Response response = requestSpecification
                .contentType(ContentType.JSON)
                .when()
                .get("veiculos/"+placa)
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
                .post("veiculos/")
                .then()
                .extract().response();
        return response;

    }
    public Response editaUmVeiculo(JSONObject carro, String placa){
        RequestSpecification requestSpecification;
        requestSpecification = RestAssured.given();
        Response response = requestSpecification
                .contentType(ContentType.JSON)
                //.param("licensePlate",placa)
                .when()
                .body(carro.toString())
                .put("veiculos/"+placa)
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
                .post("revisao/")
                .then()
                .extract().response();
        return response;


    }

}
