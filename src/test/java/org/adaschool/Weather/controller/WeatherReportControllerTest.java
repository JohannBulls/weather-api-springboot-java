package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WeatherReportControllerTest {

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @Test
    public void testGetWeatherReport() {
        // Mock data for the WeatherReport
        WeatherReport mockWeatherReport = new WeatherReport();
        mockWeatherReport.setTemperature(25.0);
        mockWeatherReport.setHumidity(60.0);

        // Simula la respuesta del servicio
        Mockito.when(weatherReportService.getWeatherReport(37.8267, -122.4233))
                .thenReturn(mockWeatherReport);

        // Llamada al método a probar
        WeatherReport result = weatherReportController.getWeatherReport(37.8267, -122.4233);

        // Verificaciones
        assertEquals(25.0, result.getTemperature());
        assertEquals(60.0, result.getHumidity());
    }
}
