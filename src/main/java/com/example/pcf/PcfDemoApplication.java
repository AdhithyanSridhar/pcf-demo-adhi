package com.example.pcf;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class PcfDemoApplication implements CommandLineRunner {

	TestService ts;

	@Autowired
	public PcfDemoApplication(TestService ts) {
		super();
		this.ts = ts;
	}

	public static void main(String[] args) {
		SpringApplication.run(PcfDemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		Stream.of("adhi", "barath").forEach(name -> {
			ts.save(new Test(name));
		});
	}
}
