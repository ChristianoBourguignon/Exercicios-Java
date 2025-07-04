# 🏦 Sistema de Conta Bancária

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Um sistema de conta bancária desenvolvido em Java que permite realizar operações bancárias básicas com controle de saldo, cheque especial e pagamento de boletos.

## 📋 Índice

- [Funcionalidades](#-funcionalidades)
- [Requisitos](#-requisitos)
- [Instalação](#-instalação)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Testes](#-testes)
- [Contribuindo](#-contribuindo)
- [Exercício Original](#-exercício-original)

## ✨ Funcionalidades

- ✅ **Consultar saldo** - Visualizar o saldo atual da conta
- ✅ **Consultar cheque especial** - Verificar o limite disponível
- ✅ **Depositar dinheiro** - Adicionar valores à conta
- ✅ **Sacar dinheiro** - Retirar valores da conta
- ✅ **Pagar boleto** - Realizar pagamentos de boletos
- ✅ **Verificar uso do cheque especial** - Monitorar se a conta está usando o limite

## 🔧 Requisitos

- Java 11 ou superior
- Maven 3.6 ou superior
- IDE compatível com Java (recomendado: IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Instalação

1. **Clone o repositório**
   ```bash
   git clone https://github.com/ChristianoBourguignon/Exercicios-Java/tree/master/ContaBancaria.git
   cd ContaBancaria
   ```

2. **Compile o projeto**
   ```bash
   mvn clean compile
   ```

3. **Execute os testes**
   ```bash
   mvn test
   ```

4. **Execute a aplicação**
   ```bash
   mvn exec:java -Dexec.mainClass="br.com.contaBancaria.Main"
   ```

## 📁 Estrutura do Projeto

```
ContaBancaria/
├── src/
│   ├── main/java/br/com/contaBancaria/
│   │   ├── Controllers/          # Controladores da aplicação
│   │   ├── Exception/            # Classes de exceção customizadas
│   │   ├── Models/               # Modelos de dados
│   │   ├── Views/                # Interface de usuário
│   │   └── Main.java            # Classe principal
│   └── resources/               # Recursos da aplicação
├── src/test/java/               # Testes unitários
├── pom.xml                      # Configuração do Maven
└── README.md                    # Este arquivo
```

## 🧪 Testes

O projeto inclui uma suíte completa de testes unitários:

```bash
# Executar todos os testes
mvn test

# Executar testes específicos
mvn test -Dtest=CriacaoContaBancariaTest
```

### Testes disponíveis:
- `CriacaoContaBancariaTest` - Testa a criação de contas
- `ConsultarSaldoTest` - Testa consultas de saldo
- `AdicionarSaldoTest` - Testa depósitos
- `RetirarSaldoTest` - Testa saques
- `PagarBoletoTest` - Testa pagamentos de boletos

## 🤝 Contribuindo

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

### Diretrizes para contribuição:
- Mantenha o código limpo e bem documentado
- Adicione testes para novas funcionalidades
- Siga as convenções de nomenclatura do projeto
- Atualize a documentação quando necessário


## 📚 Exercício Original

> **Nota:** Este projeto foi desenvolvido como parte de um exercício de programação.

### Enunciado do Exercício:

1. **Escreva um código onde temos uma conta bancaria que possa realizar as seguintes operações:**
    - Consultar saldo
    - Consultar cheque especial
    - Depositar dinheiro
    - Sacar dinheiro
    - Pagar um boleto
    - Verificar se a conta está usando cheque especial

2. **Siga as seguintes regras para implementar:**
    - A conta bancária deve ter um limite de cheque especial somado ao saldo da conta
    - O valor do cheque especial é definido no momento da criação da conta, de acordo com o valor depositado na conta em sua criação
    - Se o valor depositado na criação da conta for de R$500,00 ou menos o cheque especial deve ser de R$50,00
    - Para valores acima de R$500,00 o cheque especial deve ser de 50% do valor depositado
    - Caso o limite de cheque especial seja usado, assim que possível a conta deve cobrar uma taxa de 20% do valor usado do cheque especial

---

## 📞 Suporte

Se você encontrar algum problema ou tiver dúvidas, por favor:

1. Verifique se o problema já foi reportado nas [Issues](../../issues)
2. Crie uma nova issue com detalhes sobre o problema
3. Para dúvidas gerais, abra uma discussão

## ⭐ Avaliação

Se este projeto foi útil para você, considere dar uma ⭐ no repositório!

---

**Desenvolvido com ❤️ em Java**
