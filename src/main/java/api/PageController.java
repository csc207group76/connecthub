package api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    // bundled JavaScript files
    private final String SIGN_UP_SCRIPT = "/js/sign_up/app.js";
    private final String LOG_IN_SCRIPT = "/js/login/app.js";
    private final String POST_SCRIPT = "";
    private final String CREATE_POST_SCRIPT = "/js/create_post/app.js";
    private final String HOME_PAGE_SCRIPT = "/js/home_page/app.js";
    private final String STYLES_SCRIPT = "/js/styles/app.css";

    private final String TEMPLATE_FILE = "index";
    private final String PAGE_TITLE = "page_title";
    private final String SCRIPT = "script";
    private final String STYLE = "style";
    private final String DIV_ID = "div_id";


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(PAGE_TITLE, "Home");
        model.addAttribute(SCRIPT, HOME_PAGE_SCRIPT);
        model.addAttribute(DIV_ID, "homeDiv");
        model.addAttribute(STYLE, STYLES_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute(PAGE_TITLE, "Home");
        model.addAttribute(SCRIPT, HOME_PAGE_SCRIPT);
        model.addAttribute(DIV_ID, "homeDiv");
        model.addAttribute(STYLE, STYLES_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/signup")
    public String signIn(Model model) {
        model.addAttribute(PAGE_TITLE, "Sign Up");
        model.addAttribute(SCRIPT, SIGN_UP_SCRIPT);
        model.addAttribute(DIV_ID, "signUpDiv");
        model.addAttribute(STYLE, STYLES_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(PAGE_TITLE, "Log In");
        model.addAttribute(SCRIPT, LOG_IN_SCRIPT);
        model.addAttribute(DIV_ID, "loginDiv");
        model.addAttribute(STYLE, STYLES_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/create-post")
    public String createPost(Model model) {
        model.addAttribute(PAGE_TITLE, "Create Post");
        model.addAttribute(SCRIPT, CREATE_POST_SCRIPT);
        model.addAttribute(DIV_ID, "createPostDiv");
        model.addAttribute(STYLE, STYLES_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/post")
    public String post( Model model) {
        model.addAttribute(PAGE_TITLE, "Home");
        model.addAttribute(SCRIPT, POST_SCRIPT);
        model.addAttribute(DIV_ID, "postDiv");
        model.addAttribute(STYLE, STYLES_SCRIPT);
        return TEMPLATE_FILE;
    }
}
