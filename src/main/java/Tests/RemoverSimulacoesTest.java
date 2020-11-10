package Tests;

import Utils.Data;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RemoverSimulacoesTest {

    //Com id existente - Retorna o HTTP Status 204 se simulação for removida com sucesso
    @Test
    public void removerSimulacaoPositivoTest(){
        given()
                .log().all()
        .when()
            .delete(Data.API_SIMULACOES+Data.ID_VALIDO)
        .then()
            .statusCode(200);
    }

    //Retorna o HTTP Status 404 com a mensagem "Simulação não encontrada" se não existir a simulação pelo ID informado
    @Test
    public void removerSimulacaoNegativoTest(){
        given()
            .log().all()
        .when()
            .delete(Data.API_SIMULACOES+Data.ID_INVALIDO)
        .then()
            .statusCode(404);
    }
}
