package com.blubank.interviewpostquestion.repository;

import com.blubank.interviewpostquestion.domain.Like;
import com.blubank.interviewpostquestion.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findFirstByTweetAndLikedBy(Tweet tweet, String likedBy);
}
