package com.radianbroker.service;

import com.radianbroker.entity.Visit;
import org.springframework.stereotype.Service;

@Service
public interface VisitService {
    Boolean sendReportPaymentToAccounts(Long risId, Long orderNo);

    Boolean sendReportPaymentToAccounts(Visit visit, String examCodes);
}
