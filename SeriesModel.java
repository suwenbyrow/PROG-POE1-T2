package tvstreamingapp;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
/**
 * This class represents a single TV series and stores its details.
 */
public class SeriesModel {
    // Fields to store the series details.
    private String seriesId;
    private String seriesName;
    private int seriesAgeRestriction;
    private int seriesNumberOfEpisodes;

    /**
     * Constructor for a new SeriesModel object.
     *
     * @param seriesId The unique ID for the series.
     * @param seriesName The name of the series.
     * @param seriesAgeRestriction The age restriction for the series.
     * @param seriesNumberOfEpisodes The number of episodes in the series.
     */
    public SeriesModel(String seriesId, String seriesName, int seriesAgeRestriction, int seriesNumberOfEpisodes) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.seriesAgeRestriction = seriesAgeRestriction;
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }

    // --- Getters and Setters ---

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getSeriesAgeRestriction() {
        return seriesAgeRestriction;
    }

    public void setSeriesAgeRestriction(int seriesAgeRestriction) {
        this.seriesAgeRestriction = seriesAgeRestriction;
    }

    public int getSeriesNumberOfEpisodes() {
        return seriesNumberOfEpisodes;
    }

    public void setSeriesNumberOfEpisodes(int seriesNumberOfEpisodes) {
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }
}