package br.com.insight.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insight.api.model.Activity;
import br.com.insight.api.model.User;
import br.com.insight.api.repository.ActivityRepository;
import br.com.insight.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers(String type, String value) {
		try {
			if (type == null || value == null) {
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

	public User create(User newUser) {
		return userRepository.save(newUser);
	}

	public User update(Long id, User userUpdate) {
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

	public User getOne(Long id) {
		return userRepository.getOne(id);
	}

	public User delete(Long id) {
		User user = userRepository.getOne(id);
		userRepository.delete(user);
		userRepository.flush();
		return user;
	}
}
