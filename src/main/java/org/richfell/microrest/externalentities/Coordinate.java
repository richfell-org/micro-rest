/*
 */

package org.richfell.microrest.externalentities;

/**
 * A latitude and longitude coordinate.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class Coordinate
{
    /**
     * Creates a <code>Coordiate</code> with the given latitude and
     * longitude values.
     * 
     * @param lat the latitude
     * @param lng the longitude
     */
    public Coordinate(double lat, double lng)
    {
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Creates a <code>Coordiate</code> with a latitude and
     * longitude of 0.0.
     */
    public Coordinate()
    {
        this(0.0, 0.0);
    }

    /** The latitude */
    private double lat;

    /**
     * Gets the latitude.
     * 
     * @return the latitude
     */
    public double getLat()
    {
        return lat;
    }

    /**
     * Sets the latitude.
     * 
     * @param lat the latitude
     */
    public void setLat(double lat)
    {
        this.lat = lat;
    }

    /** The longitude */
    private double lng;

    /**
     * Gets the longitude.
     * 
     * @return the longitude
     */
    public double getLng()
    {
        return lng;
    }

    /**
     * Sets the longitude.
     * 
     * @param lng the longitude
     */
    public void setLng(double lng)
    {
        this.lng = lng;
    }
}
