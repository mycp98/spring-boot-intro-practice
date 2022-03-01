package car;

import org.springframework.web.bind.annotation.*;

import java.util.List;
//API layer, contains all the http requests
//talks to the CarService
@RestController
public class CarController {
    private CarService carService; //create instance carService

    public CarController(CarService carService) {   //constructor
        this.carService = carService;
    }

    @PostMapping(path = "cars")  //not returning anything
    public void addCar(@RequestBody Car car) {
        carService.registerNewCar(car);
    }

    @GetMapping(path = "cars")
    public List<Car> getCars() {
        return carService.getCars();
    }


    //Afternoon Task:
    @GetMapping(path = "cars/{id}") //returning Car
    public Car getCarById(@PathVariable("id") Integer carId) {

        return carService.getCarById(carId);
    }

    @DeleteMapping(path = "cars/{id}")   //not returning anything
    public void deleteCarById(@PathVariable("id") Integer carId) {
        carService.deleteCarById(carId);
    }

    @PutMapping(path = "cars/{id}") //not returning anything
    public void updateCar(@PathVariable("id") Integer carId, @RequestBody Car update) {
        carService.updateCarById(carId, update);
    }
}
