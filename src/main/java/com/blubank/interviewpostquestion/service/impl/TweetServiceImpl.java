package com.blubank.interviewpostquestion.service.impl;

import com.blubank.interviewpostquestion.domain.Like;
import com.blubank.interviewpostquestion.domain.Tweet;
import com.blubank.interviewpostquestion.repository.LikeRepository;
import com.blubank.interviewpostquestion.repository.TweetRepository;
import com.blubank.interviewpostquestion.service.TweetService;
import com.blubank.interviewpostquestion.service.api.tweet.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;
    private final LikeRepository likeRepository;

    @Override
    public TweetSaveResult save(TweetSaveParam param) {
        Tweet tweet = Tweet.builder()
                .likes(new HashSet<>())
                .content(param.getContent())
                .author(param.getAuthor())
                .build();
        tweet = tweetRepository.save(tweet);
        return TweetSaveResult.builder()
                .tweetId(tweet.getId())
                .build();
    }

    @Override
    public TweetLikeResult like(TweetLikeParam param) {
        // TODO: use locking mechanism along with unique constrains for handling concurrency issue
        Tweet tweet = tweetRepository.findById(param.getTweetId()).orElseThrow(() -> new EntityNotFoundException("tweet not found"));
        Optional<Like> alreadyLikedOptional = likeRepository.findFirstByTweetAndLikedBy(tweet, param.getLikedBy());
        if (alreadyLikedOptional.isPresent()) {
            return new TweetLikeResult(false);
        }
        Like like = Like.builder()
                .tweet(tweet)
                .likedBy(param.getLikedBy())
                .build();
        likeRepository.save(like);
        return new TweetLikeResult(true);
    }

    @Override
    public TweetModel load(TweetLoadParam param) {
        return null;
    }
}
