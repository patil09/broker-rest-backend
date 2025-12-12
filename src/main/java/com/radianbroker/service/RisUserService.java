package com.radianbroker.service;


import com.radianbroker.dto.ObservingPractitioner;

import java.util.List;

public interface RisUserService {


	ObservingPractitioner getObservingPractitioner(Long risId, Long mis1);
}
