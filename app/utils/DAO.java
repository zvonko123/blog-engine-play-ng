package utils;

import com.google.common.collect.Lists;
import models.Post;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by zv0 on 23.02.16..
 */

//singleton for DAObject
public class DAO {

    private static DAO dao = null;

    public static  DAO getInstance()
    {
        synchronized(DAO.class){
            if (dao == null)
            {
                dao= new DAO();
            }
        }
        return dao;

    }

    public List<Post> findAllPosts(){
        final List<Post> posts= Lists.newArrayList();

        JPA.withTransaction(() -> {
            posts.addAll(JPA.em().createQuery("select e from Post e", Post.class).getResultList());

        });
        return posts;
    };
}
