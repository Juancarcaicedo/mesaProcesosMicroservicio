package husjp.api.plantilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class MesaProcesosMicroservicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MesaProcesosMicroservicioApplication.class, args);
    }

}
