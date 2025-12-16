package com.radianbroker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radianbroker.exceptions.ResourceNotFoundException;
import com.radianbroker.service.MipService;


@RestController
@RequestMapping("/api")
public class GroupAdminController {
	
	@Autowired
	MipService mipService;
	
	Logger logger = LoggerFactory.getLogger(GroupAdminController.class);

	@PreAuthorize("hasAnyRole('ROLE_GROUPADMIN')")
	@GetMapping("/mips")
	public ResponseEntity<?> getActiveAllowedMips(@CookieValue(name = "SESSIONROLE", required = true) String sessionRole,
			@RequestParam(value = "risId", required = true) Long risId) {
		try {
			return new ResponseEntity<>(mipService.getActiveAllowedMips(sessionRole,risId), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			logger.error("Error found: {}", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error found: {}", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
