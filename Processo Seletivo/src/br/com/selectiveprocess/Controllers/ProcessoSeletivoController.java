package br.com.selectiveprocess.Controllers;

import br.com.selectiveprocess.Exception.DuplicateCpfException;
import br.com.selectiveprocess.Exception.EmptyCandidatosException;
import br.com.selectiveprocess.Exception.ExceptionCustom;
import br.com.selectiveprocess.Exception.ShowingCandidatoException;
import br.com.selectiveprocess.Models.Candidato;
import br.com.selectiveprocess.Views.CandidatoView;

import java.util.*;

public class ProcessoSeletivoController {
    private Map<String, Candidato> ListaDeCandidatos;
    private final Double SalarioBase = 2000.00;

    public ProcessoSeletivoController() {
        ListaDeCandidatos = new HashMap<String, Candidato>();
    }


    public boolean verificarQuantidadeCandidatos() throws ExceptionCustom{
        if (ListaDeCandidatos.isEmpty()){
            throw new ExceptionCustom(
                    "Erro ao verificar candidatos: ",
                    new EmptyCandidatosException("Não há candidatos criados para o processo seletivo."));
        }
        return true;
    }
    public boolean verificarUsoCpf(String cpfCandidato) throws ExceptionCustom{
        if(ListaDeCandidatos.isEmpty()) {
            return true;
        } else {
            return !ListaDeCandidatos.containsKey(cpfCandidato);
        }
    }
    public boolean verificarSeExisteCandidato(String cpfCandidato) throws ExceptionCustom {
        verificarQuantidadeCandidatos();
        if(!ListaDeCandidatos.containsKey(cpfCandidato)){
            throw new ExceptionCustom(
                    "Erro ao verificar candidato: ",
                    new EmptyCandidatosException("Não há candidato com esse CPF: " + cpfCandidato));
        }
        return true;
    }
    public void AdicionarCandidato() throws ExceptionCustom {
        CandidatosControllers cc = new CandidatosControllers();
        Candidato candidato = cc.CriarCandidato();
        if(verificarUsoCpf(candidato.getCpf())) {
            ListaDeCandidatos.put(candidato.getCpf(), candidato);
            System.out.println("\nCandidato criado com sucesso\n");
            mostrarMenu();
        } else {
            throw new ExceptionCustom("Ocorreu um erro ao cadastrar o novo candidato: ", new DuplicateCpfException("CPF já cadastrado"));
        }
    }
    public void excluirCandidato(String cpf) throws ExceptionCustom {
        if(verificarSeExisteCandidato(cpf)) {
            ListaDeCandidatos.remove(cpf);
            System.out.println("\nCandidato removido com sucesso\n");
        }
    }
    public void verificarSalarioPretendido (Candidato candidato){
        if (candidato.getSalarioPretendido() < SalarioBase){
            System.out.printf("\nLigar para o Candidato: %s \n", candidato.getNome());
        } else if (candidato.getSalarioPretendido().equals(SalarioBase)){
            System.out.printf("\nLigar para o Candidato: %s com Contra Proposta\n", candidato.getNome());
        } else {
            System.out.print("\nAguardando o resultado dos demais candidatos\n");
        }
    }
    public void analisarCandidatos() throws ExceptionCustom{
        verificarQuantidadeCandidatos();
        ArrayList <Candidato> candidatos = new ArrayList<>();
        ListaDeCandidatos.forEach((key, value) -> verificarSalarioPretendido(value));
    }
    public void analisarCandidatoPorCpf (String cpf) throws ExceptionCustom{
        if(verificarSeExisteCandidato(cpf)) {
            Candidato candidato = getCandidato(cpf);
            verificarSalarioPretendido(candidato);
        }
    }
    public Candidato getCandidato(String cpf) throws ExceptionCustom{
        if(verificarSeExisteCandidato(cpf)) {
            return ListaDeCandidatos.get(cpf);
        }
        throw new ExceptionCustom(
                "Erro ao tentar obter o candidato",
                new Exception("CPF Inválido: " + cpf)
        );
    }
    public void mostrarCandidatos() throws ExceptionCustom{
        if(verificarQuantidadeCandidatos()) {
            ListaDeCandidatos.forEach((cpf, candidato) -> {
                try {
                    CandidatoView.exibirCandidato(candidato);
                } catch (ExceptionCustom e) {
                    throw new ShowingCandidatoException("Erro ao exibir candidato com CPF " + cpf + ": " + e.getMessage());
                }
            });
            mostrarMenu();
        }
    }
    public void mostrarMenu(){
        Scanner sc = new Scanner(System.in);
        int opc = 9;
        while(opc!=-1) {
            System.out.println("=========================");
            System.out.println("1 - Voltar ao menu principal");
            System.out.println("0 - Sair");
            System.out.println("Digite o que você deseja:");
            opc = sc.nextInt();
            switch (opc) {
                case 1 -> {
                    opc= -1;
                }
                case 0 -> System.exit(0);
                default -> {
                    System.out.println("Numero inválido, veja as opções novamente!");
                }
            }
        }
    }
}
