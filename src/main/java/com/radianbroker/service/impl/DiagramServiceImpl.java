package com.radianbroker.service.impl;


import com.radianbroker.entity.Diagram;
import com.radianbroker.entity.Diagram.FileType;
import com.radianbroker.repository.DiagramRepository;
import com.radianbroker.service.DiagramService;
import com.radianbroker.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiagramServiceImpl implements DiagramService {

    @Value("${json2img.base.url}")
    private String baseUrl;

    @Autowired
    DiagramRepository diagramRepository;

    @Autowired
    FileSystemStorageService fileSystemStorageService;
    @Override
    public boolean generateJpegForReportDiagrams(Long reportId) {

        String jpegFileExtension = new String(".").concat(FileType.JPEG.name().toLowerCase());
        String jsonFileExtension = new String(".").concat(FileType.JSON.name().toLowerCase());

        String url = baseUrl + "/json2img?path={path}";
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity(headers);
        Map<String, String> params = new HashMap<String, String>();

        List<Diagram> diagrams = diagramRepository.getAllByReportIdAndFileTypeAndDateVerifiedIsNull(reportId, FileType.JSON.name());
//		List<Diagram> succeedDiagrams = new ArrayList<Diagram>();
        if (diagrams.size() > 0) {
            diagrams.stream().forEach((diagram) -> {
                String path = null;
                try {
                    path = fileSystemStorageService.load(diagram.getFilePath()).toString();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                params.put("path", path);
                URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(params).toUri();
                ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, request, String.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    System.out.println(response.getBody());
                    diagram.setFileType(FileType.JPEG);
                    String filePath = diagram.getFilePath();
                    filePath = filePath.replace(jsonFileExtension, jpegFileExtension);
                    diagram.setFilePath(filePath);
                    diagram = diagramRepository.save(diagram);
//					succeedDiagrams.add(diagram);
                } else {
                    throw new RuntimeException("Failed to convert jpeg for reportId : " + reportId);
                }

            });
        }
//		return succeedDiagrams;
        return true;
    }

    @Override
    public List<Diagram> getAllUnVerifiedReportDiagrams(Long reportId) {
        return diagramRepository.getAllByReportIdAndFileTypeAndDateVerifiedIsNull(reportId, FileType.JPEG.name());
    }

    @Async
    @Override
    public void updateDateVerifiedReportDiagrams(Long reportId) {
        List<Diagram> diagrams = diagramRepository.getAllByReportIdAndFileTypeAndDateVerifiedIsNull(reportId, FileType.JPEG.name());
        diagrams.forEach(diagram ->{
            diagram.setDateVerified(LocalDate.now());
        });
        diagramRepository.saveAll(diagrams);
    }
}
