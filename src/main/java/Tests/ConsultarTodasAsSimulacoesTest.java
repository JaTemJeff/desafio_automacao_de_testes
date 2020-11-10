package Tests;

import Utils.Data;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ConsultarTodasAsSimulacoesTest {

    //Retorna a lista de simulações cadastradas e existir uma ou mais
    @Test
    public void consultarTodasAsSimulacoesTest(){
        given()
        .when()
                .get(Data.API_SIMULACOES)
        .then()
            .statusCode(200);
    }

    //Retorna HTTP Status 204 se não existir simulações cadastradas (Esse teste só passa se a lista estiver vazia)
    @Test
    public void consultaListaVaziaTest(){
        given()
        .when()
            .get(Data.API_SIMULACOES)
        .then()
            .statusCode(204);
    }

}
