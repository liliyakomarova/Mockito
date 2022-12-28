import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {

    private static GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    void testLocationByIp() {

        Assertions.assertEquals(Country.RUSSIA, geoService.byIp("172.0.32.11").getCountry());
        Assertions.assertEquals(Country.USA, geoService.byIp("96.44.183.196").getCountry());
    }
    @Test
    void testByCoordinates() throws RuntimeException {

        Throwable thrown = assertThrows(RuntimeException.class, () ->
                geoService.byCoordinates(40.7143,74.006));

        Assertions.assertNotNull(thrown.getMessage());
    }
}