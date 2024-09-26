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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertEquals(83.0, result.getHumidity());
    }

    @Test
    public void testGetWeatherReportSuccess() {
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60.0);
        mockApiResponse.setMain(main);

        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenReturn(mockApiResponse);

        WeatherReport result = weatherReportService.getWeatherReport(37.8267, -122.4233);

        assertEquals(0.0, result.getTemperature());
        assertEquals(83.0, result.getHumidity());
    }

    @Test
    public void testGetWeatherReportBoundaryValues() {
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(30.0);
        main.setHumidity(70.0);
        mockApiResponse.setMain(main);

        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenReturn(mockApiResponse);

        WeatherReport result = weatherReportService.getWeatherReport(-90.0, 180.0);

        assertEquals(0.0, result.getTemperature());
        assertEquals(82.0, result.getHumidity());
    }

    @Test
    public void testGetWeatherReportApiReturnsNull() {
        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenReturn(null);

        WeatherReport result = weatherReportService.getWeatherReport(37.8267, -122.4233);

        assertNull(result);
    }

    @Test
    public void testGetWeatherReportApiThrowsException() {
        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenThrow(new RuntimeException("API error"));

        assertThrows(RuntimeException.class, () -> {
            weatherReportService.getWeatherReport(37.8267, -122.4233);
        });
    }

    @Test
    public void testGetWeatherReportWithDifferentApiResponses() {
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(15.0);
        main.setHumidity(50.0);
        mockApiResponse.setMain(main);

        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenReturn(mockApiResponse);

        WeatherReport result = weatherReportService.getWeatherReport(48.8566, 2.3522); // Paris

        assertEquals(0.0, result.getTemperature());
        assertEquals(97.0, result.getHumidity());
    }

}
