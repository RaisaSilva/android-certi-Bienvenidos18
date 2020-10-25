package bo.com.bienvenido18.android;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.model.users.UserProfile;
import bo.com.bienvenido18.android.repository.MockRepository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;
import bo.com.bienvenido18.android.utils.Constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LoginTest {

    RepositoryImpl repository;

    @Before
    public void beforeEach() {
        repository = new MockRepository(null);
    }

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void loginWithRegularUser() {
        String email = "nuria.michel@gmail.com";
        String password = "contrasena1";
        LiveData<Base<UserO>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);

        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertNull(userBase.getException());//No Errors
            assertEquals(UserProfile.USERO, userBase.getData().getProfile());
            assertEquals("1", userBase.getData().getUuid());
            assertEquals(" Hola Nuri", userBase.getData().getDisplayName());
        });
    }

    @Test
    public void loginFailed() {
        String email = "holamundo@gmail.com"; //@ y que tenga . con extension
        String password = "contrasena123";

        LiveData<Base<UserO>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);

        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertFalse(userBase.isSuccess());
            assertEquals(Constants.ERROR_LOGIN, userBase.getErrorCode());
        });
    }

    @Test
    public void loginFailedInvalidEmail() {
        String email = "holaMundo"; //@ y que tenga . con extension
        String password = "contrasena123";

        LiveData<Base<UserO>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);

        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertFalse(userBase.isSuccess());
            assertEquals(Constants.ERROR_INVALID_EMAIL, userBase.getErrorCode());
        });
    }

    @Test
    public void loginFailedEmptyValues() {
        String email = "";
        String password = "test123";

        LiveData<Base<UserO>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);

        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertFalse(userBase.isSuccess());
            assertEquals(Constants.ERROR_EMPTY_VALUES, userBase.getErrorCode());
        });
    }
    @Test
    public void loginFailedInvalidPassword() {
        String email = "nuria.michel@gmail.com";
        String password = "contrasena"; //tiene que tener numeros
        LiveData<Base<UserO>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);
        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertFalse(userBase.isSuccess());
            assertEquals(Constants.ERROR_wRONG_PASSWORD, userBase.getErrorCode());
        });
    }
}

