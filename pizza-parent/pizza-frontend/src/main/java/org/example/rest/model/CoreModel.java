package org.example.rest.model;

import org.example.entity.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class CoreModel implements ApiModel{
    private Long id;

    private Date creationDate;

    private Date modificationDate;

    private Long creatorUserId;

    private Long modifierUserId;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Long getModifierUserId() {
        return modifierUserId;
    }

    public void setModifierUserId(Long modifierUserId) {
        this.modifierUserId = modifierUserId;
    }
}
