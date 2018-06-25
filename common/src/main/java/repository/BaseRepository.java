package repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BaseRepository<E> {
    private EntityManager em;

    public BaseRepository(EntityManager em) {
        this.em = em;
    }

    public E createAndSave(E entity) {
        em.persist(entity);
        return entity;
    }

    public List<E> createAndSaveAll(List<E> entities) {
        entities.forEach(this::createAndSave);
        return entities;
    }

    public E findById(Class<E> clazz, int id) {
        return em.find(clazz, id);
    }

    /**
     * Troubles with inner classes?
     * -> try to use getName() instead of getCanonicalName()
     */
    public List<E> findAll(Class<E> clazz) {
        String className = clazz.getCanonicalName();
        TypedQuery<E> query = em.createQuery("SELECT e FROM " + className + " e", clazz);
        return query.getResultList();
    }

    public void delete(E entity) {
        em.remove(entity);
    }

    public void deleteById(Class<E> clazz, int id) {
        E entity = findById(clazz, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

}
