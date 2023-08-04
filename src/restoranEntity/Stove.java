package restoranEntity;

import java.util.concurrent.Semaphore;

public class Stove {

    private Semaphore semaphore;

    public Stove(int permits) {
        this.semaphore = new Semaphore(permits);
    }
    public boolean tryToCook() throws InterruptedException {
       return semaphore.tryAcquire();
    }

    public void releaseTheStove() {
        semaphore.release();
    }

    public int getAvailiableSpots() {
        return semaphore.availablePermits();
    }
}
