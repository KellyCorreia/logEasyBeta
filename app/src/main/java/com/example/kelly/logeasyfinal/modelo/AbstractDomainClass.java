package com.example.kelly.logeasyfinal.modelo;

import java.util.Date;

/**
 * Created by jt on 12/16/15.
 */
public class AbstractDomainClass implements DomainObject {

    protected Integer id;

    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void updateTimeStamps() {
        lastUpdated = new Date();
        if (dateCreated==null) {
            dateCreated = new Date();
        }
    }
}
