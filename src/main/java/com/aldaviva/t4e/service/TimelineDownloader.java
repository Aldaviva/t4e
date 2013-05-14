package com.aldaviva.t4e.service;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

public interface TimelineDownloader {

	List<Status> downloadTimeline() throws TwitterException;

}