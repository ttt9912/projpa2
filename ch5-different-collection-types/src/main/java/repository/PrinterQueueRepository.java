package repository;

import entity.PrinterQueue;

import javax.persistence.EntityManager;

public class PrinterQueueRepository extends BaseRepository<PrinterQueue> {

    public PrinterQueueRepository(EntityManager em) {
        super(em);
    }
}
