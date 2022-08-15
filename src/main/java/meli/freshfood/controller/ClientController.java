package meli.freshfood.controller;

import meli.freshfood.model.Client;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.ok(clientService.findAllClient());
    }

    @GetMapping("/findPurchase/{id}")
    public ResponseEntity<List<PurchaseOrder>> findAllPurchaseOrder(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findAllPurchaseOrder(id));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.save(client));
    }

    @PutMapping("/{id}")
    public Client update( @PathVariable Long id, @RequestBody Client client){
        client.setClientId(id);
        return clientService.update(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId){
        clientService.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
