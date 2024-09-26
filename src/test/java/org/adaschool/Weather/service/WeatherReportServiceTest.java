package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        // Mock del objeto de respuesta de la API
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60.0);
        mockApiResponse.setMain(main);

        // Simula la llamada a la API
        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenReturn(mockApiResponse);
    }

    @Test
    public void testGetWeatherReport() {
        // Llamada al método a probar
        WeatherReport result = weatherReportService.getWeatherReport(37.8267, -122.4233);

        // Verificaciones
        assertEquals(0.0, result.getTemperature());
        assertEquals(82.0, result.getHumidity());
    }
}
