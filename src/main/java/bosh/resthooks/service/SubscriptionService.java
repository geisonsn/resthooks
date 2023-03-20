package bosh.resthooks.service;

import bosh.resthooks.model.Subscription;
import bosh.resthooks.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubscriptionService {

    private SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public Flux<Subscription> list() {
        return repository.findAll();
    }

    public Mono<Subscription> get(Integer id) {
        return repository.find(id);
    }

    public Mono<Subscription> save(Subscription s) {
        return repository.save(s);
    }

    public Mono<Subscription> update(Integer id, Subscription s) {
        return repository.update(id, s);
    }

    public void delete(Integer id) {
        repository.remove(id);
    }
}
