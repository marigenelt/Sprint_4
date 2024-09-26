package practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTest extends BaseSetUpTest {
    private final int faqNumber;
    private static final String[] EXPECTED_ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    public FaqTest(int faqNumber) {
        this.faqNumber = faqNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getNumberFaq() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }

    /**
     * Нажать на стрелку - проверить, что текст отображается и соответствует ожидаемому.
     */
    @Test
    public void whenArrowClickedTextShouldBeShown() {
        //вызвать скролл
        scooterPage.scrollToQuestions();
        // найти стрелку
        List<WebElement> questions = scooterPage.getQuestions();
        WebElement item = questions.get(faqNumber);
        //нажать на стрелку
        scooterPage.openItem(item);
        // Проверить появление текста
        assertTrue("Ответ отобразился", scooterPage.isAnswerDisplayed(item));

        // Получить текст ответа
        String actualAnswer = scooterPage.getAnswerText(item);

        // Проверить соответствие текста с ожидаемым
        assertEquals("Ответ совпадает с ожидаемым", EXPECTED_ANSWERS[faqNumber], actualAnswer);
    }
}