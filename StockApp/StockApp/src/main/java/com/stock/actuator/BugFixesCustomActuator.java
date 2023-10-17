package com.stock.actuator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Endpoint(id="bug-fixes")
public class BugFixesCustomActuator {

	private Map<String, List<String>> bugFixesByVersionMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
		bugFixesByVersionMap.put("v1", Arrays.asList("Invalid status issue", "Application closing not working"));
		bugFixesByVersionMap.put("v2", Arrays.asList("Window size change not working", "Window minimizing not working"));
	}
	
	@WriteOperation
	public void addBugFixes(@Selector String version, String bugFixes) {
		bugFixesByVersionMap.put(version, Arrays.asList(bugFixes.split(",")));
	}
	@DeleteOperation
	public void deleteBugFixes(@Selector String version) {
		bugFixesByVersionMap.remove(version);
	}
	
	
	@ReadOperation
	public Map<String, List<String>> getAllBugFixes() {
		return this.bugFixesByVersionMap;
	}
	@ReadOperation
	public List<String> getBugFixesByVersion(@Selector String version) {
		return this.bugFixesByVersionMap.get(version);
	}
	
}
