package com.example.CustomerSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CustomerSystem.entity.MstUser;

@Repository
public interface UserRepository extends JpaRepository<MstUser, Integer> {
	MstUser findByUserIdAndPasswordAndDeleteYmdIsNull(String userId, String password);
}
