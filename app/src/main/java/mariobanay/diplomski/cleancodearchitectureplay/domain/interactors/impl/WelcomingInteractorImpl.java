package mariobanay.diplomski.cleancodearchitectureplay.domain.interactors.impl;

import mariobanay.diplomski.cleancodearchitectureplay.domain.executor.Executor;
import mariobanay.diplomski.cleancodearchitectureplay.domain.executor.MainThread;
import mariobanay.diplomski.cleancodearchitectureplay.domain.interactors.WelcomingInteractor;
import mariobanay.diplomski.cleancodearchitectureplay.domain.interactors.base.AbstractInteractor;
import mariobanay.diplomski.cleancodearchitectureplay.domain.repository.MessageRepository;

public class WelcomingInteractorImpl extends AbstractInteractor implements WelcomingInteractor {

    private WelcomingInteractor.Callback mCallback;
    private MessageRepository mMessageRepository;

    public WelcomingInteractorImpl(Executor threadExecutor, MainThread mMainThread, Callback callback, MessageRepository messageRepository) {
        super(threadExecutor, mMainThread);

        mCallback = callback;
        mMessageRepository = messageRepository;
    }



    @Override
    public void run() {

        // retrieve the message
        final String message = mMessageRepository.getWelcomeMessage();

        // check if retrieving message failed
        if (message == null || message.length() == 0 ) {
            // notify the failure on the main thread
            notifyError();

            return;
        }

        // we have retrieved our message, notify the UI on the main thread
        postMessage(message);

    }

    private void postMessage(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onMessageRetrieved(message);
            }
        });
    }

    private void notifyError() {

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });

    }
}
