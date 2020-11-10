package Tests;

import Utils.Data;
import Utils.GeradorUtils;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CadastrarSimulacoesTest {
    GeradorUtils geradorUtils = new GeradorUtils();

    //Uma simulação cadastrada com sucesso retorna o HTTP Status 201 e os dados inseridos como retorno
    // Teste positivo - Cria uma simulação com todos os dados corretos - 201
    @Test
    public void criarSimulacaoCorretamenteTest(){
        given()
            .log().all()
            .contentType("application/json")
            .body("{\"nome\": \"Jeff\", \"cpf\":" +geradorUtils.gerarCPF()+",\"email\": \"jff_94@hotmail.com\",\"valor\": 2000,\"parcelas\": 10,\"seguro\": true}")
        .when()
                .post(Data.API_SIMULACOES)
        .then()
            .log().all()
            .statusCode(201);
    }

    //Uma simulação com problema em alguma regra retorna o HTTP Status 400 com a lista de erros
    // Teste tentando criar simulação com nome vazio - 400 - nome nao pode ser vazio
    @Test
    public void criarSimulacaoComNomeVazioTest(){
        given()
            .log().all()
            .contentType("application/json")
            .body("{\"cpf\": 66414919004,\"email\": \"jff_94@hotmail.com\",\"valor\": 2000,\"parcelas\": 10,\"seguro\": true}")
        .when()
            .post(Data.API_SIMULACOES)
        .then()
            .log().all()
            .statusCode(400);
    }

    //Uma simulação para um mesmo CPF retorna um HTTP Status 409 com a mensagem "CPF já existente"
    // Teste tentando criar simulação com cpf já existente - 409 - porém da 400 - "CPF duplicado"
    @Test
    public void criarSimulacaoComCPFJaExistenteTest(){
        given()
            .log().all()
            .contentType("application/json")
            .body("{\"nome\": \"Jeff\", \"cpf\": 66414919004,\"email\": \"jff_94@hotmail.com\",\"valor\": 2000,\"parcelas\": 10,\"seguro\": true}")
        .when()
            .post(Data.API_SIMULACOES)
        .then()
            .log().all()
            .statusCode(400);
    }
}