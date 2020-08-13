package br.com.insight.api.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.insight.api.model.Activity;
import br.com.insight.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	
//	List<User> findByActivities(List activities);
    
//	List<User> (String type, String Description);
//	@Autowired
//	@Override
//	default User getOne(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}