package com.aldaviva.t4e.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.Status;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class TimelineServiceImpl implements TimelineService {

	private static final int CACHE_KEY = 0;

	@Autowired private TimelineDownloader timelineDownloader;

	private LoadingCache<Integer, List<Status>> cache;

	public TimelineServiceImpl(){
		final CacheLoader<Integer, List<Status>> loader = new CacheLoader<Integer, List<Status>>(){
			@Override
			public List<Status> load(final Integer arg0) throws Exception {
				return timelineDownloader.downloadTimeline();
			}
		};

		cache = CacheBuilder.newBuilder()
			.expireAfterWrite(1, TimeUnit.MINUTES)
			.build(loader);
	}

	@Override
	public List<Status> getTimeline() {
		return cache.getUnchecked(CACHE_KEY);
	}

}
