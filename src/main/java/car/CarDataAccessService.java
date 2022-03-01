package car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
//another implementation of CarDAO is CarDataAccessService
//which will connect to actual database
@Repository("postgres")
public class CarDataAccessService implements CarDAO{
    //inject jdbc template:
    private JdbcTemplate jdbcTemplate;

    public CarDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Car selectCarById(Integer id) {
        return null;
    }

    @Override
    public List<Car> selectAllCars() {
        String sql = """
                SELECT id, regnumber, brand, price FROM car
                """;
        RowMapper<Car> carRowMapper = (rs, rowNum) ->{
            Car car = new Car(
                    rs.getInt("id"),
                    rs.getString("regnumber"),
                    Brand.valueOf(rs.getString("brand")),
                    rs.getDouble("price")
            );
            return car;
        };

        List<Car> cars = jdbcTemplate.query(sql, carRowMapper);
        return cars;
    }

    @Override
    public int insertCar(Car car) {
        String sql = """
                INSERT INTO car(regnumber, brand, price) VALUES(?, ?, ?)""";    //don't include id because id is a SERIAL and gets automated for you
        int rowsAffected = jdbcTemplate.update(
                sql,
                car.getRegNumber(),
                car.getBrand().name(),  //added .name to convert to string since datatype for brand is TEXT
                car.getPrice()
        );
        return rowsAffected;
    }

    @Override
    public int deleteCar(Integer id) {
        return 0;
    }

    @Override
    public int updateCar(Integer id, Car update) {
        return 0;
    }
}
