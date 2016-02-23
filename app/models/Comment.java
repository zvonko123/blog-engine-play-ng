package models;

import javax.persistence.*;

/**
 * Created by zv0 on 23.02.16..
 */
@Entity
public class Comment {

    public Comment() {
    }

    public String id;

    public String message;


    @GeneratedValue
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
