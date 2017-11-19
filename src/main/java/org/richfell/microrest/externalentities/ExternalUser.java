/*
 */

package org.richfell.microrest.externalentities;

/**
 * A user entity from JSONPlaceholder.
 * 
 *  https://jsonplaceholder.typicode.com/
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class ExternalUser
extends ExternalEntity
{
    /**
     * Creates a <code>ExternalUser</code>.
     */
    public ExternalUser()
    {
    }

    /**
     * Creates a <code>ExternalUser</code>, setting the user ID to the given value.
     * 
     * @param id  the user ID
     */
    public ExternalUser(Long id)
    {
        super(id);
    }

    /** The user's real name */
    private String name;

    /**
     * Gets the user's real name.
     * 
     * @return the real name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the user's real name.
     * 
     * @param name  the real name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /** The user's user name */
    private String username;

    /**
     * Gets the user's username.
     * 
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Sets the user's username.
     * 
     * @param username  the username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /** The user's email address */
    private String email;

    /**
     * Gets the user's email address.
     * 
     * @return the email address
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the user's email address.
     * 
     * @param email  the email address
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /** The user's address */
    private Address address;

    /**
     * Gets the user's street address.
     * 
     * @return the street address
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * Sets the user's street address.
     * 
     * @param address  the street address
     */
    public void setAddress(Address address)
    {
        this.address = address;
    }

    /** The user's phone number */
    private String phone;

    /**
     * Gets the user's phone number.
     * 
     * @return the user phone number
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * Sets the user's phone number.
     * 
     * @param phone  the user phone number
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /** The user's web site */
    private String website;

    /**
     * Get the user's web site URL.
     * 
     * @return the user web site
     */
    public String getWebsite()
    {
        return website;
    }

    /**
     * Sets the user's web site URL.
     * 
     * @param website  the user web site
     */
    public void setWebsite(String website)
    {
        this.website = website;
    }

    /** The user's company */
    private Company company;

    /**
     * Gets the user's company.
     * 
     * @return the company
     */
    public Company getCompany()
    {
        return company;
    }

    /**
     * Sets the user's company.
     * 
     * @param company  the company
     */
    public void setCompany(Company company)
    {
        this.company = company;
    }
}
