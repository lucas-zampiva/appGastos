package tainersoft.raiz;

import static org.hamcrest.CoreMatchers.is;

import com.jayway.restassured.RestAssured;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import tainersoft.raiz.models.Lancamento;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({ "server.port:0", "spring.datasource.url:jdbc:h2:mem:webapi;DB_CLOSE_ON_EXIT=FALSE" })
public class LancamentosControllerTest {
    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void adicionarLancamentoTest() throws Exception {
        /* Criar o lancamento que será enviado para o método do controller */
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao("Confraternização de Final do semestre da turma de sexta!");
        lancamento.setValor(30.45f);

        RestAssured.given()
        .contentType("application/json")
        .body(lancamento) // por ser post, o objeto lancamento é enviado no corpo
        .when()
        .post("/lancamentos") // endereço do post
        .then()
        .body("id", is(1)); //teste
    }
}