package repository;

import entity.Badge;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BadgeRepository {
    protected EntityManager em;

    public BadgeRepository(EntityManager em) {
        this.em = em;
    }

    public Badge createAndSave(String building) {
        final Badge badge = new Badge();
        badge.setBuilding(building);
        em.persist(badge);
        return badge;
    }

    public List<Badge> findAll() {
        TypedQuery<Badge> query = em.createQuery("SELECT b FROM Badge b", Badge.class);
        return query.getResultList();
    }
}
