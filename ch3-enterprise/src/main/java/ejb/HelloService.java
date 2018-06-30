package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import java.util.logging.Logger;

/*
 * Stateless Session Bean
 *
 * Hat keinen state
 * Eignet sich für die Kapselung von Business Services.
 * Entweder mit oder ohne Interface
 *
 * Lifecycle Callbacks:
 *  @PostConstruct: Initialisierungen (alternativ zum Konstruktor)
 *  @PreDestroy: Release resources
 */

@Stateless
public class HelloService {
    private Logger logger;

    @PostConstruct
    void init(){
        logger = Logger.getLogger("notification");
    }

    @PreDestroy
    void shutdown(){
        // free resources
    }

    public String sayHello(final String name) {
        return "Hello, " + name;
    }
}
