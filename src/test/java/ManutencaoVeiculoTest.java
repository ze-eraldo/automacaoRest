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
        novoCarro.put("Nome","Qadra");
        novoCarro.put("Placa","XYZ1234");
        novoCarro.put("Modelo","Torton");
        novoCarro.put("Ano",2077);
        novoCarro.put("Categoria","Sport");
        novoCarro.put("Status",true);
        novoCarro.put("KM", 1000);
        //clique no botao add
         insereUmVeiculo(novoCarro);
         response = carregaListaViculos();
        //retorno da lista com o carro inserido
        Assert.assertTrue(response.jsonPath().getString("Placa").contains("XYZ1234"));
    }
    @Test
    public void  registrarNovaManutencao(){
        //dados preenchidos no formulario
        JSONObject novaManutencao = new JSONObject();
        novaManutencao.put("placa","");
        novaManutencao.put("Descricao","");
        novaManutencao.put("DataRevisao","");
        novaManutencao.put("Valor","");
        //clique no botao add
        response = insereManutencao(novaManutencao);
        //retorno da lista de manutencao
        Assert.assertTrue(response.jsonPath().getString("placa").contains("xx"));
        Assert.assertTrue(response.jsonPath().getString("DataRevisao").contains("xx"));

    }
    @Test
    public void registrarNovoValorOdometro(){

    }
    @Test
    public void editarDescricaoManutencao(){

    }
    @Test
    public void editarDadosCarro(){
        carregaUmVeiculo("XYZ5678");
    }

}
