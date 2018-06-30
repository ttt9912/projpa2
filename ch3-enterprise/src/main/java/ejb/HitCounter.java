package ejb;

import javax.ejb.Singleton;
import javax.ejb.Startup;

/*
 * Singleton Session Bean
 *
 * Hat state
 *
 * Lifecycle Callbacks (Bean existiert bis die app heruntergefahren wird):
 *  @PostConstruct, @PreDestroy (wie Stateless Session Bean)
 *  @Startup: eager initialization bei Application start
 */

@Singleton
@Startup // eager init
public class HitCounter {
    private int count;

    public void increment() {
        ++count;
    }

    public int getCount() {
        return count;
    }

    public void reset() {
        count = 0;
    }
}
