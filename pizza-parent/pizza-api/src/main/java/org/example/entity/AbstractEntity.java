package org.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity extends CoreEntity{

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "creation_date")
    private Date creationDate;

    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    @ManyToOne
    private User creatorUser;

    @ManyToOne
    private User modifierUser;
    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }
    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public User getModifierUser() {
        return modifierUser;
    }

    public void setModifierUser(User modifierUser) {
        this.modifierUser = modifierUser;
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
}




