package org.apache.geode.examples.MBeanRetrieve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.apache.geode.cache.Cache;
import org.apache.geode.management.ManagementService;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.distributed.DistributedSystem;
import org.apache.geode.management.MemberMXBean;
import org.apache.geode.management.OSMetrics;
import org.apache.geode.management.JVMMetrics;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.apache.geode.cache.GemFireCache;

@SpringBootApplication
@CacheServerApplication(autoStartup = false)
public class MBeanRetrieveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MBeanRetrieveApplication.class, args);
	}

	@Bean
	public Boolean getJVMMetrics(GemFireCache gemfireCache)
	{
		DistributedSystem obj_distributedSystem = gemfireCache.getDistributedSystem(); 
		DistributedMember obj_member = obj_distributedSystem.getDistributedMember();
		ManagementService obj_mgmtSvc = ManagementService.getManagementService((Cache) gemfireCache);
		javax.management.ObjectName memberMBeanName = obj_mgmtSvc.getMemberMBeanName(obj_member);
		MemberMXBean memberMxBean = obj_mgmtSvc.getMBeanInstance(memberMBeanName, MemberMXBean.class);
		JVMMetrics jvmMetrics = memberMxBean.showJVMMetrics();
		System.out.println("---------Print jvmMetrics start----------");
		System.out.println(jvmMetrics.toString());
		System.out.println("---------Print jvmMetrics end----------");

		OSMetrics osMetrics=memberMxBean.showOSMetrics();
		System.out.println("---------Print OSMetrics start----------");
		System.out.println(osMetrics.toString());
		System.out.println("---------Print OSMetrics end----------");

		return true;
	}

}
