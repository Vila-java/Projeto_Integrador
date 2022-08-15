package meli.freshfood.controller;

import meli.freshfood.model.Supervisor;
import meli.freshfood.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/fresh-products/supervisor")
public class SupervisorController {

	@Autowired
	SupervisorService supervisorService;

	@PostMapping()
	public ResponseEntity<Supervisor> create (@RequestBody Supervisor supervisor){
		return ResponseEntity.status(HttpStatus.CREATED).body(supervisorService.create(supervisor));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Supervisor> findById(@PathVariable Long id){
		return ResponseEntity.ok(supervisorService.findById(id));
	}

	@GetMapping()
	public ResponseEntity<List<Supervisor>> findAll (){
		return ResponseEntity.ok(supervisorService.findAll());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Supervisor> update (@RequestBody Supervisor supervisor){
		return ResponseEntity.status(HttpStatus.CREATED).body(supervisorService.update(supervisor));
	}
}