package br.com.selectiveprocess.Controllers;

import br.com.selectiveprocess.Exception.*;
import br.com.selectiveprocess.Models.Candidato;
import br.com.selectiveprocess.Views.CandidatoView;

import java.util.*;

public class ProcessoSeletivoController {
    private Map<String, Candidato> ListaDeCandidatos;
    private final Double SalarioBase = 2000.00;

    public ProcessoSeletivoController() {
        ListaDeCandidatos = new HashMap<String, Candidato>();
    }

    public Map<String, Candidato> getListaDeCandidatos() {
        return ListaDeCandidatos;
    }

    public boolean verificarQuantidadeCandidatos() throws ExceptionCustom{
        return ListaDeCandidatos.isEmpty();
    }
    public boolean verificarSeExisteCandidato(String cpfCandidato) throws ExceptionCustom {
        if(verificarQuantidadeCandidatos()) {
            return ListaDeCandidatos.containsKey(cpfCandidato);
        } else {
            return true;
        }
    }

    public void AdicionarCandidato() throws ExceptionCustom {
        CandidatosControllers cc = new CandidatosControllers();
        Candidato candidato = cc.CriarCandidato();
        if(verificarQuantidadeCandidatos()) {
            if(!verificarSeExisteCandidato(candidato.getCpf())) {
                ListaDeCandidatos.put(candidato.getCpf(), candidato);
            } else {
                throw new ExceptionCustom("Ocorreu um erro ao cadastrar o novo candidato: ",
                        new DuplicateCpfException("CPF já cadastrado"));
            }
        } else {
            ListaDeCandidatos.put(candidato.getCpf(), candidato);
        }
    }
    public void excluirCandidato(String cpfCandidato) throws ExceptionCustom {
        if(verificarSeExisteCandidato(cpfCandidato)) {
            ListaDeCandidatos.remove(cpfCandidato);
        } else {
            throw new ExceptionCustom(
                    "Erro ao excluir o candidato: ",
                    new NoExistsCandidatoForCpfException("Não há candidatos com esse CPF no processo seletivo."));
        }
    }
    public void verificarSalarioPretendido (Candidato candidato){
        if (candidato.getSalarioPretendido() < SalarioBase){
            System.out.printf("\nLigar para o Candidato: %s \n", candidato.getNome());
        } else if (candidato.getSalarioPretendido().equals(SalarioBase)){
            System.out.printf("\nLigar para o Candidato: %s com Contra Proposta\n", candidato.getNome());
        } else {
            System.out.printf("\nCandidato: %s está aguardando o resultado dos demais candidatos\n",candidato.getNome());
        }
    }
    public void analisarCandidatos() throws ExceptionCustom{
        if(verificarQuantidadeCandidatos()) {
            ListaDeCandidatos.forEach((cpf, candidato) -> verificarSalarioPretendido(candidato));
        }
    }
    public void analisarCandidatoPorCpf (String cpfCandidato) throws ExceptionCustom{
        if(verificarSeExisteCandidato(cpfCandidato)) {
            Candidato candidato = getCandidato(cpfCandidato);
            verificarSalarioPretendido(candidato);
        } else {
            throw new ExceptionCustom(
                    "Erro ao analisar candidato: ",
                    new EmptyCandidatosException("Não há candidato com esse CPF: " + cpfCandidato));
        }
    }
    public Candidato getCandidato(String cpfCandidato) throws ExceptionCustom{
        if(verificarSeExisteCandidato(cpfCandidato)) {
            return ListaDeCandidatos.get(cpfCandidato);
        }
        throw new ExceptionCustom(
                "Erro ao tentar obter o candidato",
                new Exception("CPF Inválido: " + cpfCandidato)
        );
    }
    public void mostrarTodosCandidatos() throws ExceptionCustom{
        if(verificarQuantidadeCandidatos()) {
            ListaDeCandidatos.forEach((cpf, candidato) -> {
                try {
                    CandidatoView.exibirCandidato(candidato);
                } catch (ExceptionCustom e) {
                    throw new ShowingCandidatoException("Erro ao exibir candidato com CPF " + cpf + ": " + e.getMessage());
                }
            });
        } else {
            throw new ExceptionCustom("Erro ao exibir os candidatos",
                    new EmptyCandidatosException("Não há candidatos cadastrados"));
        }
    }
    public void mostrarDadosCandidato(String cpfCandidato) throws ExceptionCustom{
        if(verificarQuantidadeCandidatos()) {
            CandidatoView.exibirCandidato(getCandidato(cpfCandidato));
        } else {
            throw new ExceptionCustom("Erro ao exibir o candidato: " + cpfCandidato,
                    new EmptyCandidatosException("Não existe um candidato com esse CPF"));
        }
    }
}
