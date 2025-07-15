# 📧 ServicesMarketing

Um sistema de marketing digital desenvolvido em Java que demonstra boas práticas de programação, incluindo logging estruturado, tratamento de exceções customizadas e uso de interfaces para diferentes canais de comunicação.

## 🚀 Funcionalidades

### 📨 Canais de Comunicação
- **Email**: Envio de mensagens via email
- **SMS**: Envio de mensagens via SMS
- **WhatsApp**: Envio de mensagens via WhatsApp

### 🛠️ Tecnologias e Conceitos Implementados

#### 📝 Sistema de Logging
- **Logback + SLF4J**: Logging estruturado com rotação de arquivos
- **Logs diários**: Arquivos de log organizados por data (`logs/aplication-YYYY-MM-DD.log`)
- **Níveis de log**: Debug, Info, Warn, Error
- **Interface Logger**: Implementação de interface para logging consistente

#### 🎯 Interfaces e Polimorfismo
- **Interface `SendMessages`**: Abstração para diferentes serviços de envio
- **Implementações específicas**: `EmailService`, `SmsService`, `WhatsappService`
- **Polimorfismo**: Uso de interface para tratar diferentes tipos de serviço uniformemente

#### ⚠️ Tratamento de Exceções Customizadas
- **`ExceptionCustom`**: Classe base para exceções customizadas
- **`NumberLenghtInvalidExeception`**: Validação de números de telefone (11 dígitos)
- **`MapContactsInvalidException`**: Controle de duplicação de contatos
- **Validações robustas**: Nome, email, número de telefone

#### 🏗️ Arquitetura MVC
- **Controllers**: `ContactControllers` - Gerenciamento de contatos
- **Models**: `Person`, `SendMessages`, `Logger` - Entidades e interfaces
- **Services**: Serviços específicos para cada canal de comunicação
- **Views**: `PersonView` - Interface de usuário

## 📋 Pré-requisitos

- Java 23+
- Maven 3.6+

## 🚀 Como Executar

1. **Clone o repositório**
```bash
git clone [url-do-repositorio]
cd ServicesMarketing
```

2. **Compile o projeto**
```bash
mvn clean compile
```

3. **Execute a aplicação**
```bash
mvn exec:java -Dexec.mainClass="br.com.servicesmarketing.Main"
```

## 📁 Estrutura do Projeto

```
ServicesMarketing/
├── src/main/java/br/com/servicesmarketing/
│   ├── Controllers/
│   │   └── ContactControllers.java
│   ├── Exception/
│   │   ├── ExceptionCustom.java
│   │   ├── MapContactsInvalidException.java
│   │   └── NumberLenghtInvalidExeception.java
│   ├── Models/
│   │   ├── Logger.java
│   │   ├── Person.java
│   │   └── SendMessages.java
│   ├── Services/
│   │   ├── EmailService.java
│   │   ├── SmsService.java
│   │   └── WhatsappService.java
│   ├── Views/
│   │   └── PersonView.java
│   └── Main.java
├── src/main/resources/
│   └── logback.xml
├── logs/
│   └── aplication-YYYY-MM-DD.log
└── pom.xml
```

## 🔧 Configurações

### Logging
O sistema utiliza Logback configurado em `src/main/resources/logback.xml`:
- Logs diários com rotação automática
- Formato: `YYYY-MM-DD HH:mm:ss LEVEL LOGGER - MESSAGE`
- Armazenamento em `logs/aplication-YYYY-MM-DD.log`

### Validações
- **Nome**: Mínimo 3 caracteres
- **Email**: Deve conter "@"
- **Telefone**: Exatamente 11 dígitos
- **Contatos**: Não permite duplicação por nome

## 💡 Exemplo de Uso

```java
// Criar contatos
Person p1 = new Person("João", "11999999999", "joao@email.com");
Person p2 = new Person("Maria", "11888888888", "maria@email.com");

// Adicionar ao controller
ContactControllers cc = new ContactControllers();
cc.addContacts(p1);
cc.addContacts(p2);

// Enviar mensagens por diferentes canais
for (Person person : cc.getContacts().values()) {
    SendMessages email = new EmailService(person);
    email.sendMessage("Promoção especial!");
    
    SendMessages sms = new SmsService(person);
    sms.sendMessage("Confira nossa oferta!");
    
    SendMessages whatsapp = new WhatsappService(person);
    whatsapp.sendMessage("Novidades para você!");
}
```

## 🎯 Conceitos Demonstrados

### ✅ Boas Práticas
- **Separação de responsabilidades** (MVC)
- **Interfaces e polimorfismo**
- **Tratamento de exceções customizadas**
- **Logging estruturado**
- **Validações robustas**

### 🔄 Padrões de Design
- **Strategy Pattern**: Diferentes estratégias de envio
- **Factory Pattern**: Criação de serviços de comunicação
- **Observer Pattern**: Logging de eventos

## 📊 Logs de Exemplo

```
2025-01-15 10:30:15 DEBUG ContactControllers - Adicionando novo contato: João
2025-01-15 10:30:15 INFO  EmailService - Enviando email para joao@email.com
2025-01-15 10:30:15 ERROR Person - Erro na validação do número: NumberLenghtInvalidExeception
```

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---

**Desenvolvido com ❤️ em Java, demonstrando conceitos avançados de programação orientada a objetos, logging estruturado e tratamento de exceções.** 