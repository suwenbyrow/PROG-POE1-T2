package tvstreamingapp;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handles all the business logic for the TV streaming application.
 * It contains methods for capturing, searching, updating, deleting, and reporting series.
 */
public class Series {
    // A list to store SeriesModel objects. This acts as our in-memory database.
    private final List<SeriesModel> seriesList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Captures a new TV series from user input and saves it to the list.
     */
    public void captureSeries() {
        System.out.println("--- Capture New Series ---");

        System.out.print("Enter Series ID: ");
        String seriesId = scanner.nextLine();

        System.out.print("Enter Series Name: ");
        String seriesName = scanner.nextLine();

        int ageRestriction = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter Age Restriction (2-18): ");
                ageRestriction = Integer.parseInt(scanner.nextLine());
                if (ageRestriction >= 2 && ageRestriction <= 18) {
                    validInput = true;
                } else {
                    System.out.println("Invalid age restriction. Please enter a number between 2 and 18.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for age restriction.");
            }
        }

        int numberOfEpisodes = -1;
        validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter Number of Episodes: ");
                numberOfEpisodes = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for episodes.");
            }
        }
        
        // Check for duplicate series ID before adding
        for (SeriesModel series : seriesList) {
            if (series.getSeriesId().equals(seriesId)) {
                System.out.println("A series with this ID already exists. Please use a unique ID.");
                return;
            }
        }

        SeriesModel newSeries = new SeriesModel(seriesId, seriesName, ageRestriction, numberOfEpisodes);
        seriesList.add(newSeries);
        System.out.println("\nSeries details have been successfully saved.");
    }

    /**
     * Searches for a series by its ID and displays its details.
     */
    public void searchSeries() {
        System.out.println("--- Search for a Series ---");
        System.out.print("Enter the Series ID to search for: ");
        String seriesId = scanner.nextLine();
        
        SeriesModel foundSeries = findSeriesById(seriesId);
        
        if (foundSeries != null) {
            System.out.println("\nSeries Found!");
            System.out.println("ID: " + foundSeries.getSeriesId());
            System.out.println("Name: " + foundSeries.getSeriesName());
            System.out.println("Age Restriction: " + foundSeries.getSeriesAgeRestriction());
            System.out.println("Number of Episodes: " + foundSeries.getSeriesNumberOfEpisodes());
        } else {
            System.out.println("No series data could be found for the given ID.");
        }
    }

    /**
     * Updates an existing series based on its ID.
     */
    public void updateSeries() {
        System.out.println("--- Update a Series ---");
        System.out.print("Enter the Series ID to be updated: ");
        String seriesId = scanner.nextLine();
        
        SeriesModel seriesToUpdate = findSeriesById(seriesId);
        
        if (seriesToUpdate != null) {
            System.out.println("Series found. Enter new details (press Enter to keep current value):");
            
            System.out.print("New Series Name [" + seriesToUpdate.getSeriesName() + "]: ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                seriesToUpdate.setSeriesName(newName);
            }

            int newAge = -1;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("New Age Restriction [" + seriesToUpdate.getSeriesAgeRestriction() + "]: ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    validInput = true; // Keep old value
                } else {
                    try {
                        newAge = Integer.parseInt(input);
                        if (newAge >= 2 && newAge <= 18) {
                            seriesToUpdate.setSeriesAgeRestriction(newAge);
                            validInput = true;
                        } else {
                            System.out.println("Invalid age restriction. Please enter a number between 2 and 18.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for age restriction.");
                    }
                }
            }

            int newEpisodes = -1;
            validInput = false;
            while (!validInput) {
                System.out.print("New Number of Episodes [" + seriesToUpdate.getSeriesNumberOfEpisodes() + "]: ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    validInput = true; // Keep old value
                } else {
                    try {
                        newEpisodes = Integer.parseInt(input);
                        seriesToUpdate.setSeriesNumberOfEpisodes(newEpisodes);
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for episodes.");
                    }
                }
            }
            
            System.out.println("\nSeries details updated successfully.");
        } else {
            System.out.println("Series with ID " + seriesId + " not found. Cannot update.");
        }
    }

    /**
     * Deletes a series based on its ID after user confirmation.
     */
    public void deleteSeries() {
        System.out.println("--- Delete a Series ---");
        System.out.print("Enter the Series ID to be deleted: ");
        String seriesId = scanner.nextLine();
        
        SeriesModel seriesToDelete = findSeriesById(seriesId);
        
        if (seriesToDelete != null) {
            System.out.print("Are you sure you want to delete '" + seriesToDelete.getSeriesName() + "'? (Y/N): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("Y")) {
                seriesList.remove(seriesToDelete);
                System.out.println("\nSeries deleted successfully.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Series with ID " + seriesId + " not found. Cannot delete.");
        }
    }

    /**
     * Generates and displays a report of all series currently in the list.
     */
    public void seriesReport() {
        System.out.println("--- TV Series Report ---");
        if (seriesList.isEmpty()) {
            System.out.println("No series have been captured yet.");
            return;
        }

        System.out.printf("%-15s %-30s %-20s %s\n", "Series ID", "Series Name", "Age Restriction", "Number of Episodes");
        System.out.println("---------------------------------------------------------------------------------");

        for (SeriesModel series : seriesList) {
            System.out.printf("%-15s %-30s %-20s %s\n",
                series.getSeriesId(),
                series.getSeriesName(),
                series.getSeriesAgeRestriction(),
                series.getSeriesNumberOfEpisodes());
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

    /**
     * Helper method to find a series by its ID.
     * @param seriesId The ID to search for.
     * @return The SeriesModel object if found, otherwise null.
     */
    private SeriesModel findSeriesById(String seriesId) {
        for (SeriesModel series : seriesList) {
            if (series.getSeriesId().equalsIgnoreCase(seriesId.trim())) {
                return series;
            }
        }
        return null;
    }
}
