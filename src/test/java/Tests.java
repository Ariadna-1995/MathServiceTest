import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class Tests {
    static MathService mathService;

    @BeforeAll
    static void init() {
        mathService = new MathService();
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 11, 0, 121", "2, 2, 2, -12", "0, 4, 1, 16", "9, -11, 2, 49"})
    void testMethodGetD(int a, int b, int c, int res) {
        int discriminant = mathService.getD(a, b, c);
        Assertions.assertEquals(res, discriminant);
    }
    @ParameterizedTest
    @CsvSource(value = {"4, -1, -5, 1.25, -1", "1, 3, -4, 1, -4", "2, 5, -3, 0.5, -3"})
    void testGetAnswerPositiveDiscriminant(int a, int b, int c, double x1, double x2) throws NotFoundAnswerException {
        Pair pair = mathService.getAnswer(a, b, c);
        Assertions.assertEquals(x1, pair.first);
        Assertions.assertEquals(x2, pair.second);
    }
    @ParameterizedTest
    @CsvSource(value = {"1, 6, 9, -3", "-5, 0, 0, 0", "-6, 0, 0, 0"})
    void testGetAnswerZeroDiscriminant(int a, int b, int c, double res) throws NotFoundAnswerException {
        Pair pair = mathService.getAnswer(a, b, c);
        Assertions.assertEquals(res, pair.first);
    }

    @ParameterizedTest
    @CsvSource(value = {"10, -6, 5", "2, 2, 4", "1, -1, 56"})
    void testGetAnswerDecLessZeroNegative(int a, int b, int c) {
        Throwable exc = assertThrows(NotFoundAnswerException.class,
                () -> mathService.getAnswer(a, b, c));
        Assertions.assertEquals("Корни не могут быть найдены", exc.getMessage());
    }


}
