package husjp.api.plantilla.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;

//Permite que pondamos iniciar sesión con el token en swagger
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi innProducOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/ejemplo/**"};
        return GroupedOpenApi.builder()
                .group("ejemplo")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("InnProduc API").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }
    @Bean
    public GroupedOpenApi AreaServicio(@Value("2.3.0") String appVersion){
        String [] paths = {"/AreaServicio/**"};
        return GroupedOpenApi.builder()
                .group("AreaServicio")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Area Servicio").version(appVersion)
                        .description("metodos que obtienen las Areas de Servicio Hospital San Jose ")))
                .pathsToMatch(paths)
                .build();
    }
    
    @Bean
    public GroupedOpenApi ProcesosOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/procesos/**"};
        return GroupedOpenApi.builder()
                .group("Procesos")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("procesos API").version(appVersion)
                        .description("procesos de Areas de Servicio del Hospital San jose   ")))
                .pathsToMatch(paths)
                .build();
    }
    
    @Bean
    public GroupedOpenApi SubProcesosOpenApi(@Value("2.3.0") String appVersion){
      String [] paths = {"/subprocesos/**"};
        return GroupedOpenApi.builder()
                .group("subProcesos")
               .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("procesos API").version(appVersion)
                        .description("subprocesos de  Procesos de Areas de Servicio del Hospital San jose   ")))
               .pathsToMatch(paths)
                .build();
    }
}
