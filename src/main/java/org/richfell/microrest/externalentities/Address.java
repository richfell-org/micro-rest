/*
 */

package org.richfell.microrest.externalentities;

/**
 * An address for user entities from JSONPlaceholder.
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

    public Address(String street, String suite, String city, String zipcode)
    {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
    }

    String street;

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    String suite;

    public String getSuite()
    {
        return suite;
    }

    public void setSuite(String suite)
    {
        this.suite = suite;
    }

    String city;

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    String zipcode;

    public String getZipcode()
    {
        return zipcode;
    }

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
