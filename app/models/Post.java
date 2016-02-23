package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zv0 on 23.02.16..
 */
@Entity
public class Post {


    public Set<Comment> comments = new HashSet<Comment>(0);


    public String title;

    public String body;


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
                "} with "+comments.size()+" comments";
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Id
    @GeneratedValue
    public Integer getId() {

        return id;
    }


    public Integer id;

    public void setId(Integer id) {
        this.id = id;
    }


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PostComment", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "comment_id") })
    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
