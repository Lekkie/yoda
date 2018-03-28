package com.avantir.yoda.controller;

import com.avantir.yoda.model.User;
import com.avantir.yoda.repository.UserRepository;
import com.avantir.yoda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lekanomotayo on 28/03/2018.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/{id}", headers = "Accept=application/json")
    @ResponseBody
    public Object getById(@PathVariable Long id)
    {
        String fxnParams = "id=" + id;
        try
        {
            User user = userService.findByUserId(id);
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);
        }
    }

}
