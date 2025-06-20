import br.com.selectiveprocess.Controllers.ProcessoSeletivoController;
import br.com.selectiveprocess.Exception.ExceptionCustom;
import br.com.selectiveprocess.Models.Candidato;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CriarListaCandidatos {

    @Test
    void AdicionarCandidatoNaLista() throws ExceptionCustom {
        // Arrange (preparação dos dados)
        String nome = "Christiano";
        String cpf = "12312312312";
        double pretensaoSalarial = 200.00;
        int idade = 25;
        Candidato candidato = new Candidato(nome, cpf, pretensaoSalarial, idade);
        Map<String, Candidato> listaCandidatos = new HashMap<String,Candidato>();
        listaCandidatos.put(candidato.getCpf(), candidato);
        ProcessoSeletivoController ps = new ProcessoSeletivoController();


        // Act (ação que será testada)
//        ps.AdicionarCandidato(candidato);


        // Assert (verificações)
//        assertEquals(listaCandidatos.size(), ps.getListaDeCandidatos().size());
//        assertEquals(candidato.getCpf(), ps.getListaDeCandidatos().get(candidato.getCpf()).getCpf());
//        assertEquals(candidato.getNome(), ps.getListaDeCandidatos().get(candidato.getCpf()).getNome());
//        assertEquals(candidato.getIdade(), ps.getListaDeCandidatos().get(candidato.getCpf()).getIdade());
//        assertEquals(candidato.getSalarioPretendido(), ps.getListaDeCandidatos().get(candidato.getCpf()).getSalarioPretendido());

    }
}
