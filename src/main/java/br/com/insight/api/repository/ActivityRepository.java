package br.com.insight.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.insight.api.model.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	List<Activity> findByTypeAndDescription(String type, String Description);
}
