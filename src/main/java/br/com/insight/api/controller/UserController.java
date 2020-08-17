package br.com.insight.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
    private ActivityRepository activityRepository;
	
	@Autowired
    private UserRepository userRepository;

	@ApiOperation(value="Retorna a lista de usuários")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getAll(@RequestParam(required = false) String type , @RequestParam(required = false) String value ) {
    	try {
    		if(type == null || value == null) {
    			return userRepository.findAllByOrderByIdAsc();
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
    public User create(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable("id") Long id, @RequestBody User userUpdate) {
    	try {
    		User user = userRepository.getOne(id);
    		user.setName(userUpdate.getName());
    		user.setEmail(userUpdate.getEmail());
    		user.setAddress(userUpdate.getAddress());
    		user.setPhone(userUpdate.getPhone());
            return userRepository.saveAndFlush(user);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
    	
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable("id") Long id) {
        return userRepository.getOne(id);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable("id") Long id) {
    	User user = userRepository.getOne(id);
    	userRepository.delete(user);
    	userRepository.flush();
        return user;
    }
}
