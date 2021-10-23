package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@ResponseBody
@RequestMapping("hello")
public class HelloController {

//    // Handles requests at path /goodbye
//    @GetMapping("hello")
//    @ResponseBody
//    public String hello() {
//        return "Hello, Spring!";
//    }

    // lives at /hello/goodbye
    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    // lives at hello/hello
    // // Handles the requests of the form /hello?name=LaunchCode
    // RequestMapping handles both get and post requests
    @RequestMapping(method={RequestMethod.GET, RequestMethod.POST})
    public String helloWithQueryParam(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    // Handles requests of the form /hello/LaunchCode
    @GetMapping("{name}")
    public String helloWithPathParam(@PathVariable String name) {
       return "Hello, " + name + "!";
    }

    // lives at /hello/form
    @GetMapping("form")
    public String helloForm() {
        return "<html>" +
                "<body>" +
                "<form action='/hello' method='POST'>" +  // submit a request to /hello
                                                        // note the default method if not specified is post
                "<input type='text' name='name'>" +
                "<input type='submit' value='Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    @GetMapping("form2")
    @ResponseBody
    public String helloFormWithLanguage() {
        return "<html>" +
                "<body>" +
                "<form action='/helloWithLanguage' method='POST'>" +
                // submit a request to /helloWithLanguage
                // note the default method if not specified is post
                "<label for='name'>Your name: </label>" +
                "<input type='text' name='name'>" +
                "<br/><br/>" +
                "<label for='language-select'>Select a language: </label>" +
                "<select name='language' id='language-select'>" +
                "<option value=''>--Please choose an option--</option>" +
                "<option value='spanish'>Spanish</option>" +
                "<option value='french'>French</option>" +
                "<option value='italian'>Italian</option>" +
                 "<option value='german'>German</option>" +
                "<option value='english'>English</option>" +
                "</select>" +
                "<br/><br/>" +
                "<input type='submit' value='Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    @PostMapping("helloWithLanguage")
    @ResponseBody
    public String helloPost(@RequestParam String name, @RequestParam String language) {
        if (name == null || name == "") {
            name = "a name was not entered.";
        }
        return createMessage(name, language);
    }

    public static String createMessage(String name, String language) {
        String greeting = "";
        if (language.equals("spanish")) {
            greeting += "Hola";
        }
        else if (language.equals("french")) {
            greeting += "Bonjour";
        }
        else if (language.equals("italian")) {
            greeting += "Bonjourno";
        }
        else if (language.equals("german")) {
            greeting += "Hallo";
        }
        else if (language.equals("english")) {
            greeting += "Hello";
        }
        else if (language.equals("")) {
            greeting += "The language was not selected, ";
        }
        return greeting + " " + name;
    }


}
