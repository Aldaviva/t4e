package com.aldaviva.t4e.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.TwitterException;

import com.aldaviva.t4e.service.TimelineService;

@Path("/timeline")
@Component
public class TimelineResource {

	@Autowired private TimelineService timelineService;

	@GET
	@Produces({ "text/plain" })
	public String timelineTsv() throws TwitterException{
		final List<Status> statuses = timelineService.getTimeline();
		final StringBuilder responseStringBuilder = new StringBuilder();
		for (final Status status : statuses) {
			responseStringBuilder.append(status.getUser().getName());
			responseStringBuilder.append('\t');
			responseStringBuilder.append(status.getText().replace('\n', ' '));
			responseStringBuilder.append('\t');
			responseStringBuilder.append(DateTimeFormat.shortDateTime().print(new DateTime(status.getCreatedAt())));
			responseStringBuilder.append("\r\n");
		}
		return responseStringBuilder.toString();
	}

}
