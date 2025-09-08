package tvstreamingapp;

import java.util.Scanner; // Import Scanner

public class TVStreamingApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Series seriesApp = new Series(); // Make sure Series.java exists
        int choice;

        do {
            System.out.println("\n--- TV Streaming App Menu ---");
            System.out.println("1. Capture a new TV series");
            System.out.println("2. Search for a TV series");
            System.out.println("3. Update a TV series");
            System.out.println("4. Delete a TV series");
            System.out.println("5. View series report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        seriesApp.captureSeries();
                        break;
                    case 2:
                        seriesApp.searchSeries();
                        break;
                    case 3:
                        seriesApp.updateSeries();
                        break;
                    case 4:
                        seriesApp.deleteSeries();
                        break;
                    case 5:
                        seriesApp.seriesReport();
                        break;
                    case 6:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0; // Reset choice to loop again
            }

        } while (choice != 6);

        scanner.close();
    }
}
