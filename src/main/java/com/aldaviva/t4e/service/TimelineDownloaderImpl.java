package com.aldaviva.t4e.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Service
public class TimelineDownloaderImpl implements TimelineDownloader {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimelineDownloaderImpl.class);

	private @Value("${twitter.consumerKey}") String consumerKey;
	private @Value("${twitter.consumerSecret}") String consumerSecret;
	private @Value("${twitter.accessToken}") String accessToken;
	private @Value("${twitter.accessSecret}") String accessSecret;

	private Twitter remoteService;

	@PostConstruct
	public void init(){
		remoteService = new TwitterFactory().getInstance();
		remoteService.setOAuthConsumer(consumerKey, consumerSecret);
		remoteService.setOAuthAccessToken(new AccessToken(accessToken, accessSecret));
	}

	@Override
	public List<Status> downloadTimeline() throws TwitterException{
		LOGGER.info("Downloading timeline...");
		return remoteService.getHomeTimeline(new Paging(1, 200));
	}

}
