package io.mylabs.automation.tests;

import io.mylabs.automation.factory.DriverFactoryManager;
import io.mylabs.automation.pages.SpinPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Feature("Spin Game")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpinnerTest {

    private SpinPage spinPage;
    private List<String> initialSymbols;
    private List<String> actualSymbols;
    private Set<String> uniqueSymbols = new HashSet<>();
    private final Integer MAX_NUMBER_OF_SYMBOLS_IN_SPIN_GAME = 6;

    @BeforeAll
    void setUp() {
        DriverFactoryManager driverFactoryManager = new DriverFactoryManager();
    }

    @BeforeEach
    public void loadPage() {
        spinPage = new SpinPage().get();
        initialSymbols = spinPage.getSymbolInfo();
    }

    @Test
    @Order(1)
    @DisplayName("Check the Message once the game is loaded")
    @Severity(SeverityLevel.NORMAL)
    public void verifyStatusMessageWhenGameIsLoaded() {
        assertEquals("Welcome", spinPage.getStatusMessage());
    }

    @Test
    @Order(2)
    @DisplayName("Check if the Start button spins the reel")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyIfSpinButtonWorks() {
        assertNotEquals(initialSymbols, spinPage.startSpin().getSymbolInfo());
    }

    @Test
    @Order(3)
    @DisplayName("Check the Message when all the symbols are same")
    @Severity(SeverityLevel.NORMAL)
    public void verifyStatusMessageWhenAllThreeSymbolsAreSame() {
        keepSpinningUnlessScoreIs(3);
        assertEquals(getExpectedMessage(getScore(actualSymbols)), spinPage.getStatusMessage(), "Incorrect message!");
    }

    @Test
    @Order(3)
    @DisplayName("Check the Message when only 2 symbols are same")
    @Severity(SeverityLevel.NORMAL)
    public void verifyStatusMessageWhenTwoSymbolsAreSame() {
        keepSpinningUnlessScoreIs(2);
        assertEquals(getExpectedMessage(getScore(actualSymbols)), spinPage.getStatusMessage(), "Incorrect message!");

    }

    @Test
    @Order(3)
    @DisplayName("Check the Message when none of the symbols are same")
    @Severity(SeverityLevel.NORMAL)
    public void verifyStatusMessageWhenAllThreeSymbolsAreUnique() {
        keepSpinningUnlessScoreIs(1);
        assertEquals(getExpectedMessage(getScore(actualSymbols)), spinPage.getStatusMessage(), "Incorrect message!");
    }

    @Test
    @Order(4)
    @DisplayName("Check unique number of symbols displayed in the entire play")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyImagesWhileSpin() {
        assertEquals(MAX_NUMBER_OF_SYMBOLS_IN_SPIN_GAME, uniqueSymbols.size(), "Spinning Reel does not have expected number of symbols");
    }

    @Step
    private void keepSpinningUnlessScoreIs(int score) {
        do {
            actualSymbols = spinPage.startSpin().getSymbolInfo();
            //to save unique symbols
            actualSymbols.forEach(s -> uniqueSymbols.add(s));
        } while (getScore(actualSymbols) != score);
    }

    @Step
    private int getScore(List<String> symbols) {
        int score = 1;
        Set<String> uniqueSet = new HashSet<>(symbols);
        if (uniqueSet.size() < 3) {
            for (String temp : uniqueSet) {
                if (score <= Collections.frequency(symbols, temp))
                    score = Collections.frequency(symbols, temp);
            }
        }
        return score;
    }

    @Step
    private String getExpectedMessage(int score) {
        String expectedMessage = "";
        switch (score) {
            case 1:
                expectedMessage = "No Win, try again.";
                break;
            case 2:
                expectedMessage = "Small win, try again to win more.";
                break;
            case 3:
                expectedMessage = "Big win, congratulations.";
                break;
            default:
                break;
        }
        return expectedMessage;
    }


}
