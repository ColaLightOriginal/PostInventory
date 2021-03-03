package com.PostInventory.Classes;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private int id;
    @Column(name="post_id", nullable = false)
    private int postId;
    @Column(name="comment_user_id", nullable = false)
    private int postUserId;
    @Column(name="content", nullable = false)
    private String content;
    @Column(name="likes_count", nullable = false)
    private int likesCount;
    @Column(name="create_date_time", nullable= true)
    private String createDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(int postUserId) {
        this.postUserId = postUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        try {
            LocalDate dateNow = LocalDate.now();
            LocalTime timeNow = LocalTime.now();
            LocalDateTime dateTimeNow = LocalDateTime.of(dateNow, timeNow);
            String dateTimeString = dateTimeNow.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            this.createDateTime = dateTimeString;
        } catch(DateTimeParseException e){
            e.printStackTrace();
        }
    }

}
