package com.PostInventory.Classes;

import com.PostInventory.Enums.PostStatus;
import com.PostInventory.Enums.PostType;
import com.PostInventory.Utlis.GeoLocationUtils.GeoLocation;

import javax.persistence.*;
import javax.validation.ValidationException;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private int id;
    @Column(name="type", nullable = false)
    private String type;
    @Column(name="status", nullable = false)
    private String status;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="valid_date_time", nullable = true)
    private String validDateTime;
    @Column(name="create_date_time", nullable = false)
    private String createDateTime;
    @Column(name="create_user", nullable = false)
    private int createUser;
    @Column(name="coordinate_x", nullable = true)
    private String coordinateX;
    @Column(name="coordinate_y", nullable = true)
    private String coordinateY;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValidDateTime() {
        return validDateTime;
    }

    public void setValidDateTime(String validDateTime) {
        this.validDateTime = validDateTime;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public String getCoordinateX() { return coordinateX; }

    public void setCoordinateX(String coordinateX) {
        if(GeoLocation.validateCoordinate(coordinateX)) this.coordinateX = coordinateX;
        else{
            ValidationException e = new ValidationException();
            e.printStackTrace();
        }
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(String coordinateY) {
        if(GeoLocation.validateCoordinate(coordinateY)) this.coordinateY = coordinateY;
        else{
            ValidationException e = new ValidationException();
            e.printStackTrace();
        }
    }
}
