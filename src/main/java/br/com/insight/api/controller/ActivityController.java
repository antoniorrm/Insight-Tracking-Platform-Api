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

import br.com.insight.api.model.Activity;
import br.com.insight.api.model.User;
import br.com.insight.api.repository.ActivityRepository;
import br.com.insight.api.repository.UserRepository;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class ActivityController {	
	@Autowired
    private ActivityRepository activityRepository;
	
	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }
    
    @RequestMapping(value = "/activity/user/{id}", method = RequestMethod.GET)
    public List<Activity> getByUserId(@PathVariable("id") Long id, @RequestParam(required = false) String type ) {
    	User user = userRepository.getOne(id);
    	if(type != null) {
    		return activityRepository.findAllByUserAndTypeOrderByIdAsc(user, type);
    	}
        return activityRepository.findAllByUserOrderByIdAsc(user);
    }
    
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public Activity create(@RequestBody Activity activity) {
    	try {
        	User user = userRepository.getOne(activity.getUser().getId());
        	activity.setUser(user);
            return activityRepository.save(activity);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
    	
    }
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
    public Activity update(@PathVariable("id") Long id, @RequestBody Activity activityUpdate) {
    	try {
    		Activity activity = activityRepository.getOne(id);
    		activity.setType(activityUpdate.getType());
        	activity.setDescription(activityUpdate.getDescription());
            return activityRepository.saveAndFlush(activity);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
    	
    }
    
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    public Activity getOne(@PathVariable("id") Long id) {
        return activityRepository.getOne(id);
    }
    
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.DELETE)
    public Activity delete(@PathVariable("id") Long id) {
    	Activity activity = activityRepository.getOne(id);
        activityRepository.delete(activity);
        activityRepository.flush();
        return activity;
    }
}
