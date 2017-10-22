package com.example.pcf;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

@RestController
@RequestMapping(value = "/eureka")
public class EurekaController {

	@RequestMapping("/service-instances")
	public @ResponseBody List<Application> serviceInstancesByApplicationName() {
		DiscoveryClient discoveryClient = DiscoveryManager.getInstance().getDiscoveryClient();

		if (null != discoveryClient) {
			Applications apps = discoveryClient.getApplications();
			List<Application> appslist = apps.getRegisteredApplications();
			appslist.forEach(app -> {
				System.out.println(app);
			});
			return appslist;
		}
		return null;
	}

}
