package models;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by zv0 on 23.02.16..
 */
@Entity

public class Post {

    @Id
    @GeneratedValue
    public Integer id;
    @Column
    public String title;
    @Column
    public String body;
    //public Comment[] comments;
    @Column
    public DateTime timestamp;
}
