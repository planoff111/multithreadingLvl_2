package restoranEntity;

import java.util.concurrent.Semaphore;

public class Stove {

    private Semaphore semaphore;

    public Stove(int permits) {
        this.semaphore = new Semaphore(permits);
    }
    public void tryToCook() throws InterruptedException {
        semaphore.acquire();
    }

    public void releaseTheStove() {
        semaphore.release();
    }

    public int getAvailiableSpots() {
        return semaphore.availablePermits();
    }
}
