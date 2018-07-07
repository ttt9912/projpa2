package ch5.repository;

import ch5.entity.PrintJob;
import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class PrintJobRepository extends BaseRepository<PrintJob> {

    public PrintJobRepository(final EntityManager em) {
        super(em);
    }
}
