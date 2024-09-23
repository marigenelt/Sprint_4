package practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest extends BaseSetUpTest {
    private final String clientFirstName;
    private final String clientLastName;
    private final String deliveryAddress;
    private final int clientMetroStation;
    private final String clientPhoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String commentForCourier;


    public OrderTest(String clientFirstName, String clientLastName, String deliveryAddress, int clientMetroStation,
                     String clientPhoneNumber, String deliveryDate, String rentalPeriod, String scooterColor, String commentForCourier) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.deliveryAddress = deliveryAddress;
        this.clientMetroStation = clientMetroStation;
        this.clientPhoneNumber = clientPhoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.commentForCourier = commentForCourier;
    }

    @Parameterized.Parameters
    public static Object[][] getClientData() {
        return new Object[][]{
                {"Тест", "Тестов", "ул. Тестовая 3,к5", 2, "89269999999", "30.09.2024", "четверо суток", "black", "Звонок не работает"},
                {"Иван", "Иванов", "Даниловский пер. 44", 2, "+79934040122", "29.09.2024", "сутки", "grey", "Позвонить на телефон"},
        };
    }


    @Test
    public void checkOrderByUsingTopOrderButton() {
        //кликнуть на верхнюю кнопку заказа
        scooterPage.clickOnTopOrderButton();
        checkOrderProcess();
    }

    @Test
    public void checkOrderByUsingBottomOrderButton() {
        //кликнуть на нижнюю кнопку заказа
        scooterPage.clickOnBottomOrderButton();
        checkOrderProcess();
    }

    private void checkOrderProcess() {
        //заполнить форму заказа о клиенте
        scooterPage.fillFormAboutClient(clientFirstName, clientLastName, deliveryAddress, clientMetroStation, clientPhoneNumber);
        //нажать Далее
        scooterPage.clickOnNextButtonInOrder();
        //заполнить форму заказа о аренде
        scooterPage.fillFormAboutRent(deliveryDate, rentalPeriod, scooterColor, commentForCourier);
        //нажать на Заказать
        String orderConfirmed = scooterPage.submitOrder();
        assertEquals("Заказ успешно оформлен", "Заказ оформлен", orderConfirmed.substring(0, 14));
    }


}