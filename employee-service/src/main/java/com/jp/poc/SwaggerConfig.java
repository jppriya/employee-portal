package com.jp.poc;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Jayapriya", "", "jayapriyajp.vr@gmail.com");
	public static final ApiInfo DEFAULT_APIINFO = new ApiInfo("Api Documentation", "Api Description", "1.0", "urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_APIINFO)
				.produces(new HashSet(Arrays.asList(new String[] { "application/json" })))
				.consumes(new HashSet(Arrays.asList(new String[] { "application/json" })));
	}

}
