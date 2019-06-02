package mariobanay.diplomski.cleancodearchitectureplay.domain.interactors.base;


import mariobanay.diplomski.cleancodearchitectureplay.domain.executor.Executor;
import mariobanay.diplomski.cleancodearchitectureplay.domain.executor.MainThread;


// AbstractInteractor takes care of running it on the background thread
public abstract class AbstractInteractor implements Interactor {

    protected Executor mThreadExecutor;
    protected MainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor(Executor threadExecutor, MainThread mMainThread) {
        this.mThreadExecutor = threadExecutor;
        this.mMainThread = mMainThread;
    }

    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute() {
        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        mThreadExecutor.execute(this);
    }

}
