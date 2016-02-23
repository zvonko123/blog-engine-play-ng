package models;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by zv0 on 23.02.16..
 */
@Entity

public class Post {

    public Post() {
    }

    public Post(String title, String body) {
        this.setTitle(title);
        this.setBody(body);
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {

        return id;
    }

    @Id
    @GeneratedValue
    public Integer id;
    @Column
    public String title;
    @Column
    public String body;
    //public Comment[] comments;

}
