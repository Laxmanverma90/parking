package com.ing.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.parking.entity.Role;

/**
 * @author Laxman
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByRoleName(String name);

	public Role findByRoleId(int roleId);

}
