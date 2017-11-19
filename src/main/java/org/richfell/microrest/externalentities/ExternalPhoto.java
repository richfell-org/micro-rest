
package org.richfell.microrest.externalentities;

/**
 * A class for JSONPlaceholder photo entities.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class ExternalPhoto
extends ExternalEntity
{
    /**
     * Creates a <code>ExternalPhoto</code>.
     */
    public ExternalPhoto()
    {
    }

    /**
     * Creates a <code>ExternalPhoto</code> with the given ID.
     * 
     * @param id  the ID for the photo
     */
    public ExternalPhoto(Long id)
    {
        super(id);
    }

    /** the ID for the photo's album */
    private Long albumId;

    /**
     * Gets the album ID.
     * 
     * @return the album ID
     */
    public Long getAlbumId()
    {
        return albumId;
    }

    /**
     * Sets the album ID.
     * 
     * @param albumId  the album ID
     */
    public void setAlbumId(Long albumId)
    {
        this.albumId = albumId;
    }

    /** The title of the photo */
    private String title;

    /**
     * Gets the photo title.
     * 
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the photo title.
     * 
     * @param title  the title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /** The URL for the photo */
    private String url;

    /**
     * Gets the photo's URL.
     * 
     * @return the URL
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Sets the photo's URL.
     * 
     * @param url  the URL
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /** The URL for the photo's thumbnail */
    private String thumbnailUrl;

    /**
     * Gets the photo's thumbnail URL.
     * 
     * @return the thumbnail URL
     */
    public String getThumbnailUrl()
    {
        return thumbnailUrl;
    }

    /**
     * Sets the photo's thumbnail URL.
     * 
     * @param thumbnailUrl  the thumbnail URL
     */
    public void setThumbnailUrl(String thumbnailUrl)
    {
        this.thumbnailUrl = thumbnailUrl;
    }
}
