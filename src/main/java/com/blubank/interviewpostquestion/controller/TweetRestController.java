package com.blubank.interviewpostquestion.controller;

import com.blubank.interviewpostquestion.controller.api.tweet.TweetSaveRequest;
import com.blubank.interviewpostquestion.controller.api.tweet.TweetSaveResponse;
import com.blubank.interviewpostquestion.service.TweetService;
import com.blubank.interviewpostquestion.service.api.tweet.TweetSaveParam;
import com.blubank.interviewpostquestion.service.api.tweet.TweetSaveResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/tweets")
public class TweetRestController {

    private final TweetService tweetService;

    @PostMapping
    public TweetSaveResponse saveTweet(@RequestBody TweetSaveRequest tweetSaveRequest) {
        TweetSaveResult tweetSaveResult = tweetService.save(TweetSaveParam.builder()
                .author(tweetSaveRequest.getAuthor())
                .content(tweetSaveRequest.getContent())
                .build());
        return TweetSaveResponse.builder().tweetId(tweetSaveResult.getTweetId()).build();
    }
}
