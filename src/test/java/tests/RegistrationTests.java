package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess() {
      //  Random random = new Random();
      //  int i = random.nextInt(1000);

        int h = (int) System.currentTimeMillis()/1000;

        User user = new User().setEmail("noa" + h + "@yandex.ru").setPassword("Noa12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void registrationWrongEmail() {

        User user = new User().setEmail("noa@@yandex.ru").setPassword("Noa12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent1("Wrong email or password"));

    }

    @Test
    public void registrationWrongPassword() {

        User user = new User().setEmail("noa@yandex.ru").setPassword("noa12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent1("Wrong email or password"));

    }
}
