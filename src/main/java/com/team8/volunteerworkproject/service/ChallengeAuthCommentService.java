package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthCommentResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ChallengeAuthCommentService {

    //작성
    ChallengeAuthCommentResponseDto createAuthComment(@PathVariable Long challengeAuthId, @RequestBody ChallengeAuthCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails);

    //수정
    ChallengeAuthCommentResponseDto updateAuthComment(@PathVariable Long challengeAuthId, @RequestBody ChallengeAuthCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long challengeAuthCommentId);

    //삭제
    String deleteAuthComment(@PathVariable Long challengeAuthId, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long challengeAuthCommentId);

    //게시글의 댓글 조회

    List<ChallengeAuthCommentResponseDto> getChallengeAuthComment(@PathVariable Long challengeAuthId);
}
