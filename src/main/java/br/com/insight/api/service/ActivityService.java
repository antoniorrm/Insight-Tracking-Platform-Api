package br.com.insight.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insight.api.model.Activity;
import br.com.insight.api.model.User;
import br.com.insight.api.repository.ActivityRepository;
import br.com.insight.api.repository.UserRepository;

@Service
public class ActivityService {
	@Autowired
    private ActivityRepository activityRepository;
	
	@Autowired
	private UserRepository userRepository;

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }
    
    public List<Activity> getByUserId(Long id, String type ) {
    	User user = userRepository.getOne(id);
    	if(type != null) {
    		return activityRepository.findAllByUserAndTypeOrderByIdAsc(user, type);
    	}
        return activityRepository.findAllByUserOrderByIdAsc(user);
    }
    
    
    public Activity create(Activity activity) {
    	try {
        	User user = userRepository.getOne(activity.getUser().getId());
        	activity.setUser(user);
            return activityRepository.save(activity);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
    	
    }
   
    public Activity update(Long id, Activity activityUpdate) {
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
    
    public Activity getOne(Long id) {
        return activityRepository.getOne(id);
    }
    
    public Activity delete(Long id) {
    	Activity activity = activityRepository.getOne(id);
        activityRepository.delete(activity);
        activityRepository.flush();
        return activity;
    }
}
