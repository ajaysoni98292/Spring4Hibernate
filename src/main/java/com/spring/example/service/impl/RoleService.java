package com.spring.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.example.persistence.dao.IRoleDao;
import com.spring.example.persistence.dao.common.IOperations;
import com.spring.example.persistence.model.Role;
import com.spring.example.service.IRoleService;
import com.spring.example.service.common.AbstractService;

@Service
public class RoleService extends AbstractService<Role> implements IRoleService {

	@Autowired(required=true)
    private IRoleDao dao;

    public RoleService() {
        super();
    }

	@Override
	protected IOperations<Role> getDao() {
		 return dao;
	}

	@Override
	public Map<String, String> getAllRole() {
		List<Role> roleList = getDao().findAll();
		Map<String,String> roleMap = new HashMap<>();
		for(Role role : roleList){
			roleMap.put(String.valueOf(role.getId()), role.getRoleName());
		}
		return roleMap;
	}

}
