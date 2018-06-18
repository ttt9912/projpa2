package p1_ordering.repository;

import p1_ordering.entity.PrinterQueue;

import javax.persistence.EntityManager;

public class PrinterQueueRepository extends BaseRepository<PrinterQueue> {

    public PrinterQueueRepository(EntityManager em) {
        super(em);
    }
}
