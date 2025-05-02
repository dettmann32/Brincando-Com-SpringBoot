package com.elasticsearchAppTeset.elasticsearch;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.elasticsearchAppTeset.elasticsearch.CarRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/car")
class CarController{
    
    private final CarRepository carRepository;

    @PostMapping
    private ResponseEntity<Void> createCar(@RequestBody CarDto car){

        car novoCar = new car();
        novoCar.setName(car.name());
        novoCar.setColor(car.color());

        carRepository.save(novoCar);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private ResponseEntity<Iterable<car>> getAllCars(){
        Iterable<car> cars = carRepository.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    private ResponseEntity<car> getByIdCar(@PathVariable String id){
        car car = carRepository.findById(id).orElse(null);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/search-match")
    public List<car> searchByName(@RequestParam String name) {
        return carRepository.searchByNameMatch(name);
    }

    @GetMapping("/search-color")
    public List<car> searchByColor(@RequestParam String color) {
        return carRepository.searchByColorWildcard(color);
    }

    @GetMapping("/search-advanced")
    public List<car> searchByNameAndColor(@RequestParam String name, @RequestParam String color) {
        return carRepository.searchByNameAndColorAdvanced(name, color);
    }
}



