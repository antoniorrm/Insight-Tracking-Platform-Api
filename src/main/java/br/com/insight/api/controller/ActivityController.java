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
import br.com.insight.api.service.ActivityService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class ActivityController {	
	@Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public List<Activity> getAll() {
        return activityService.getAll();
    }
    
    @RequestMapping(value = "/activity/user/{id}", method = RequestMethod.GET)
    public List<Activity> getByUserId(@PathVariable("id") Long id, @RequestParam(required = false) String type ) {
        return activityService.getByUserId(id, type);
    }
    
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public Activity create(@RequestBody Activity activity) {
		return activityService.create(activity);
    	
    }
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
    public Activity update(@PathVariable("id") Long id, @RequestBody Activity activityUpdate) {
		return activityService.update(id, activityUpdate);
    	
    }
    
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    public Activity getOne(@PathVariable("id") Long id) {
        return activityService.getOne(id);
    }
    
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.DELETE)
    public Activity delete(@PathVariable("id") Long id) {
        return activityService.delete(id);
    }
}
