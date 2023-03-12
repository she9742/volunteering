package com.team8.volunteerworkproject.dto.response;

import com.team8.volunteerworkproject.entity.ChallengeAuthComment;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ChallengeAuthCommentResponseDto {

    private Long challengeAuthCommentId;
    private Long challengeAuthId;
    private String challengeAuthComment;
    private String nickname;

    public ChallengeAuthCommentResponseDto(ChallengeAuthComment challengeAuthComment) {
        this.challengeAuthCommentId = challengeAuthComment.getChallengeAuthCommentId();
        this.challengeAuthId = challengeAuthComment.getChallengeAuthId();
        this.challengeAuthComment = challengeAuthComment.getChallengeAuthComment();
        this.nickname = challengeAuthComment.getNickname();
    }
}