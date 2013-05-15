package com.aldaviva.t4e.service;

import java.util.List;

import twitter4j.Status;

public interface TimelineService {

	List<Status> getTimeline();

}
