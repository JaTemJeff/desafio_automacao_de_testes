package Tests;

import Utils.Data;
import javafx.application.Application;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AlterarSimulacoesTest {

    //Altera o nome
    @Test
    public void alterarNomeTest(){
        given()
            .log().all()
            .contentType("application/json")
            .body("{\"nome\": \"Jeff Nome alterado\", \"cpf\": 66414919004,\"email\": \"jff_94@hotmail.com\",\"valor\": 2000,\"parcelas\": 10,\"seguro\": true}")
        .when()
            .put(Data.API_SIMULACOES+"/66414919004")
        .then()
            .statusCode(200);
    }

    //Se o CPF não possuir uma simulação o HTTP Status 404 é retornado com a mensagem "CPF não encontrado"
    @Test
    public void tentaAlterarNomeComCpfQueNaoPossuiSimulacaoTest(){
        given()
            .log().all()
            .contentType("application/json")
            .body("{\"nome\": \"Jeff Nome alterado\", \"cpf\": 11122233344,\"email\": \"jff_94@hotmail.com\",\"valor\": 2000,\"parcelas\": 10,\"seguro\": true}")
        .when()
            .put(Data.API_SIMULACOES+"/1112223344")
        .then()
            .statusCode(404);
    }
}
