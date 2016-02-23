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


    //a web method which gives our frontend all the fresh posts and comments
    public Result getFreshData() {

        return ok(Json.toJson(DAO.getInstance().findAllPosts()));
    }

    public Result addComment(){

        DynamicForm requestData = Form.form().bindFromRequest();
        String name = requestData.get("name");
        String message = requestData.get("message");
        String topic_id = requestData.get("topic_id");
        Comment comment = new Comment(name,message);

        DAO.getInstance().addComment(comment,topic_id);

        return ok(Json.toJson("success commenting topic "+topic_id));
    }

    public Result index() {

        return ok(index.render(DAO.getInstance().findAllPosts().size() +" topics total"));
    }

}
