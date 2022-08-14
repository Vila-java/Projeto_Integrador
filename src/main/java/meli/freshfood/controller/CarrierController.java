package meli.freshfood.controller;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.service.CarrierService;
import meli.freshfood.client.ViaCepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/carriers")
@Validated
public class CarrierController {

    @Autowired
    private CarrierService carrierService;

    @Autowired
    private ViaCepClient viaCepClient;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CarrierDTO save(@RequestBody CarrierDTO carrierDTO) {

        return carrierService.save(carrierDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CarrierDTO updateById(@RequestBody CarrierDTO carrierDTO, @PathVariable Long id) {
        carrierDTO.setId(id);
        return carrierService.updateById(carrierDTO);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<CarrierDTO> findaAll() {
        return carrierService.findaAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CarrierDTO findById(@PathVariable Long id) {
        return carrierService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        carrierService.deleteById(id);
    }
}