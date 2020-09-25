package com.amigoscode.demo.User.Repository;

import java.util.Optional;

import com.amigoscode.demo.User.Model.Role;
import com.amigoscode.demo.User.Model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
