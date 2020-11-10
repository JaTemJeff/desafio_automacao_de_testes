package Tests;

import Utils.Data;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ConsultarSimulacoesPorCPF {

    //Retorna a simulação cadastrada
    @Test
    public void consultaSimulacaoPorCPFQuePossuiSimulacaoTest(){
        given()
        .when()
            .get(Data.API_SIMULACOES+"/44288752310")
        .then()
            .statusCode(200);
    }

    //Se o CPF não possuir uma simulação o HTTP Status 404 é retornado
    @Test
    public void consultaSimulacaoPorCPFQueNaoPossuiSimulacaoTest(){
        given()
        .when()
            .get(Data.API_SIMULACOES+"/80914418009")
        .then()
            .statusCode(404);
    }
}
