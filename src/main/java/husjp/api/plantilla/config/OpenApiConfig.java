package husjp.api.plantilla.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
