package bosh.resthooks;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HooksRepository  {

    private static final List<Hook> hooks = new ArrayList<>(){{
        add(new Hook(1, "subscriptions", "abc123", "http://localhost:8080/subscriptions", "hook.create", "Topic to create hooks"));
        add(new Hook(2, "subscriptions/2", "def456", "http://localhost:8080/subscriptions/2", "hook.update", "Topic to update hooks"));
        add(new Hook(3, "subscriptions/3", "ghi789", "http://localhost:8080/subscriptions/3", "hook.delete", "Topic to delete hooks"));
    }};

    public List<Hook> list() {
        return hooks;
    }

    public Hook get(Integer id) {
        if (!hooks.isEmpty()) {
            return hooks.stream()
                .filter(h -> h.getId().intValue() == id.intValue()).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Hook %d not found", id)));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hooks not found");
    }

    public Hook save(Hook h) {
        var id = getNextId(hooks);
        h.setId(id);
        hooks.add(h);
        return h;
    }

    public void update(Integer id, HooksController.HookDTO h) {
        Hook hook = this.get(id);
        hook.setTopicId(h.topicId());
        hook.setUrl(h.url());
        hook.setHookUri(h.hookUri());
        hook.setTopicName(h.topicName());
        hook.setTopicDescription(h.topicDescription());
    }

    public void remove(Integer id) {
        Hook hook = this.get(id);
        this.remove(hook);
    }
    private void remove(Hook hook) {
        hooks.removeIf(h -> h.getId().intValue() == hook.getId().intValue());
    }

    public Integer getNextId(List<Hook> hooks) {
        if (!hooks.isEmpty()) {
            return hooks.get(hooks.size()-1).getId() + 1;
        }
        return 1;
    }

}
