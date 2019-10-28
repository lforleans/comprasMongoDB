Este arquivo explica como compilar e executar os testes da aplicação:

Requisitos:

1. MongoDB (v. 4.2.1)
2. Java 8
3. Maven 

Após clonar o código, executar 'mvn test' dentro do diretório da aplicação. Todos os 38 testes serão executados e, 
dando tudo certo, todos eles terão sucesso.

A aplicação foi projetada da seguinte forma:

1. O pacote "model" contém as classes de modelo. Estas classes representam o domínio da aplicação e possuem a lógica de
negócio. Os testes destas classes (testes unitários), verificam se a lógica está correta.

2. O pacote "controller" contém as classes responsáveis por persistir os objetos da camada de domínio no banco de dados.
Seus testes são considerados testes de integração, pois precisam de classes e objetos que são oferecidos pelo Spring 
(conhecido como Contexto da aplicação). Os testes verificam se os métodos realizam as alterações corretas no banco de 
dados.

3. O pacote "repos" contém os repositórios, gerados automaticamente pelo Spring Data.

4. O pacote "exceptions" possui as exceções de sistema e de aplicação. Para este desafio, foram criadas as exceções
ModeloInvalidoException (checked, que representa alguma quebra de consistência em alguma classe do pacote "model") e 
DesafioException (unchecked). As checked exceptions são capturadas nos controladores e transformadas em unchecked 
exceptions, uma operação comum em sistemas web. 





