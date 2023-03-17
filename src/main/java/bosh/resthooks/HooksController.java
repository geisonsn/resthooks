package bosh.resthooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class HooksController {

    private HooksService service;

    @Autowired
    private HooksController(HooksService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Hook> get() {
        return service.list();
    }

    @GetMapping("{idHook}")
    public Hook get(@PathVariable("idHook")  Integer id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hook create(@RequestBody Hook h) {
        return service.save(h);
    }

    @PutMapping("{idHook}")
    public void update(@PathVariable("idHook") Integer id, @RequestBody HookDTO h) {
        service.update(id, h);
    }

    @DeleteMapping("{idHook}")
    public void delete(@PathVariable("idHook")  Integer id) {
         service.remove(id);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleException(ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(exception.getMessage());
    }

    public record HookDTO(Integer id,  String url, String topicId, String hookUri, String topicName, String topicDescription){}
}


