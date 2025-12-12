package com.radianbroker.service;

import com.radianbroker.entity.Diagram;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiagramService {
    boolean generateJpegForReportDiagrams(Long reportId);

    List<Diagram> getAllUnVerifiedReportDiagrams(Long reportId);

    @Async
    void updateDateVerifiedReportDiagrams(Long reportId);
}
