package repository;

import entity.Employee;
import entity.ParkingSpace;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ParkingSpaceRepository {
    protected EntityManager em;

    public ParkingSpaceRepository(EntityManager em) {
        this.em = em;
    }

    public ParkingSpace createAndSave(String location){
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setLocation(location);
        em.persist(parkingSpace);
        return parkingSpace;
    }

    public List<ParkingSpace> findAll(){
        TypedQuery<ParkingSpace> query = em.createQuery("SELECT p FROM ParkingSpace p", ParkingSpace.class);
        return query.getResultList();
    }
}
