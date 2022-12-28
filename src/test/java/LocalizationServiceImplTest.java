import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource(names = { "GERMANY", "USA", "BRAZIL" })
    void testLocale (Country country){
        LocalizationServiceImpl local = new LocalizationServiceImpl();

        Assertions.assertEquals("Добро пожаловать", local.locale(Country.RUSSIA));
        Assertions.assertEquals("Welcome", local.locale(country));
    }
}