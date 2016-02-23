package controllers;

import com.google.common.collect.Lists;
import models.Post;
import play.*;

import play.api.libs.json.jackson.JacksonJson;
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
        
    }

    public Result index() {

        return ok(index.render(DAO.getInstance().findAllPosts().size() +" topics total"));
    }

}
