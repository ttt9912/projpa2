package repository;

import entity.PrintJob;

import javax.persistence.EntityManager;

public class PrintJobRepository extends BaseRepository<PrintJob> {

    public PrintJobRepository(final EntityManager em) {
        super(em);
    }
}
