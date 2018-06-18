package p1_ordering.app;

import p1_ordering.entity.IPAddress;
import p1_ordering.entity.Printer;
import p1_ordering.entity.PrinterQueue;
import p1_ordering.repository.PrinterQueueRepository;
import p1_ordering.repository.PrinterRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class App {

    private static final String PU_NAME = "persistenceUnit";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU_NAME);
        EntityManager em = emf.createEntityManager();

        PrinterRepository printerRepository = new PrinterRepository(em);
        PrinterQueueRepository printerQueueRepository = new PrinterQueueRepository(em);

        // Create and createAndSave Printer and PrinterQueues
        em.getTransaction().begin();
        Printer printer = new Printer();
        Collection<PrinterQueue> printerQueues = printerQueueRepository.createAndSaveAll(createRandomPrinterQueues());
        printer.setPrinterQueues((List<PrinterQueue>) printerQueues);
        printer.setIpAddresses(createRandomIpAddresses());
        printerRepository.createAndSave(printer);
        System.out.println(printer);
        em.getTransaction().commit();

        // find all PrinterQueues
        List<PrinterQueue> all = printerQueueRepository.findAll(PrinterQueue.class);
        System.out.println(all);
    }

    private static List<PrinterQueue> createRandomPrinterQueues() {
        List<PrinterQueue> printerQueues = new ArrayList<>();
        printerQueues.add(new PrinterQueue(5));
        printerQueues.add(new PrinterQueue(10));
        printerQueues.add(new PrinterQueue(2));
        return printerQueues;
    }

    private static List<IPAddress> createRandomIpAddresses() {
        List<IPAddress> ipAddresses = new ArrayList<>();
        ipAddresses.add(new IPAddress("192.168.192.1"));
        ipAddresses.add(new IPAddress("255.1.1.1"));
        ipAddresses.add(new IPAddress("98.0.0.2"));
        return ipAddresses;
    }

}
