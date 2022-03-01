package car;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fake")
public class FakeCarDataAccessService implements CarDAO{
    private List<Car> db;  //db is a list of cars

    public FakeCarDataAccessService() {
        this.db = new ArrayList<>();
    }

    @Override
    public Car selectCarById(Integer id) {
        //loop through all the cars in the db list and find matchings with id
        for (Car car :db){
            if(car.getId() == id){
                return car;
            }
        }
        return null;
    }

    @Override
    public List<Car> selectAllCars() {
        return db;
    }

    @Override
    public int insertCar(Car car) {
        db.add(car);
        return 1;
    }

    @Override                           //need to implement, going to be the same as selectCarById but add in remove
    public int deleteCar(Integer id) {
        Car carToDelete = selectCarById(id);
        db.remove(carToDelete);
        return 1;   //if we successfully delete a car then we return 1
    }

    @Override           //need to implement
    public int updateCar(Integer id, Car update) {
        Car carToUpdate = selectCarById(id); //fetch the car by id then assign it to carToUpdate
        carToUpdate.setBrand(update.getBrand());
        carToUpdate.setPrice(update.getPrice());
        carToUpdate.setRegNumber(update.getRegNumber());
        return 1; //if successful return 1, hence is not successful return !=1
    }
}
