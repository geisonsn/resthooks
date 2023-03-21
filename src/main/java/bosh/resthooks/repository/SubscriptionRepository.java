package bosh.resthooks.repository;

import bosh.resthooks.model.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Repository
public class SubscriptionRepository {

    private static Integer lastItem = 3;

    private static Flux<Subscription> subscriptions = Flux.just(
        new Subscription(1, "subscriptions/1", "abc123", "http://localhost:8080/subscriptions/1", "hook.create", "Topic to create hooks", true),
        new Subscription(2, "subscriptions/2", "def456", "http://localhost:8080/subscriptions/2", "hook.update", "Topic to update hooks", true),
        new Subscription(3, "subscriptions/3", "ghi789", "http://localhost:8080/subscriptions/3", "hook.delete", "Topic to delete hooks", true)
    );

    /**
     * List all subscriptions
     * @return a Flux
     * @throws ResponseStatusException if no subscriptions found
     */
    public Flux<Subscription> findAll() {
        return subscriptions
            .sort(Comparator.comparing(Subscription::getId))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No subscriptions found")));
    }

    /**
     * Get a subscription by ID
     * @param id
     * @return a Mono
     * @throws ResponseStatusException if no subscription being found
     */
    public Mono<Subscription> find(Integer id) {
        Flux<Subscription> flux = subscriptions
            .filter(s -> s.getId().intValue() == id.intValue())
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subscription %d not found", id))));
        return flux.next();
    }

    /**
     * Save a subscription
     * @param su a subscription to be saved
     * @return a Mono
     */
    public Mono<Subscription> save(Subscription su) {
        var id = getNextId();
        su.setId(id);
        su.setStatus(true);
        Mono<Subscription> newSub = Mono.just(su);
        subscriptions = Flux.concat(newSub, subscriptions);
        return newSub;
    }

    /**
     * Update a subscription by ID
     * @param id
     * @param su a Subscription instance
     * @return a Mono
     * @throws ResponseStatusException if no subscription being found
     */
    public Mono<Subscription> update(Integer id, Subscription su) {
        Mono<Subscription> mono = find(id).map(s -> {
            if (s.getId().intValue() == id.intValue()) {
                s.setTopicId(su.getTopicId());
                s.setUrl(su.getUrl());
                s.setHookUri(su.getHookUri());
                s.setTopicName(su.getTopicName());
                s.setTopicDescription(su.getTopicDescription());
            }
            return s;
        });
        return mono;
    }

    /**
     * Remove a subscription by ID
      * @param id
     * @return a Mono
     * @throws ResponseStatusException if no subscription being found
     */
    public Mono<Subscription> remove(Integer id) {

        Mono<Subscription> subscriptionMono = find(id);

        Flux<Subscription> flux = subscriptions.filter(ho -> ho.getId().intValue() != id.intValue());

        subscriptions = flux;
        return subscriptionMono;

    }

    private Integer getNextId() {
        return ++lastItem;
    }

}
