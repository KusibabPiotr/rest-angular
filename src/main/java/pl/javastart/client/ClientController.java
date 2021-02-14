package pl.javastart.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientRepo clientRepo;

    @Autowired
    public ClientController(ClientRepo clientRepo){
        this.clientRepo = clientRepo;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(@RequestBody Client client){
        String idNumber = client.getClientDetails().getIdNumber();
        Optional<Client> optional = clientRepo.findByClientDetailsIdNumber(idNumber);
        if (optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else {
            Client save = clientRepo.save(client);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(save.getId())
                    .toUri();
            return ResponseEntity.created(location).body(client);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllClients(@RequestParam(defaultValue = "lastName")String sortBy){
        List<Client> all = clientRepo.findAll();
        if (sortBy.equals("firstName")){
            all.sort(Comparator.comparing(Client::getFirstName));
        }else{
            all.sort(Comparator.comparing(Client::getLastName));
        }
        return ResponseEntity.ok(all);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id){
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isPresent()){
            return ResponseEntity.ok(optionalClient.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isPresent()){
            clientRepo.deleteById(id);
            return ResponseEntity.ok(optionalClient.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
