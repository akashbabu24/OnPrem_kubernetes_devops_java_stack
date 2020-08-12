package com.ascenthr.camel.consumer.service;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class MetricsService {
	
	private MeterRegistry meterRegistry;
	private Counter messageReceivedFromQueueCounter;
	private Counter employeeReceivedFromQueueCounter;
	
	public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initUploadCounters();
    }
	
	private void initUploadCounters() {
		messageReceivedFromQueueCounter = Counter.builder("message.received.from.queue")
                .description("No. of message received from queue")
                .register(meterRegistry);
		employeeReceivedFromQueueCounter = Counter.builder("employee.received.from.queue")
                .description("No. of employee received from queue")
                .register(meterRegistry);
    }
	
	public void incrementMessageReceivedFromQueueCounter() {
		this.messageReceivedFromQueueCounter.increment();
	}
	
	public void incrementEmployeeReceivedFromQueueCounter(double amount) {
		this.employeeReceivedFromQueueCounter.increment(amount);
	}

}
