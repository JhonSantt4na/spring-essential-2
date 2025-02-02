package spring.santt4na.spring_introduction.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*
 * Essa anotação combina três outras anotações importantes:
 * 
 * @EnableAutoConfiguration → Habilita a configuração automática do Spring
 * Boot.
 * 
 * @ComponentScan → Permite que o Spring escaneie automaticamente os pacotes
 * e registre os beans.
 * 
 * @Configuration → Define a classe como uma fonte de configurações da
 * aplicação.
 */

@ComponentScan(basePackages = "spring.santt4na.spring_introduction")
// Porem como o nosso Start não esta na raiz presisamos informa onde precisa
// escanear buscando o @Compents, @Controllers, @Services etc...
public class ApplicationStart {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args); // Inicializa a aplicação Spring Boot
		/*
		 * SpringApplication.run(MinhaAplicacao.class, args) é o ponto de entrada para
		 * inicializar uma aplicação Spring Boot, configurando o ambiente da aplicação,
		 * os beans e o servidor (se for uma aplicação web).
		 * 
		 * Ele aceita a classe principal da aplicação (onde o Spring Boot vai procurar a
		 * configuração) e os argumentos passados na linha de comando.
		 * 
		 */
	}

}
