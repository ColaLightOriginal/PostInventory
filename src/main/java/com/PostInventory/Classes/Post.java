package com.PostInventory.Classes;

import com.PostInventory.Enums.PostStatus;
import com.PostInventory.Enums.PostType;
import com.PostInventory.Utlis.DateTimeFormat;
import com.PostInventory.Utlis.GeoLocationUtils.GeoLocation;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.time.format.DateTimeParseException;

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
    @Column(name="title", nullable= false)
    private String title;
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
    @Column(name="tag", nullable=true)
    private String tag;
    @Column(name="event_date", nullable = true)
    private String eventDate;
    @Column(name="price", nullable = true)
    private String price;
    @Column(name="likesCount", nullable = false)
    private int likesCount;
    @Column(name="unlikesCount", nullable = false)
    private int unlikesCount;

    public Post(){
        this.setStatus("Created");
        this.setCreateDateTime();
        this.setValidDateTime();
        this.setLikesCount(0);
        this.setUnlikesCount(0);
    }

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
        if(EnumUtils.isValidEnum(PostType.class,type))
            this.type = type;
        else throw new EnumConstantNotPresentException(PostType.class,type);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(EnumUtils.isValidEnum(PostStatus.class,status))
            this.status = status;
        else throw new EnumConstantNotPresentException(PostStatus.class, status);
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

    public void setValidDateTime() {
        try {
            this.validDateTime = DateTimeFormat.getZonedDateTime().plusDays(3).toString();
        } catch(DateTimeParseException e){
            e.printStackTrace();
        }
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime() {
        try {
            this.createDateTime = DateTimeFormat.getZonedDateTime().toString();
        } catch(DateTimeParseException e){
            e.printStackTrace();
        }
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getUnlikesCount() {
        return unlikesCount;
    }

    public void setUnlikesCount(int unlikesCount) {
        this.unlikesCount = unlikesCount;
    }

    public void log(){
        System.out.println(" id: " + this.id +
                            " coordinateX: " + this.coordinateX +
                            " coordinateY: " + this.coordinateY +
                            " type: " + this.type +
                            " status: " + this.status);
    }
}
