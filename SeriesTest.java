package tvstreamingapp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;

/**
 * JUnit test class for the Series application.
 * Note: These tests use a technique to mock standard input/output for testing purposes.
 * This is a common practice for testing console applications.
 */
public class SeriesTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    private Series seriesApp;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(outputStreamCaptor));
        seriesApp = new Series();
        // Reset the series list before each test
        try {
            Field seriesListField = Series.class.getDeclaredField("seriesList");
            seriesListField.setAccessible(true);
            List<SeriesModel> seriesList = new ArrayList<>();
            seriesListField.set(seriesApp, seriesList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        // Restore standard System.out
        System.setOut(standardOut);
    }

    private void provideInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        try {
            Field scannerField = Series.class.getDeclaredField("scanner");
            scannerField.setAccessible(true);
            scannerField.set(seriesApp, new Scanner(testInput));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void testCaptureSeries_Success() {
        // Test case for successful series capture
        String input = "1\nTest Series 1\n12\n100\n";
        provideInput(input);
        
        seriesApp.captureSeries();
        
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Series details have been successfully saved."), "Series was not captured successfully.");
    }

    @Test
    void testSearchSeries() {
        // Setup: Capture a series first for searching
        String captureInput = "1\nTest Series 1\n12\n100\n";
        provideInput(captureInput);
        seriesApp.captureSeries();
        
        // Test search function
        String searchInput = "1\n";
        provideInput(searchInput);
        
        seriesApp.searchSeries();
        
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Series Found!"), "Search function did not find the series.");
        assertTrue(output.contains("ID: 1"), "Search function did not display the correct ID.");
    }
    
    @Test
    void testSearchSeries_SeriesNotFound() {
        // Test case for a series not found
        String searchInput = "nonexistent\n";
        provideInput(searchInput);
        
        seriesApp.searchSeries();
        
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("No series data could be found for the given ID."), "Search function did not handle 'not found' case correctly.");
    }
    
    @Test
    void testUpdateSeries() {
        // Setup: Capture a series
        String captureInput = "1\nOld Name\n10\n50\n";
        provideInput(captureInput);
        seriesApp.captureSeries();
        
        // Test update function
        String updateInput = "1\nNew Name\n15\n150\n";
        provideInput(updateInput);
        
        seriesApp.updateSeries();
        
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Series details updated successfully."), "Update function did not work correctly.");
    }
    
    @Test
    void testDeleteSeries() {
        // Setup: Capture a series
        String captureInput = "1\nSeries to Delete\n12\n100\n";
        provideInput(captureInput);
        seriesApp.captureSeries();
        
        // Test delete function with confirmation
        String deleteInput = "1\nY\n";
        provideInput(deleteInput);
        
        seriesApp.deleteSeries();
        
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Series deleted successfully."), "Series was not deleted successfully.");
    }
    
    @Test
    void testDeleteSeries_SeriesNotFound() {
        // Test delete on a non-existent series
        String deleteInput = "nonexistent\n";
        provideInput(deleteInput);
        
        seriesApp.deleteSeries();
        
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Series with ID nonexistent not found. Cannot delete."), "Delete function did not handle 'not found' case correctly.");
    }

    @Test
    void testSeriesAgeRestriction_AgeValid() {
        // Test capture with a valid age
        String input = "1\nTest Age Valid\n15\n100\n";
        provideInput(input);
        seriesApp.captureSeries();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Series details have been successfully saved."));
    }

    @Test
    void testSeriesAgeRestriction_AgeInvalid() {
        // Test capture with an invalid age (out of range)
        String input = "1\nTest Age Invalid\n20\n18\n100\n"; // 20 is invalid, then 18 is valid
        provideInput(input);
        seriesApp.captureSeries();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Invalid age restriction. Please enter a number between 2 and 18."));
    }
}