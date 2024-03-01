package com.ess.api.services;

import com.ess.api.entities.Role;
import com.ess.api.exceptions.ResourceNotFoundException;
import com.ess.api.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Add role
    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    // Get role
    public Role getRoleById(long roleId){
        return roleRepository
                .findById(roleId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role", "RoleId", roleId));
    }

    // Get all roles
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public void deleteAll(){
        roleRepository.deleteAll();
    }
}
