package utils;

import com.google.common.collect.Lists;
import models.Comment;
import models.Post;
import models.PostComment;
import play.db.jpa.JPA;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by zv0 on 23.02.16..
 */

//singleton  DAObject
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

    public void addComment(Comment comment, String post_id) {
        JPA.withTransaction(() -> {
            JPA.em().persist(comment);
            //parameters need sanitizing
            JPA.em().persist(new PostComment(post_id,comment.getId()));

        });
    }

    //for some reason, we cant persist the object created manually with id in constructor
    //so we fetch the Post with find(primarykey) and update the body and title on it
    public void editPost(Post post) {
        JPA.withTransaction(() -> {
            Post postToModify = JPA.em().find(Post.class,post.getId());
            postToModify.setBody(post.getBody());
            postToModify.setTitle(post.getTitle());

            JPA.em().persist(postToModify);

        });
    }

    public void addPost(Post post) {
        JPA.withTransaction(() -> {
            JPA.em().persist(post);
        });
    }
}
