package mariobanay.diplomski.cleancodearchitectureplay.domain.interactors;

import mariobanay.diplomski.cleancodearchitectureplay.domain.interactors.base.Interactor;

// All interactors are run in the background thread
public interface WelcomingInteractor extends Interactor {

    // Callback is responsible for communicating to UI main thread
    interface Callback {
        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);
    }


}
