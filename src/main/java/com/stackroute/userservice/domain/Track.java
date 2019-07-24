package com.stackroute.userservice.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Track {

    public Track() {
    }

    @Id
    private int id;
    private String name;
    private String comment;

    public Track(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
