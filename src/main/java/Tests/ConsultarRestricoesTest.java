/*
   Neste caso eu utilizei o método de particionamento de equivalência que consiste em atingir a máxima cobertura com o mínimo de casos de teste possível.
   Assim eu peguei as amostras dos seguintes casos:
   -CPF com restrição - 200
   -CPF sem restrição - 204
   -CPF inválido
*/
package Tests;

import Utils.Data;
import io.restassured.http.Method;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class ConsultarRestricoesTest {

    //Se não possui restrição do HTTP Status 204 é retornado
    // Teste Positivo - Verifica se o CPF não possui restrição - 204
    @Test
    public void consultarUmaRestrincaoPeloCPFPositivoTest(){
        given()
        .when()
            .get(Data.API_RESTRICOES+Data.CPF_SEM_RESTRICAO)
        .then()
            .statusCode(204);
    }

    //Se possui restrição o HTTP Status 200 é retornado com a mensagem "O CPF 99999999999 possui restrição"
    // Teste negativo - Verifica se o CPF possui restrição
    @Test
    public void consultarUmaRestrincaoPeloCPFNegativoTest(){
        given()
        .when()
            .get(Data.API_RESTRICOES+Data.CPF_COM_RESTRICAO)
        .then()
            .statusCode(200);
        Assert.assertEquals("{\"mensagem\":\"O CPF 97093236014 tem problema\"}", request(Method.GET, Data.API_RESTRICOES+Data.CPF_COM_RESTRICAO).getBody().asString());
    }

    //Teste Inválido - Verifica se há validação para CPF inválido
    @Test
    public void consultarUmaRestrincaoPeloCPFInvalidoTest(){
        given()
        .when()
            .get(Data.API_RESTRICOES+Data.CPF_INVALIDO)
        .then()
            .statusCode(204);
    }

}
