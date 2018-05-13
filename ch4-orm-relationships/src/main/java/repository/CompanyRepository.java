package repository;

import entity.Address;
import entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CompanyRepository {
    protected EntityManager em;

    public CompanyRepository(EntityManager em) {
        this.em = em;
    }

    public Company createAndSave(String name, Address address) {
        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        em.persist(company);
        return company;
    }

    public List<Company> findAll() {
        TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c", Company.class);
        return query.getResultList();
    }
}
