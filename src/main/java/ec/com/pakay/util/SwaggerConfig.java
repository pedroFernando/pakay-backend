package ec.com.pakay.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

@Configuration
public class SwaggerConfig {
    private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<>(Arrays.asList("application/json"));

    @Bean
    public Docket api() {
        RequestParameterBuilder requestParameterBuilder = new RequestParameterBuilder();
        requestParameterBuilder.name("Authorization")
                .description("JWT Token")
                .in(ParameterType.HEADER)
                .required(true)
                .build();
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(requestParameterBuilder.build());
        return new Docket(DocumentationType.OAS_30)
                .produces(DEFAULT_PRODUCES_CONSUMES)
                .consumes(DEFAULT_PRODUCES_CONSUMES)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ec.com.isoftech.pakay.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointInfo())
                .globalRequestParameters(parameters);
    }

    private ApiInfo apiEndPointInfo() {
        return new ApiInfoBuilder().title("Pakay API")
                .description("Endpoints para administración de cajas de ahorro")
                .license("Apache 2.0")
                .version("1.0.0")
                .licenseUrl("http://www.apache.org/license/LICENSE-2.0.html")
                .build();
    }

}
