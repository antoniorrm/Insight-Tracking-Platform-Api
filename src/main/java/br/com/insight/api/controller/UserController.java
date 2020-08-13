package br.com.insight.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.insight.api.model.Activity;
import br.com.insight.api.model.User;
import br.com.insight.api.repository.ActivityRepository;
import br.com.insight.api.repository.UserRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api")
public class UserController {
	@Autowired
    private ActivityRepository activityRepository;
	
	@Autowired
    private UserRepository userRepository;

	@ApiOperation(value="User", notes="")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getUsersOfActivity(@RequestParam(required = false) String type , @RequestParam(required = false) String value ) {
    	try {
    		if(type == null || value == null) {
    			return userRepository.findAll();
    		}
    		List<Activity> activitys = activityRepository.findByTypeAndDescription(type, value);
        	System.out.println(type);
        	List<User> aux = new ArrayList<User>();
        	for (Activity activity : activitys) {
				aux.add(activity.getUser());
			}
            return aux;	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
    	
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User Store(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
}
