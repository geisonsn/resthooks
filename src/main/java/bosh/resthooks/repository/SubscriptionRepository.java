package bosh.resthooks.repository;

import bosh.resthooks.model.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SubscriptionRepository {

    private static Integer lastItem = 3;

    private static Flux<Subscription> subscriptions = Flux.just(
        new Subscription(1, "subscriptions", "abc123", "http://localhost:8080/subscriptions", "hook.create", "Topic to create hooks", false),
        new Subscription(2, "subscriptions/2", "def456", "http://localhost:8080/subscriptions/2", "hook.update", "Topic to update hooks", false),
        new Subscription(3, "subscriptions/3", "ghi789", "http://localhost:8080/subscriptions/3", "hook.delete", "Topic to delete hooks", false)
    );

    public Flux<Subscription> findAll() {
        return subscriptions.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No subscriptions found")));
    }

    public Mono<Subscription> find(Integer id) {
        Flux<Subscription> flux = subscriptions
                .filter(s -> s.getId().intValue() == id.intValue())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subscription %d not found", id))));
        return flux.next();
    }

    public Mono<Subscription> save(Subscription h) {
        var id = getNextId();
        h.setId(id);
        h.setStatus(true);
        Mono<Subscription> newSub = Mono.just(h);
        subscriptions = Flux.concat(newSub, subscriptions);
        return newSub;
    }

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

    public void remove(Integer id) {
        Flux<Subscription> flux = subscriptions.filter(ho -> ho.getId().intValue() != id.intValue())
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subscription %d not found", id))));
        subscriptions = flux;
    }

    private Integer getNextId() {
        return ++lastItem;
    }

}
