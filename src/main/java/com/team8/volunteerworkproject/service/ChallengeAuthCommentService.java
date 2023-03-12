package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthCommentResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ChallengeAuthCommentService {

    //작성
    ChallengeAuthCommentResponseDto createAuthComment( Long challengeAuthId, ChallengeAuthCommentRequestDto requestDto,UserDetailsImpl userDetails);

    //수정
    ChallengeAuthCommentResponseDto updateAuthComment( Long challengeAuthId, ChallengeAuthCommentRequestDto requestDto, UserDetailsImpl userDetails,@PathVariable Long challengeAuthCommentId);

    //삭제
    String deleteAuthComment(@PathVariable Long challengeAuthId, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long challengeAuthCommentId);

    //게시글의 댓글 조회
    List<ChallengeAuthCommentResponseDto> getChallengeAuthComment( Long challengeAuthId);
}
