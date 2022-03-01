package car;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
//all the business logic in this class and talks to the CarDAO
@Service
public class CarService {
    private CarDAO carDAO; //making instance to connect to carDAO and pass it below

    public CarService(@Qualifier("postgres") CarDAO carDAO) {  //passing in the carDAO created and calling in the implementation called "postgres"
        this.carDAO = carDAO;
    }  //switch between "postgres" and "fake"

    public void registerNewCar(Car car) {
        // business logic. check if reg number is valid and not take
        if (car.getPrice() <= 0) {
            throw new IllegalStateException("Car price cannot be 0 or less");
        }
        //check if car already exists in db list, if it does throw an exception:
        Car carInDb = carDAO.selectCarById(car.getId());
        if(carInDb != null){
            throw new IllegalStateException("Car with the id " + car.getId() + " already exists in db. Can't register with the same id");
        }

        //after checking above, can insertCar
        int result = carDAO.insertCar(car);

        if (result != 1) {
            throw new IllegalStateException("Could not save car...");
        }
    }




    public List<Car> getCars() {
        return carDAO.selectAllCars();
    }


    public Car getCarById(Integer id){
        if(id ==null){
            throw new IllegalStateException("Id is invalid, cannot be null");
        }

        Car carToGet = carDAO.selectCarById(id);
        if(carToGet == null){
            throw new IllegalStateException("Id of Car cannot be found");
        } else {
            return carToGet; //returns id of the car
        }
    }

    public void deleteCarById(Integer id){
        if(id ==null){
            throw new IllegalStateException("Id is invalid, cannot be null");
        }
        Car carToDelete = carDAO.selectCarById(id);
        if(carToDelete == null){
            throw new IllegalStateException("Id of car to delete cannot be found");
        }
        int result = carDAO.deleteCar(id);

        if (result != 1) {
            throw new IllegalStateException("Could not delete car...");
        }

    }

    public void updateCarById (Integer id, Car update){
        if(id ==null){
            throw new IllegalStateException("Id is invalid, cannot be null");
        }
        Car carToUpdate = carDAO.selectCarById(id);
        if(carToUpdate == null){
            throw new IllegalStateException("Id of car cannot be found");
        }
        int result = carDAO.updateCar(id, update);

        if (result != 1) {
            throw new IllegalStateException("Could not update car...");
        }

    }
}
