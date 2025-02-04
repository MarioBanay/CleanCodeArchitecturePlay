package mariobanay.diplomski.cleancodearchitectureplay.threading;

import mariobanay.diplomski.cleancodearchitectureplay.domain.executor.MainThread;

public class TestMainThread implements MainThread {

    @Override
    public void post(Runnable runnable) {

        // tests can run on this thread, no need to invoke other threads
        runnable.run();
        
    }
}
