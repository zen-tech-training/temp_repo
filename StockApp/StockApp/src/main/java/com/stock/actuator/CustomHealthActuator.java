package com.stock.actuator;

import java.util.Random;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthActuator extends AbstractHealthIndicator{

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		Random random = new Random();
		int randomNo = random.nextInt(1000); 
		if(randomNo%2==0) {
			builder.up();
		}
		else {
			builder.down();
		}
	}
	

}
