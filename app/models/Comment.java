package models;

import javax.persistence.*;

/**
 * Created by zv0 on 23.02.16..
 */
@Entity
public class Comment {

    public String message;

    public String id;

    public String name;

    public Comment() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @GeneratedValue
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
