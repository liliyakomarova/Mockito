import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @Test
    void send_language_validation_testW(){

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.123.12.20"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");


        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headersRussian = new HashMap<String, String>();
        headersRussian.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.20");

        Map<String, String> headersUSA= new HashMap<String, String>();
        headersUSA.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");


        Assertions.assertEquals("Добро пожаловать", messageSender.send(headersRussian));
        Assertions.assertEquals("Welcome", messageSender.send(headersUSA));
    }
}