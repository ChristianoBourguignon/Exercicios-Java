package br.com.selectiveprocess.Views;

import br.com.selectiveprocess.Controllers.ProcessoSeletivoController;
import br.com.selectiveprocess.Exception.ExceptionCustom;
import br.com.selectiveprocess.Models.Candidato;

public class CandidatoView {

    public static void exibirCandidato(Candidato candidato) throws ExceptionCustom {
        System.out.println("============================================");
        System.out.println("Nome do Candidato: " + candidato.getNome());
        System.out.println("Idade do Candidato: " + candidato.getIdade());
        System.out.println("CPF do Candidato: " + candidato.getCpf());
        System.out.println("Salário Pretendido: " + candidato.getSalarioPretendido());
    }
}
