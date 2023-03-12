package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ChallengeAuthComment extends Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeAuthCommentId;

    @Column(nullable = false)
    private String challengeAuthComment;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Long challengeAuthId;


    public ChallengeAuthComment(String challengeAuthComment, String userId, String nickname, Long challengeAuthId) {
        this.challengeAuthComment = challengeAuthComment;
        this.nickname = nickname;
        this.userId = userId;
        this.challengeAuthId = challengeAuthId;
    }

    public void updateChallengeAuthComment(ChallengeAuthCommentRequestDto requestDto) {
        this.challengeAuthComment = requestDto.getComment();
    }
}
