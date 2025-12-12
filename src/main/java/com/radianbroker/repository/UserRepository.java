package com.radianbroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	User findByEmail(String email);

	@Query(value = "SELECT * FROM users  WHERE email=?1 AND status=true", nativeQuery = true)
	User findByEmailAndStatus(String username, boolean status);

	User findByUserId(Long userId);

	@Query(value = "SELECT u.* FROM  users u, "
			+ "user_roles ur, "
			+ "roles r  "
			+ "where  "
			+ "u.user_id=ur.user_id AND "
			+ " ur.role_id = r.role_id AND "
			+ " u.status=true AND  "
			+ " r.status=true AND  "
			+ " r.name=?1", nativeQuery = true)
	List<User> findAllByRole(String role);
}
