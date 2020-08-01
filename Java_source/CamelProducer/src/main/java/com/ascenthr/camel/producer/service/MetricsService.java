package com.ascenthr.camel.producer.service;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class MetricsService {
	
	private MeterRegistry meterRegistry;
	private Counter uploadEmployeesRequestCounter;
	
	public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initUploadCounters();
    }
	
	private void initUploadCounters() {
		uploadEmployeesRequestCounter = Counter.builder("upload.employees.request")
                .description("No. of upload employees request")
                .register(meterRegistry);
    }
	
	public void incrementUploadEmployeesRequestCounter() {
		this.uploadEmployeesRequestCounter.increment();
	}

}
