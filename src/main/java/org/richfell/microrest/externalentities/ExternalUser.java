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

    public ExternalUser(Long id)
    {
        super(id);
    }

    /** The user's real name */
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /** The user's user name */
    private String username;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    /** The user's email address */
    private String email;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    /** The user's address */
    private Address address;

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    /** The user's phone number */
    private String phone;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /** The user's web site */
    private String website;

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    /** The user's company */
    private Company company;

    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }
}
