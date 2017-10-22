package com.example.pcf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/test")
public class RestControllerService {

	TestService ts;
	GitConfig gitConfig;
	
	@Autowired
	public RestControllerService(TestService ts, GitConfig gitConfig) {
		super();
		this.ts = ts;
		this.gitConfig = gitConfig;
	}

	@HystrixCommand(fallbackMethod = "getTestBackup")
	@GetMapping(value = "/service")
	@ResponseBody
	ResponseEntity<Test> getTest() {
		System.out.println("test service executed");
		return new ResponseEntity<>(ts.findOne(1l), HttpStatus.OK);
	}

	@GetMapping(value = "/git")
	@ResponseBody
	ResponseEntity<String> getGit() {
		System.out.println("Git service executed");
		return new ResponseEntity<>(gitConfig.getMessage(), HttpStatus.OK);
	}
	
	@ResponseBody
	ResponseEntity<Test> getTestBackup() {
		System.out.println("getTestBackup service executed");
		Test test = new Test();
		test.setId(1l);
		test.setName("adhi-name from fallback method");
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
	
}