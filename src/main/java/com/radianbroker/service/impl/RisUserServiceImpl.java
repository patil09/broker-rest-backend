package com.radianbroker.service.impl;


import com.radianbroker.dto.ObservingPractitioner;
import com.radianbroker.entity.RisUser;
import com.radianbroker.repository.RisUserRepository;
import com.radianbroker.service.RisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RisUserServiceImpl implements RisUserService {

	@Autowired
	RisUserRepository risUserRepository;

	@Override
	public ObservingPractitioner getObservingPractitioner(Long risId, Long userId) {
		// TODO Auto-generated method stub
		RisUser mis1RisUser = getRisUserByUserIdAndRisId(userId, risId);
//		User mis1User = mis1RisUser.getUser();
		ObservingPractitioner observingPractitioner = new ObservingPractitioner(mis1RisUser.getUserName(), mis1RisUser.getLastNameHl7(), mis1RisUser.getFirstNameHl7());
		return observingPractitioner;
	}

	public RisUser getRisUserByUserIdAndRisId(Long userId, Long risId) {
		// TODO Auto-generated method stub
		return risUserRepository.findByUserIdAndRisId(userId, risId);
	}
}
