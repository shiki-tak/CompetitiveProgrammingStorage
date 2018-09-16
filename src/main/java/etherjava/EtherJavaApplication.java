package etherjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EtherJavaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EtherJavaApplication.class, args);
//		EtherJavaApplication app = ctx.getBean(EtherJavaApplication.class);
//		app.execBlockchain(args);
	}

//	private void execBlockchain(String[] args) {
//		SimulateBlockchain simulateBlockchain = new SimulateBlockchain();
//		simulateBlockchain.createGenesisBlock();
//	}
}
