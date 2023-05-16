package com.prgx.deductionManagement.repository;

import com.prgx.deductionManagement.model.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserResource, String> {
}
