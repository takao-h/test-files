package controllers;

import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import services.Mailservice;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Mailservice mailservice;
    @Inject
    public HomeController(Mailservice mailservice) {
        this.mailservice = mailservice;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    public Result index() {
        return ok("Your new application is ready.");
    }

    public Result mail(){
        mailservice.sendEmail();
        return ok(" send mail!");
    }


}
