import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ManutencaoVeiculoTest extends BaseTest{
     private Response response;
     private String placa = "TSRee11";
    @Test
    public void inserirNovoVeiculo(){
        //dados que o usuario preencher no formulario
        JSONObject novoCarro = new JSONObject();
        novoCarro.put("name","Qadra");
        novoCarro.put("licensePlate",placa);
        novoCarro.put("model","Torton");
        novoCarro.put("brand","XdoVe");
        novoCarro.put("year",2077);
        novoCarro.put("category","Delivery");
        novoCarro.put("odometer", 1111);
        System.out.println("Request: "+novoCarro);
        //clique no botao add
         response=insereUmVeiculo(novoCarro);
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(response.jsonPath().getString("licensePlate").contains(placa));
        System.out.println(response.body().prettyPrint());
    }
    @Test
    public void  registrarNovaManutencao(){
        //dados preenchidos no formulario
        JSONObject novaManutencao = new JSONObject();
        novaManutencao.put("licensePlate",placa);
        novaManutencao.put("description","Oxigen");
        novaManutencao.put("date","01/01/2023");
        novaManutencao.put("value",1000);
        novaManutencao.put("referenceOdometer",10);
        novaManutencao.put("readOdometer",10);

        //clique no botao add
        response = insereManutencao(novaManutencao);
        System.out.println("Request: "+novaManutencao);
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.body().prettyPrint());

    }
    @Test
    public void registrarNovoValorOdometro(){

        response = carregaUmVeiculo(placa);
        //usuario seleciona o carro e edita
        JSONObject novoOdometro = new JSONObject();
        novoOdometro.put("name",response.jsonPath().getString("name"));
        novoOdometro.put("licensePlate",response.jsonPath().getString("licensePlate"));
        novoOdometro.put("model",response.jsonPath().getString("model"));
        novoOdometro.put("brand",response.jsonPath().getString("brand"));
        novoOdometro.put("year",response.jsonPath().getString("year"));
        novoOdometro.put("category",response.jsonPath().getString("category"));
        novoOdometro.put("odometer", 8122);
        //clica em edit
        System.out.println("Request: "+novoOdometro);
        response = editaUmVeiculo(novoOdometro);
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.body().prettyPrint());
    }
    @Test
    public void carregarListaCompletaVeiculos(){
        response = carregaListaViculos();
        Assert.assertEquals(response.getStatusCode(),200);
        // a lista exibida ao usuário é maior do que zero?
        Assert.assertNotEquals(response.body().jsonPath().getList("").size(),0);
        System.out.println("Quantidade de veiculos: "+response.body().jsonPath().getList("").size());
        System.out.println(response.body().prettyPrint());
    }
    @Test
    public void editarDadosCarro(){

        response = carregaUmVeiculo(placa);
        JSONObject carro = new JSONObject();

        carro.put("name",response.jsonPath().getString("name"));
        carro.put("licensePlate",response.jsonPath().getString("licensePlate"));
        carro.put("model","Runark teste");
        carro.put("brand","Strom teste");
        carro.put("year",response.jsonPath().getString("year"));
        carro.put("category",response.jsonPath().getString("category"));
        carro.put("odometer", response.jsonPath().getString("odometer"));

        System.out.println("Request: "+carro);
        response = editaUmVeiculo(carro);
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.body().prettyPrint());

    }

}
