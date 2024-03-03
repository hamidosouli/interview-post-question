package com.blubank.interviewpostquestion.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: use EmbeddedId to add unique constrain for (likedBy + tweetId)

    @Column
    private String likedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", referencedColumnName = "id")
    private Tweet tweet;


    // maybe a better way for these equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return id.equals(like.id) && likedBy.equals(like.likedBy) && tweet.equals(like.tweet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, likedBy, tweet);
    }
}
