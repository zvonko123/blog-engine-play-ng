package controllers;

import com.google.common.collect.Lists;
import models.Post;
import play.*;
import play.db.jpa.JPA;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    public Result index() {

        String title = "first post title";
        String body = "first post body";

        final Post post = new Post(title, body);
        JPA.withTransaction(() -> {
            JPA.em().persist(post);
        });

        final List<Post> posts= Lists.newArrayList();

        JPA.withTransaction(() -> {
            posts.addAll(JPA.em().createQuery("select e from Post e", Post.class).getResultList());
        });


        return ok(index.render(posts.get(0).toString()));
    }

}
