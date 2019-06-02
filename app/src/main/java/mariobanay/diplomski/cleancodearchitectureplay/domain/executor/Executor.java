package mariobanay.diplomski.cleancodearchitectureplay.domain.executor;


import mariobanay.diplomski.cleancodearchitectureplay.domain.interactors.base.AbstractInteractor;

// Executor is responsible for running interactors on background threads
public interface Executor {

    // This method should call the interactor's run method and thus start
    // the interactor which will run on background thread
    void execute (final AbstractInteractor interactor);

}
