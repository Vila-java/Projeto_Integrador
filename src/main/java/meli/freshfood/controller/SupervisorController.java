package meli.freshfood.controller;

import meli.freshfood.model.Supervisor;
import meli.freshfood.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/fresh-products")
public class SupervisorController {

	@Autowired
	SupervisorService supervisorService;

	@PostMapping("/supervisor")
	public ResponseEntity<Supervisor> save (@RequestBody Supervisor supervisor){
		return ResponseEntity.status(HttpStatus.CREATED).body(supervisorService.save(supervisor));
	}

	@GetMapping("/supervisor/{id}")
	public ResponseEntity<Supervisor> findById(@PathVariable Long id){
		return ResponseEntity.ok(supervisorService.findById(id));
	}
}