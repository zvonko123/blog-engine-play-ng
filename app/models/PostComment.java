package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by zv0 on 23.02.16..
 */
@Entity
public class PostComment {

    String id;

    @GeneratedValue
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    String post_id;

    public PostComment() {
    }

    public PostComment(String post_id, String comment_id) {

        this.post_id = post_id;
        this.comment_id = comment_id;
    }

    @Column

    String comment_id;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }
}
