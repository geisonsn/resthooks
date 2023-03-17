package bosh.resthooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HooksService {

    private HooksRepository repository;

    @Autowired
    private HooksService(HooksRepository repository) {
        this.repository = repository;
    }

    public List<Hook> list() {
        return repository.list();
    }

    public Hook get(Integer id) {
        return repository.get(id);
    }

    public Hook save(Hook h) {
        return repository.save(h);
    }

   public void update(Integer id, HooksController.HookDTO h) {
       repository.update(id, h);
    }

    public void remove(Integer id) {
        repository.remove(id);
    }
}
