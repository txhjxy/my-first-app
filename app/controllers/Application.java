package controllers;

import play.data.Form;
import play.mvc.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import play.mvc.Result;
import views.html.index;
import views.html.tasks;
import views.html.helps;
import models.Task;

import java.util.Date;
import java.util.Map;



public class Application extends Controller {

	public static Result index() {


		System.out.println(flash("foo"));
		return ok(index.render("Your new application is ready"));
	}

	public static Result tasks() {

		flash("foo","pizza");
	    // java.util.Random;
	    Random rnd = new Random();

	    Task task   = new Task();
	    task.name   = "ピザを" + rnd.nextInt(10) + "枚食べる";
	    task.period = new Date();
	    task.save();

	    String tasknow= task.name;

	    List<Task> taskList = Task.find.all();
	    return ok(tasks.render(taskList, tasknow));


	}
	public static Result createTask() {
		 /*Task newTask = Form.form(Task.class).bindFromRequest().get();

		    newTask.save();
		    return redirect(routes.Application.tasks());
		}*/

		Form<Task> form = Form.form(Task.class).bindFromRequest();

	    if (form.hasErrors()) {
	        return badRequest(form.errorsAsJson());
	    } else {
	        Task newTask = form.get();
	        newTask.save();
	        return redirect(routes.Application.tasks());
	    }
	}

	public static Result helps(){
		return ok(helps.render());
	}
}