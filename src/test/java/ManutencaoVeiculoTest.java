import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ManutencaoVeiculoTest extends BaseTest{
     Response response;
    @Test
    public void inserirNovoVeiculo(){
        //dados que o usuario preencher no formulario
        JSONObject novoCarro = new JSONObject();
        novoCarro.put("name","Qadra");
        novoCarro.put("licensePlate","AXC0101");
        novoCarro.put("model","Torton");
        novoCarro.put("brand","XdoVe");
        novoCarro.put("year",2077);
        novoCarro.put("category","Delivery");
        novoCarro.put("odometer", 1111);
        System.out.println(novoCarro);
        //clique no botao add
         response=insereUmVeiculo(novoCarro);
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(response.jsonPath().getString("name").contains("Qadra"));
        System.out.println(response.body().prettyPrint());
    }
    @Test
    public void  registrarNovaManutencao(){
        //dados preenchidos no formulario
        JSONObject novaManutencao = new JSONObject();
        novaManutencao.put("licensePlate","ABC1234");
        novaManutencao.put("description","Oxigen");
        novaManutencao.put("date","01/01/2023");
        novaManutencao.put("value",1000);
        novaManutencao.put("referenceOdometer",10);
        novaManutencao.put("readOdometer",10);

        //clique no botao add
        response = insereManutencao(novaManutencao);
        System.out.println(novaManutencao);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test
    public void registrarNovoValorOdometro(){
        String placa = "ABC1234";
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
        System.out.println(novoOdometro);
        response = editaUmVeiculo(novoOdometro);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test
    public void carregarListaCompletaVeiculos(){
        response = carregaListaViculos();
        Assert.assertEquals(response.getStatusCode(),200);
        // a lista exibida ao usuário é maior do que zero?
        Assert.assertNotEquals(response.body().jsonPath().getList("").size(),0);
        System.out.println(response.body().jsonPath().getList("").size());
    }
    @Test
    public void editarDadosCarro(){
        String placa = "ABC1234";
        response = carregaUmVeiculo(placa);
        JSONObject carro = new JSONObject();

        carro.put("name",response.jsonPath().getString("name"));
        carro.put("licensePlate",response.jsonPath().getString("licensePlate"));
        carro.put("model","Runark");
        carro.put("brand","Strom");
        carro.put("year",response.jsonPath().getString("year"));
        carro.put("category",response.jsonPath().getString("category"));
        carro.put("odometer", response.jsonPath().getString("odometer"));

        System.out.println(carro);
        response = editaUmVeiculo(carro);
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
