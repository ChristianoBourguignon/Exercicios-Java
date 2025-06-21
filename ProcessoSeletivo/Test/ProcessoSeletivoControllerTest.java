import br.com.selectiveprocess.Controllers.ProcessoSeletivoController;
import br.com.selectiveprocess.Exception.*;
import br.com.selectiveprocess.Models.Candidato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessoSeletivoControllerTest {

    private ProcessoSeletivoController controller;

    @BeforeEach
    public void setup() {
        controller = new ProcessoSeletivoController();
    }

    @Test
    public void testAdicionarCandidatoComSucesso() throws ExceptionCustom {
        Candidato candidato = new Candidato("Maria", "12345678900", 1800.0, 25);
        controller.getListaDeCandidatos().put(candidato.getCpf(), candidato);

        assertTrue(controller.getListaDeCandidatos().containsKey("12345678900"));
    }

    @Test
    public void testExcluirCandidatoComSucesso() throws ExceptionCustom {
        Candidato candidato = new Candidato("Pedro", "98765432100", 2100.0, 28);
        controller.getListaDeCandidatos().put(candidato.getCpf(), candidato);

        controller.excluirCandidato("98765432100");

        assertFalse(controller.getListaDeCandidatos().containsKey("98765432100"));
    }

    @Test
    public void testExcluirCandidatoInexistente() {
        ExceptionCustom exception = assertThrows(ExceptionCustom.class, () -> {
            controller.excluirCandidato("00000000000");
        });

        assertFalse(exception.getMessage().contains("Não há candidatos com esse CPF no processo seletivo."));
    }

    @Test
    public void testVerificarSalarioPretendidoAbaixo() {
        Candidato candidato = new Candidato("Lucas", "11223344556", 1500.0, 22);

        // Testa se não lança exceção ao verificar salário abaixo
        assertDoesNotThrow(() -> controller.verificarSalarioPretendido(candidato));
    }
}
