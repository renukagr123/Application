package com.example.myjwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.myjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query(value = "Select * FROM renudb.users where username=?", nativeQuery = true)
	public  User findByName(String username);
	
	@Query(value="Select * FROM renudb.users where verification_code=?", nativeQuery = true)
    public User findByVerificationCode(String code);
	
}
