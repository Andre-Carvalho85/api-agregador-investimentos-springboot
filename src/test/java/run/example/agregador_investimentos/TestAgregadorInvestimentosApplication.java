package run.example.agregador_investimentos;

import org.springframework.boot.SpringApplication;

public class TestAgregadorInvestimentosApplication {

	public static void main(String[] args) {
		SpringApplication.from(AgregadorInvestimentosApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
