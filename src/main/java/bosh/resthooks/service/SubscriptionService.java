package bosh.resthooks.service;

import bosh.resthooks.model.Subscription;
import bosh.resthooks.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubscriptionService {

    private SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    /**
     * List all subscriptions
     * @return a Flux
     * @throws ResponseStatusException if no subscription being found
     */
    public Flux<Subscription> list() {
        return repository.findAll();
    }

    /**
     * Get a subscription by ID
     * @param id
     * @return a Mono
     * @throws ResponseStatusException if no subscriptions found
     */
    public Mono<Subscription> get(Integer id) {
        return repository.find(id);
    }

    /**
     * Save a subscription
     * @param s a subscription to be saved
     * @return a Mono
     */
    public Mono<Subscription> save(Subscription s) {
        return repository.save(s);
    }

    /**
     * Update a subscription by ID
     * @param id
     * @param s a Subscription instance
     * @return a Mono
     * @throws ResponseStatusException if no subscription being found
     */
    public Mono<Subscription> update(Integer id, Subscription s) {
        return repository.update(id, s);
    }

    /**
     * Remove a subscription by ID
     * @param id
     * @return a Mono
     * @throws ResponseStatusException if no subscription being found
     */
    public Mono<Subscription> delete(Integer id) {
        return repository.remove(id);
    }
}
