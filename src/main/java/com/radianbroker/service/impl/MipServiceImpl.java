package com.radianbroker.service.impl;

import com.radianbroker.entity.Group;
import com.radianbroker.entity.Mip;
import com.radianbroker.entity.User;
import com.radianbroker.enums.Roles;
import com.radianbroker.projections.MipsProjection;
import com.radianbroker.repository.GroupRepository;
import com.radianbroker.repository.MipRepository;
import com.radianbroker.repository.UserRepository;
import com.radianbroker.service.RisService;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.radianbroker.service.MipService;

@Service
public class MipServiceImpl implements MipService {
	Logger logger = LoggerFactory.getLogger(MipServiceImpl.class);
	
	@Autowired
	MipServiceImpl(@Lazy UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	UserDetailsServiceImpl userDetailsService;

	@Value("${broker.ris.code}")
	private String risCode;

	@Autowired
	RisService risService;
	@Autowired
	MipRepository mipRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GroupRepository groupRepository;

	@Override
	public List<Mip> getMips(Long risId)throws Exception {
		return mipRepository.findAllByRisId(risId);
	}

	@Override
	public List<MipsProjection> getActiveAllowedMips(String sessionRole) {

	    Long userId = userDetailsService.getCurrentAuditor().getId();
	    Roles role = Roles.of(sessionRole);

	    List<MipsProjection> mips = new ArrayList<>();

	    switch (role) {

	        case GROUPADMIN:
	            User user = userRepository.findById(userId)
	                .orElseThrow(() ->
	                    new RuntimeException("Error: User is not found for id: " + userId)
	                );

	            Group group = groupRepository
	                .findById(user.getGroupIdForAdmin())
	                .orElseThrow(() ->
	                    new RuntimeException(
	                        "Error: Group is not found for id: " + user.getGroupIdForAdmin()
	                    )
	                );

	            Long groupId = group.getGroupId();
	            mips = mipRepository.findForGroupAdminByGroupId(groupId);
	            break;

	        default:
	            // optionally log or handle other roles
	            break;
	    }

	    return mips;
	}

}
