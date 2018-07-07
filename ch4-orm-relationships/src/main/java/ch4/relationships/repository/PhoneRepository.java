package ch4.relationships.repository;

import ch4.relationships.entity.Phone;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PhoneRepository {
    protected EntityManager em;

    public PhoneRepository(EntityManager em) {
        this.em = em;
    }

    public Phone createAndSave(String type, String number) {
        Phone phone = new Phone();
        phone.setType(type);
        phone.setNumber(number);
        em.persist(phone);
        return phone;
    }

    public List<Phone> findAll() {
        TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p", Phone.class);
        return query.getResultList();
    }
}
