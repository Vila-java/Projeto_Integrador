package meli.freshfood.controller;

import meli.freshfood.dto.SupervisorDetailsDTO;
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

	//Criar um novo supervisor.
	@PostMapping()
	public ResponseEntity<Supervisor> create (@RequestBody Supervisor supervisor){
		return ResponseEntity.status(HttpStatus.CREATED).body(supervisorService.create(supervisor));
	}

	//Buscar um Supervisor por ID.
	@GetMapping("/{id}")
	public ResponseEntity<Supervisor> findById(@PathVariable Long id){
		return ResponseEntity.ok(supervisorService.findById(id));
	}

	//Buscar todos os Supervisores.
	@GetMapping()
	public ResponseEntity<List<Supervisor>> findAll (){
		return ResponseEntity.ok(supervisorService.findAll());
	}

	//Atualizar um Supervisor com o ID.
	@PutMapping("/{id}")
	public ResponseEntity<Supervisor> updateById (@RequestBody Supervisor supervisor){
		return ResponseEntity.status(HttpStatus.CREATED).body(supervisorService.updateById(supervisor));
	}

	//Deletar um Superviror
	@DeleteMapping("/{supersivorId}")
	public ResponseEntity<Void> deleteById (@PathVariable Long supersivorId){
		supervisorService.deleteById(supersivorId);

		return ResponseEntity.noContent().build();
	}

	//Listar todos os Supervisores por Warehouse
	@GetMapping("/warehouseList/{warehouseSupervisor}")
	public ResponseEntity<List<SupervisorDetailsDTO>> findAllByWarehouseSupervisor(@PathVariable String warehouseSupervisor){
		List<SupervisorDetailsDTO> supervisorDetailsDTOList = supervisorService.findAllByWarehouseSupervisor(warehouseSupervisor);

		return ResponseEntity.ok(supervisorDetailsDTOList);

		//TODO: Adicionar Exception
		//throw new BadRequestException("Warehouse do Supervisor não encontrado!");
	}
}