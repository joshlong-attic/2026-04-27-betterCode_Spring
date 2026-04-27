package com.example.adoptions.dogs;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
@Transactional
class DogsAdoptionsController {

    private final DogRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    DogsAdoptionsController(DogRepository repository, ApplicationEventPublisher applicationEventPublisher) {
        this.repository = repository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping("/dogs/{dogId}/adoptions")
    void adopt(@PathVariable int dogId, @RequestParam String owner) {
        repository.findById(dogId).ifPresent(dog -> {
            var updated = repository.save(
                    new Dog(dog.id(), dog.name(), dog.description(), owner));
            IO.println("adopted " + updated);
            applicationEventPublisher
                    .publishEvent(new DogAdoptedEvent(dogId));
        });

    }
}
