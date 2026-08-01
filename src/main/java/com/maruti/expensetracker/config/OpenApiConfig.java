package com.maruti.expensetracker.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI expenseTrackerAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Smart Expense Tracker API")

                        .description("REST API for managing personal expenses")

                        .version("1.0.0")

                        .contact(new Contact()

                                .name("Maruti Hanumanth Naik")

                                .email("marutihanumanthnaik@gmail.com"))

                        .license(new License()

                                .name("MIT License")

                                .url("https://opensource.org/licenses/MIT"))

                )

                .externalDocs(

                        new ExternalDocumentation()

                                .description("Project Documentation")

                                .url("https://github.com/your-github/expense-tracker")

                );

    }

}