package com.ironrookcomputing.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironrookcomputing.hibernate.beans.Setting;
import com.ironrookcomputing.services.SettingServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/settings")
public class SettingsController {
	
	@PostMapping
	public ResponseEntity<Setting> addSetting(@RequestBody Setting setting) {
		setting = settingServices.addSetting(setting);
		
		if(setting != null) {
			return ResponseEntity.ok(setting);
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Setting> getSetting(@RequestParam String name) {
		Setting setting = settingServices.getSetting(name);
		
		if(setting != null) {
			return ResponseEntity.ok(setting);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	public ResponseEntity<Setting> updateSetting(@RequestBody Setting setting) {
		setting = settingServices.updateSetting(setting);
		
		if(setting != null) {
			return ResponseEntity.ok(setting);
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteSetting(@RequestBody Setting setting) {
		return ResponseEntity.noContent().build();
	}
	
	private SettingServices settingServices = new SettingServices();
}
