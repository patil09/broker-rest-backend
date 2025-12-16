package com.radianbroker.service;

import java.util.List;

import com.radianbroker.entity.Mip;
import com.radianbroker.projections.MipsProjection;

public interface MipService {

	List<Mip> getMips(Long risId)throws Exception;

	 List<MipsProjection> getActiveAllowedMips(String sessionRole,Long risId);

}
