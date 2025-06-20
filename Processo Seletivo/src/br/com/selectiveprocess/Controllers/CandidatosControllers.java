package br.com.selectiveprocess.Controllers;

import br.com.selectiveprocess.Exception.*;
import br.com.selectiveprocess.Models.Candidato;

import java.util.Scanner;

public class CandidatosControllers {
    public Candidato CriarCandidato() throws ExceptionCustom {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite o nome do candidato: ");
        String nomeCandidato = sc.nextLine();
        System.out.println("\nDigite o CPF do candidato: ");
        String cpfCandidato = sc.nextLine();
        System.out.println("\nDigite a idade do candidato: ");
        int idadeCandidato = sc.nextInt();
        System.out.println("\nDigite o salario pretendido do candidato: ");
        double salarioPretendido = sc.nextDouble();
        try {
        if(nomeCandidato.isEmpty() || cpfCandidato.isEmpty()) {
            throw new EmptyDataExeception("Campos obrigatórios não preenchidos.");
        }
        if (cpfCandidato.length() != 11) {
            throw new InvalidCpfException("Formato do CPF inválido.");
        }
        if (idadeCandidato < 18) {
            throw new InvalidAgeException("Idade mínima para candidatura é 18.");
        }

        return new Candidato(nomeCandidato,cpfCandidato,salarioPretendido,idadeCandidato);

        } catch (InvalidAgeException e){
            throw new ExceptionCustom(
                    "Ocorreu um erro: ",
                    new InvalidAgeException(e.getMessage())
            );
        } catch (EmptyDataExeception e){
            throw new ExceptionCustom(
                    "Ocorreu um erro: ",
                    new EmptyDataExeception(e.getMessage())
            );
        } catch (InvalidCpfException e){
            throw new ExceptionCustom(
                    "Ocorreu um erro: ",
                    new InvalidAgeException(e.getMessage()));
        }
    }
}
