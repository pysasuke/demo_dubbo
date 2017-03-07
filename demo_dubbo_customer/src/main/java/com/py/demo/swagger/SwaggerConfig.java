package com.py.demo.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 2016-6-2
 *
 * @author ever Swagger 配置
 */
@Configuration
@EnableWebMvc //注解@EnableWebMvc以帮助我们减少bean的声明
@EnableSwagger
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*v1.*");
//				.swaggerGroup("v1");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "PY DEMO API",
                "PY DEMO—API,依赖MySQL数据库",
                "开发者: PY",
                "pysasuke@outlook.com",
                "MIT License",
                "/LICENSE");
        return apiInfo;
    }

//	@Bean
//	public Docket api(){
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.regex("/api/.*"))
//				.build()
//				.apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("TITLE")
//				.description("DESCRIPTION")
//				.version("VERSION")
//				.termsOfServiceUrl("http://terms-of-services.url")
//				.license("LICENSE")
//				.licenseUrl("http://url-to-license.com")
//				.build();
//	}

}
