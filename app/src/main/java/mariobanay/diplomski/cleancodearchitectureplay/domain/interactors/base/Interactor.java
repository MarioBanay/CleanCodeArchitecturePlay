package mariobanay.diplomski.cleancodearchitectureplay.domain.interactors.base;


// Each interactor serves a specific use case, interactor runs on background thread
public interface Interactor {

    // method that starts interactor
    void execute();
}
