package mariobanay.diplomski.cleancodearchitectureplay.domain.interactors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mariobanay.diplomski.cleancodearchitectureplay.domain.executor.Executor;
import mariobanay.diplomski.cleancodearchitectureplay.domain.executor.MainThread;
import mariobanay.diplomski.cleancodearchitectureplay.domain.interactors.impl.WelcomingInteractorImpl;
import mariobanay.diplomski.cleancodearchitectureplay.domain.repository.MessageRepository;
import mariobanay.diplomski.cleancodearchitectureplay.threading.TestMainThread;

import static org.mockito.Matchers.anyString;

public class GetWelcomeMessageTest {

    private MainThread mMainThread;

    @Mock private Executor mExecutor;
    @Mock private MessageRepository mMessageRepository;
    @Mock private WelcomingInteractor.Callback mMockedCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }

    @Test
    public void testWelcomeMessageNotFound() throws Exception {

        WelcomingInteractorImpl interactor = new WelcomingInteractorImpl(mExecutor, mMainThread, mMockedCallback, mMessageRepository);
        interactor.run();

        Mockito.when(mMessageRepository.getWelcomeMessage())
                .thenReturn(null);

        Mockito.verify(mMessageRepository).getWelcomeMessage();
        Mockito.verifyNoMoreInteractions(mMessageRepository);
        Mockito.verify(mMockedCallback).onRetrievalFailed(anyString());
    }

    @Test
    public void testWelcomeMessageFound() throws Exception {
        String msg = "Welcome friend";

        Mockito.when(mMessageRepository.getWelcomeMessage())
                .thenReturn(msg);

        WelcomingInteractorImpl interactor = new WelcomingInteractorImpl(mExecutor, mMainThread, mMockedCallback, mMessageRepository);
        interactor.run();

        Mockito.verify(mMessageRepository).getWelcomeMessage();
        Mockito.verifyNoMoreInteractions(mMessageRepository);
        Mockito.verify(mMockedCallback).onMessageRetrieved(msg);
    }
}
