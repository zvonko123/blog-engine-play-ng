package controllers;

import com.google.common.collect.Lists;
import models.Comment;
import models.Post;
import play.*;

import play.api.libs.json.jackson.JacksonJson;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.JPA;
import play.libs.Json;
import play.mvc.*;

import utils.DAO;
import views.html.*;

import javax.persistence.PersistenceException;
import java.util.List;

public class Application extends Controller {


    public Result addPost() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String title = requestData.get("title");
        String body = requestData.get("body");
        String post_id = requestData.get("post_id");

        //either its edited (with post_id) or a new post(with no id)
        Post post = null;
        try {
            if (post_id != null) {
                post = new Post(post_id, title, body);
                DAO.getInstance().editPost(post);
            } else {
                post = new Post(title, body);
                DAO.getInstance().addPost(post);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            return internalServerError("there was an error on the server while posting, try again");
        } finally {
            return ok(Json.toJson("success in add/edit post " + title));
        }
    }

    //a web method which gives our frontend all the fresh posts and comments
    public Result getFreshData() {
        List<Post> posts = null;
        try {
            posts = DAO.getInstance().findAllPosts();

        } catch (PersistenceException e) {
            e.printStackTrace();
            return internalServerError(Json.toJson("there was an error fetching data, try reloading the page"));
        } finally {
            return ok(Json.toJson(posts));
        }

    }

    public Result addComment() {

        DynamicForm requestData = Form.form().bindFromRequest();
        String name = requestData.get("name");
        String message = requestData.get("message");
        String post_id = requestData.get("post_id");
        Comment comment = new Comment(name, message);
        try {
            DAO.getInstance().addComment(comment, post_id);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return internalServerError(Json.toJson("there was an error, try commenting again"));
        } finally {
            return ok(Json.toJson("success commenting post " + post_id));
        }
    }


    public Result index() {

        return ok(index.render(DAO.getInstance().findAllPosts().size() + " posts total"));
    }

}
