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

import java.util.List;

public class Application extends Controller {


    public Result addPost() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String title = requestData.get("title");
        String body = requestData.get("body");
        String post_id = requestData.get("post_id");

        //either its edited (with post_id) or a new post(with no id)
        Post post=null;
        if (post_id != "null")
        {
            post = new Post(post_id,title,body);
        }
        else{
            post = new Post(title,body);
        }

        DAO.getInstance().addPost(post);

        return ok(Json.toJson("success add/edit post "+title));
    }

    //a web method which gives our frontend all the fresh posts and comments
    public Result getFreshData() {

        return ok(Json.toJson(DAO.getInstance().findAllPosts()));
    }

    public Result addComment(){

        DynamicForm requestData = Form.form().bindFromRequest();
        String name = requestData.get("name");
        String message = requestData.get("message");
        String post_id = requestData.get("post_id");
        Comment comment = new Comment(name,message);

        DAO.getInstance().addComment(comment,post_id);

        return ok(Json.toJson("success commenting post "+post_id));
    }

    public Result index() {

        return ok(index.render(DAO.getInstance().findAllPosts().size() +" posts total"));
    }

}
