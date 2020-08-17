package br.com.insight.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.insight.api.model.User;
import br.com.insight.api.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
    private UserService userService;

	@ApiOperation(value="Retorna a lista de usuários")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getAll(@RequestParam(required = false) String type , @RequestParam(required = false) String value ) {
		return userService.getAllUsers(type, value);
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User newUser) {
        return userService.create(newUser);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable("id") Long id, @RequestBody User userUpdate) {
		return userService.update(id, userUpdate);
    	
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable("id") Long id) {
        return userService.getOne(id);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable("id") Long id) {
    	return userService.delete(id);
    }
}
