package bosh.resthooks.controller;

import bosh.resthooks.model.Subscription;
import bosh.resthooks.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private SubscriptionService service;

    private SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping()
    public Flux<Subscription> get() {
        return service.list();
    }

    @GetMapping("{idSubscription}")
    public Mono<ResponseEntity<Subscription>> get(@PathVariable("idSubscription")  Integer id) {
        return service.get(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Subscription> create(@RequestBody Subscription s) {
        return service.save(s);
    }

    @PutMapping("{idSubscription}")
    public Mono<ResponseEntity<Subscription>> update(@PathVariable("idSubscription") Integer id, @RequestBody Subscription s) {
        return service.update(id, s)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{idSubscription}")
    public void delete(@PathVariable("idSubscription")  Integer id) {
         service.delete(id);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleException(ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(exception.getMessage());
    }
}


