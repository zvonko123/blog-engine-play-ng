package utils;

import com.google.common.collect.Lists;
import models.Post;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by zv0 on 23.02.16..
 */
public class DAO {

    public static List<Post> findAllPosts(){
        final List<Post> posts= Lists.newArrayList();

        JPA.withTransaction(() -> {
            posts.addAll(JPA.em().createQuery("select e from Post e", Post.class).getResultList());

        });
        return posts;
    };
}
