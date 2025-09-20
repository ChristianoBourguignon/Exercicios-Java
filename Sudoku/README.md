# 🧩 Sudoku Game

Um jogo de Sudoku completo desenvolvido em Java com interface gráfica usando Swing. O projeto implementa um gerador automático de puzzles de Sudoku com diferentes níveis de dificuldade e validação em tempo real.

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com o **intuito principal de aperfeiçoar o uso de expressões lambda em Java**. Através da implementação de um jogo de Sudoku completo, foram exploradas diversas funcionalidades das lambdas, incluindo:

- **Stream API** para manipulação de coleções
- **Métodos funcionais** como `filter()`, `map()`, `anyMatch()`, `findFirst()`
- **Programação funcional** para validações e verificações
- **Expressões lambda** para simplificar código e melhorar legibilidade

## 📋 Índice

- [Objetivo do Projeto](#-objetivo-do-projeto)
- [Características](#-características)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Executar](#-como-executar)
- [Funcionalidades](#-funcionalidades)
- [Uso de Lambdas no Projeto](#-uso-de-lambdas-no-projeto)
- [Arquitetura](#-arquitetura)
- [Documentação da API](#-documentação-da-api)
- [Contribuição](#-contribuição)

## ✨ Características

- **Interface Gráfica Intuitiva**: Interface desenvolvida com Java Swing
- **Geração Automática**: Algoritmo que gera puzzles de Sudoku válidos automaticamente
- **Três Níveis de Dificuldade**: Fácil, Intermediário e Avançado
- **Validação em Tempo Real**: Verifica conflitos nas linhas, colunas e quadrantes
- **Feedback Sonoro**: Som de parabéns ao completar o jogo
- **Controles Intuitivos**: Botões para iniciar, finalizar, resetar e alterar dificuldade
- **Validação de Entrada**: Aceita apenas números de 1 a 9 em cada célula

## 🛠 Tecnologias Utilizadas

- **Java 8+**
- **Java Swing** - Interface gráfica
- **Java Sound API** - Reprodução de áudio
- **IntelliJ IDEA** - Ambiente de desenvolvimento

## 📁 Estrutura do Projeto

```
Sudoku/
├── src/
│   └── br/com/sudoku/
│       ├── Main.java                    # Ponto de entrada da aplicação
│       ├── controllers/
│       │   └── GameController.java      # Lógica principal do jogo
│       ├── model/
│       │   ├── AudioClip.java          # Gerenciamento de áudio
│       │   ├── GameDifficultyEnum.java # Enum para dificuldades
│       │   ├── GameStatusEnum.java     # Enum para status do jogo
│       │   └── SudokuCell.java         # Modelo de célula do Sudoku
│       ├── ui/
│       │   ├── View.java               # Interface principal
│       │   └── custom/
│       │       ├── BoardTemplate.java  # Template do tabuleiro
│       │       └── Button/             # Botões customizados
│       │           ├── ExitGameButton.java
│       │           ├── FinishGameButton.java
│       │           ├── ResetDifficultyGameButton.java
│       │           ├── ResetGameButton.java
│       │           └── StartGameButton.java
│       └── util/
│           └── LimitNumberInput.java   # Filtro para entrada de números
├── resources/
│   └── sounds/
│       └── parabens.wav               # Arquivo de áudio
└── README.md
```

## 🚀 Como Executar

### Pré-requisitos

- Java 8 ou superior instalado
- IDE compatível com Java (IntelliJ IDEA recomendado)

### Passos para Execução

1. **Clone ou baixe o projeto**
   ```bash
   git clone <url-do-repositorio>
   cd Sudoku
   ```

2. **Abra o projeto no IntelliJ IDEA**
   - File → Open → Selecione a pasta do projeto

3. **Execute a aplicação**
   - Navegue até `src/br/com/sudoku/Main.java`
   - Clique com o botão direito → Run 'Main.main()'
   - Ou use o atalho `Shift + F10`

4. **Alternativa via linha de comando**
   ```bash
   cd src
   javac -d ../out/production/Sudoku br/com/sudoku/*.java br/com/sudoku/**/*.java
   java -cp ../out/production/Sudoku br.com.sudoku.Main
   ```

## 🎮 Funcionalidades

### 🎯 Iniciar Jogo
- Clique em "Iniciar Jogo"
- Escolha a dificuldade: Fácil, Intermediário ou Avançado
- O tabuleiro é gerado automaticamente com números pré-preenchidos

### 📝 Preenchimento
- Clique em qualquer célula vazia
- Digite um número de 1 a 9
- O sistema valida automaticamente se há conflitos

### ✅ Finalizar Jogo
- Clique em "Finalizar Jogo"
- O sistema verifica se o Sudoku está completo e correto
- Receba feedback sobre o resultado

### 🔄 Resetar
- **Resetar Valores**: Limpa apenas os números inseridos pelo jogador
- **Resetar Dificuldade**: Permite escolher uma nova dificuldade

### 🔊 Feedback Sonoro
- Som de parabéns é reproduzido ao completar o jogo com sucesso

## 🔧 Uso de Lambdas no Projeto

O projeto demonstra o uso extensivo de **expressões lambda** e **Stream API** em Java. Aqui estão alguns exemplos práticos implementados:

### Validação de Duplicatas
```java
// Verifica duplicatas em linha usando lambda
public boolean hasDuplicateInRow(SudokuCell target) {
    return gameBoard.values().stream()
            .filter(cell -> cell.getRow() == row)
            .anyMatch(cell -> cell != target && value.equals(cell.getValue()));
}
```

### Verificação de Células Vazias
```java
// Encontra células vazias usando Stream API
private boolean isHaveCellEmpty() {
    Optional<SudokuCell> isHaveCellEmpty = gameBoard.values().stream()
            .filter(cell -> !cell.isFixed())
            .filter(cell -> {
                Integer value = cell.getValue();
                return value == null || value == 0;
            })
            .findFirst();
    return isHaveCellEmpty.isPresent();
}
```

### Ordenação e Renderização
```java
// Ordena células por posição e renderiza usando lambda
this.gameBoard.getMap().entrySet().stream()
    .sorted(Comparator.comparingInt((Map.Entry<JTextField, SudokuCell> e) -> e.getValue().getRow())
            .thenComparingInt(e -> e.getValue().getCol()))
    .forEach(entry -> this.boardPanel.add(entry.getKey()));
```

### Limpeza de Células
```java
// Limpa células não fixas usando Stream API
gameBoard.entrySet().stream()
        .filter(entry -> !entry.getValue().isFixed())
        .forEach(entry -> {
            entry.getValue().setValue(null);
            entry.getKey().setText("");
        });
```

## 🏗 Arquitetura

### Padrão MVC (Model-View-Controller)

- **Model**: Classes `SudokuCell`, `GameDifficultyEnum`, `GameStatusEnum`, `AudioClip`
- **View**: Classe `View` e componentes de UI customizados
- **Controller**: Classe `GameController` que gerencia a lógica do jogo

### Fluxo Principal

1. **Inicialização**: `Main.java` cria a interface `View`
2. **Seleção de Dificuldade**: Usuário escolhe através de dialog
3. **Geração do Puzzle**: `GameController` gera um Sudoku válido
4. **Remoção de Células**: Remove células baseado na dificuldade
5. **Interação do Usuário**: Validação em tempo real
6. **Finalização**: Verificação de completude e correção

### Algoritmo de Geração

O jogo utiliza um algoritmo sofisticado para gerar Sudokus válidos:

1. **Preenchimento Diagonal**: Preenche os blocos 3x3 da diagonal principal
2. **Backtracking**: Completa o resto do tabuleiro usando backtracking
3. **Remoção Seletiva**: Remove células baseado na dificuldade escolhida

## 📚 Documentação da API

### GameController

Classe principal que gerencia toda a lógica do jogo.

#### Métodos Principais

```java
// Inicia um novo jogo
public GameStatusEnum startedGame()

// Verifica se há duplicatas na linha, coluna ou bloco
public boolean hasDuplicateInRowOrColumnOrBlock(SudokuCell target)

// Finaliza o jogo e retorna o status
public GameStatusEnum finishGame()

// Limpa os valores inseridos pelo jogador
public void clearGame()
```

### SudokuCell

Modelo que representa uma célula do tabuleiro.

```java
public class SudokuCell {
    private final int row;        // Linha da célula
    private final int col;        // Coluna da célula
    private Integer value;        // Valor da célula (1-9 ou null)
    private boolean isFixed;      // Se a célula é fixa (pré-preenchida)
}
```

### Enums

#### GameDifficultyEnum
- `EASY`: 30 células removidas
- `MEDIUM`: 50 células removidas  
- `HARD`: 60 células removidas

#### GameStatusEnum
- `NON_STARTED`: Jogo não iniciado
- `STARTED`: Jogo iniciado
- `IN_STARTED`: Jogo em andamento
- `INCOMPLETE`: Jogo incompleto
- `COMPLETE`: Jogo completo e correto
- `HAS_ERROR`: Jogo contém erros

## 🎨 Interface do Usuário

### Layout
- **Tabuleiro 9x9**: Grade principal com bordas destacadas para quadrantes
- **Botões de Controle**: Iniciar, Finalizar, Resetar e Alterar Dificuldade
- **Feedback Visual**: Células fixas em cinza, editáveis em branco

### Validação de Entrada
- Apenas números de 1 a 9 são aceitos
- Máximo de 1 dígito por célula
- Validação em tempo real de conflitos

## 🔧 Personalização

### Adicionando Novos Níveis de Dificuldade

1. Adicione novo valor ao `GameDifficultyEnum`
2. Implemente a lógica no método `getCellsToRemove()` do `GameController`
3. Atualize a interface para incluir a nova opção

### Modificando o Feedback Sonoro

1. Substitua o arquivo `resources/sounds/parabens.wav`
2. Ou modifique a classe `AudioClip` para suportar outros formatos

## 🐛 Solução de Problemas

### Problemas Comuns

1. **Erro de Compilação**: Verifique se o Java 8+ está instalado
2. **Áudio não funciona**: Verifique se o arquivo `parabens.wav` existe
3. **Interface não aparece**: Verifique se o Swing está disponível

### Logs e Debug

O projeto utiliza `System.out.println()` para debug. Para logs mais avançados, considere integrar uma biblioteca como Log4j.

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

### Sugestões de Melhorias

- [ ] Implementar sistema de pontuação
- [ ] Adicionar timer de jogo
- [ ] Criar sistema de dicas
- [ ] Implementar salvamento de progresso
- [ ] Adicionar temas visuais
- [ ] Criar modo multiplayer

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

**Christian Bourguignon**
- Projeto desenvolvido como exercício de programação em Java

---

⭐ Se este projeto foi útil para você, considere dar uma estrela!
