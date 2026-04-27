package com.example.adoptions.cats;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices(CatFactsClient.class)
@Configuration
class CatsConfiguration {

}
