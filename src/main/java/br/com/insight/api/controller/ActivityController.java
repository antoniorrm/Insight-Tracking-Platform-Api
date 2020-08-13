package br.com.insight.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.insight.api.model.Activity;
import br.com.insight.api.model.User;
import br.com.insight.api.repository.ActivityRepository;
import br.com.insight.api.repository.UserRepository;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("api")
public class ActivityController {
	@Autowired
    private ActivityRepository activityRepository;
	
	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public List<Activity> GetUsers() {
        return activityRepository.findAll();
    }
    
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public Activity Store(@RequestBody JSONObject newUser) {
    	try {

    		Long id =newUser.getAsNumber("userId").longValue();
        	User user = userRepository.getOne(id);
        	
        	Activity activity = new Activity((String) newUser.get("type"),(String) newUser.get("description"), user);
        	
            return activityRepository.save(activity);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
    	
    }
}
