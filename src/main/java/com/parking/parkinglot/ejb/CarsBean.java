package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars()
    {
        LOG.info("findAllCars");
        try {
            TypedQuery<Car> typedQuery=entityManager.createQuery("SELECT c FROM Car c",Car.class);
            List<Car> cars=typedQuery.getResultList();
            return copyCarsToDto(cars);

        }catch (Exception ex){
            throw new EJBException(ex);
        }

    }
    List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> carList = new ArrayList<CarDto>();
        for (Car car : cars) {
            carList.add(new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getOwner().getUserName()));
        }
        return carList;
    }
    public void createCar(String licensePlate,String parkingSpot,Long userId){
        LOG.info("createCar");
        Car car=new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user=entityManager.find(User.class,userId);
        user.getCars().add(car); //we add the car object to the collection of cars of the user
        car.setOwner(user);//we set the user in the car object
        entityManager.persist(car);//we persist the car object - a new entry in the DB will be created
    }
    public CarDto findById(Long id) {
        try {
            LOG.info("findCarById");
            Car car = entityManager.find(Car.class, id);
            return new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getOwner().getUserName());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    public void updateCar(Long carId,String licensePlate,String parkingSpot,Long userId){
        LOG.info("updateCar");
        Car car=entityManager.find(Car.class,carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        //remove this car from the old owner
        User oldUser=car.getOwner();
        oldUser.getCars().remove(car);
        //add car to its new owner
        User user=entityManager.find(User.class,userId);

        user.getCars().add(car); //we add the car object to the collection of cars of the user
        car.setOwner(user);//we set the user in the car object

    }
    public void deleteCarsByIds(Collection<Long> carIds){
        LOG.info("deleteCarsByIds");
        for (Long carId : carIds) {
            Car car=entityManager.find(Car.class,carId);
            entityManager.remove(car);


        }

    }

}
