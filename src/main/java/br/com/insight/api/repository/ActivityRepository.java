package br.com.insight.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.insight.api.model.Activity;
import br.com.insight.api.model.User;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	List<Activity> findByTypeAndDescription(String type, String Description);
	List<Activity> findAllByUserOrderByIdAsc(User user);
	List<Activity> findAllByUserAndTypeOrderByIdAsc(User user, String type);
	
	
}
