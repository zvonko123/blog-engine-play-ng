package controllers;

import com.google.common.collect.Lists;
import models.Post;
import play.*;

import play.api.libs.json.jackson.JacksonJson;
import play.db.jpa.JPA;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {


    //a web method which gives our frontend all the fresh posts and comments
    public Result getFreshData() {
        final List<Post> posts= Lists.newArrayList();

        JPA.withTransaction(() -> {
            posts.addAll(JPA.em().createQuery("select e from Post e", Post.class).getResultList());

        });

        return ok(Json.toJson(posts));
    }

    public Result index() {

        final List<Post> posts= Lists.newArrayList();

        JPA.withTransaction(() -> {
            posts.addAll(JPA.em().createQuery("select e from Post e", Post.class).getResultList());

        });


        return ok(index.render(posts.size() +" topics"));
    }

}
