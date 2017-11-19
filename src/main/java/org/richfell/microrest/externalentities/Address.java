/*
 */

package org.richfell.microrest.externalentities;

/**
 * An address for JSONPlaceholder user entity address fields.
 * 
 *  https://jsonplaceholder.typicode.com/
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class Address
{
    /**
     * Creates an <code>Address</code>.
     */
    public Address()
    {
    }

    /**
     * Creates an <code>Address</code> setting all fields.
     * 
     * @param street  the street address
     * @param suite  the suit number
     * @param city  the city
     * @param zipcode  the zip code
     */
    public Address(String street, String suite, String city, String zipcode)
    {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
    }

    /** The street address */
    String street;

    /**
     * Gets the street address.
     * 
     * @return the street address
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * Sets the street address.
     * 
     * @param street  the street address
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /** The suite/unit number */
    String suite;

    /**
     * Gets the suite number.
     * 
     * @return the suite number
     */
    public String getSuite()
    {
        return suite;
    }

    /**
     * Sets the suite number.
     * 
     * @param suite  the suit number
     */
    public void setSuite(String suite)
    {
        this.suite = suite;
    }

    /** The city */
    String city;

    /**
     * Sets the city name.
     * 
     * @return the city name
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the city name.
     * 
     * @param city  the city name
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /** The ZIP code for the address */
    String zipcode;

    /**
     * Gets the ZIP Code.
     * 
     * @return the ZIP Code
     */
    public String getZipcode()
    {
        return zipcode;
    }

    /**
     * Sets the ZIP Code.
     * 
     * @param zipcode the ZIP Code
     */
    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    /** The coordinates of the address */
    Coordinate geo;

    /**
     * Gets the coordinates of the address.
     * 
     * @return the coordinates
     */
    public Coordinate getGeo()
    {
        return geo;
    }

    /**
     * Sets the coordinates of the address.
     * 
     * @param geo the coordinates
     */
    public void setGeo(Coordinate geo)
    {
        this.geo = geo;
    }
}
