package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link WeatherReportController}.
 * This class contains tests that verify the functionality of the
 * WeatherReportController by mocking the WeatherReportService.
 */
@SpringBootTest
public class WeatherReportControllerTest {

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    /**
     * Tests that the getWeatherReport method returns a valid WeatherReport.
     */
    @Test
    public void testGetWeatherReport() {
        // Mock data for the WeatherReport
        WeatherReport mockWeatherReport = new WeatherReport();
        mockWeatherReport.setTemperature(25.0);
        mockWeatherReport.setHumidity(60.0);

        // Simulate service response
        Mockito.when(weatherReportService.getWeatherReport(37.8267, -122.4233))
                .thenReturn(mockWeatherReport);

        // Call the method under test
        WeatherReport result = weatherReportController.getWeatherReport(37.8267, -122.4233);

        // Assert results
        assertEquals(25.0, result.getTemperature());
        assertEquals(60.0, result.getHumidity());
    }

    /**
     * Verifies that the getWeatherReport method correctly returns the expected values.
     */
    @Test
    public void testGetWeatherReportSuccess() {
        WeatherReport mockWeatherReport = new WeatherReport();
        mockWeatherReport.setTemperature(25.0);
        mockWeatherReport.setHumidity(60.0);

        Mockito.when(weatherReportService.getWeatherReport(37.8267, -122.4233))
                .thenReturn(mockWeatherReport);

        WeatherReport result = weatherReportController.getWeatherReport(37.8267, -122.4233);

        assertEquals(25.0, result.getTemperature());
        assertEquals(60.0, result.getHumidity());
    }

    /**
     * Tests the getWeatherReport method with boundary values for latitude and longitude.
     */
    @Test
    public void testGetWeatherReportBoundaryValues() {
        WeatherReport mockWeatherReport = new WeatherReport();
        mockWeatherReport.setTemperature(30.0);
        mockWeatherReport.setHumidity(70.0);

        Mockito.when(weatherReportService.getWeatherReport(-90.0, 180.0))
                .thenReturn(mockWeatherReport);

        WeatherReport result = weatherReportController.getWeatherReport(-90.0, 180.0);

        assertEquals(30.0, result.getTemperature());
        assertEquals(70.0, result.getHumidity());
    }

    /**
     * Verifies the behavior when the service returns null.
     */
    @Test
    public void testGetWeatherReportServiceReturnsNull() {
        Mockito.when(weatherReportService.getWeatherReport(37.8267, -122.4233))
                .thenReturn(null);

        WeatherReport result = weatherReportController.getWeatherReport(37.8267, -122.4233);

        assertNull(result);
    }

    /**
     * Tests that the getWeatherReport method throws a RuntimeException when the service fails.
     */
    @Test
    public void testGetWeatherReportServiceThrowsException() {
        Mockito.when(weatherReportService.getWeatherReport(37.8267, -122.4233))
                .thenThrow(new RuntimeException("Service error"));

        assertThrows(RuntimeException.class, () -> {
            weatherReportController.getWeatherReport(37.8267, -122.4233);
        });
    }

    /**
     * Tests the getWeatherReport method with different latitude and longitude values.
     */
    @Test
    public void testGetWeatherReportWithDifferentLatLong() {
        WeatherReport mockWeatherReport = new WeatherReport();
        mockWeatherReport.setTemperature(20.0);
        mockWeatherReport.setHumidity(55.0);

        Mockito.when(weatherReportService.getWeatherReport(50.1109, 8.6821)) // Frankfurt
                .thenReturn(mockWeatherReport);

        WeatherReport result = weatherReportController.getWeatherReport(50.1109, 8.6821);

        assertEquals(20.0, result.getTemperature());
        assertEquals(55.0, result.getHumidity());
    }

}
