package com.ascenthr.camel.producer.service;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class MetricsService {
	
	private MeterRegistry meterRegistry;
	private Counter uploadEmployeesRequestCounter;
	private Counter messageSentToQueueCounter;
	private Counter employeeSentToQueueCounter;
	
	public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initUploadCounters();
    }
	
	private void initUploadCounters() {
		uploadEmployeesRequestCounter = Counter.builder("upload.employees.request")
                .description("No. of upload employees request")
                .register(meterRegistry);
		messageSentToQueueCounter = Counter.builder("message.sent.to.queue")
                .description("No. of message sent to queue")
                .register(meterRegistry);
		employeeSentToQueueCounter = Counter.builder("employee.sent.to.queue")
                .description("No. of employee sent to queue")
                .register(meterRegistry);
    }
	
	public void incrementUploadEmployeesRequestCounter() {
		this.uploadEmployeesRequestCounter.increment();
	}
	
	public void incrementMessageSentToQueueCounter(double amount) {
		this.messageSentToQueueCounter.increment(amount);
	}
	
	public void incrementEmployeeSentToQueueCounter(double amount) {
		this.employeeSentToQueueCounter.increment(amount);
	}

}
