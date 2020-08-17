package br.com.insight.api.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.insight.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	
	List<User> findAllByOrderByIdAsc();

}