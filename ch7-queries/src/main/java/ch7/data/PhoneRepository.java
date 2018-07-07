package ch7.data;

import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class PhoneRepository extends BaseRepository<Phone> {

    public PhoneRepository(final EntityManager em) {
        super(em);
    }
}
