package restoranEntity;

import java.util.concurrent.Semaphore;

public class Table {

    private Semaphore semaphore;

    public Table(int permits) {
        this.semaphore = new Semaphore(permits);
    }
    public boolean tryToUseTable() throws InterruptedException {
        return semaphore.tryAcquire();
    }

    public void releaseTheTable() {
        semaphore.release();
    }

    public int getAvailiableSpots() {
        return semaphore.availablePermits();

    }
}
