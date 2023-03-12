package com.team8.volunteerworkproject.controller;

import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthCommentResponseDto;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import com.team8.volunteerworkproject.service.ChallengeAuthCommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("challenge-auth")
public class ChallengeAuthCommnetController {

    private final ChallengeAuthCommentServiceImpl challengeAuthCommentService;

    //작성
    @PostMapping("/{challengeAuthId}/challengeAuthComments")
    public ResponseEntity<ChallengeAuthCommentResponseDto> createAuthComment(@PathVariable Long challengeAuthId, @RequestBody ChallengeAuthCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(challengeAuthCommentService.createAuthComment(challengeAuthId,requestDto,userDetails));
    }
    //수정
    @PatchMapping("{challengeAuthId}/challengeAuthComment/{challengeAuthCommentId}")
    public ResponseEntity<ChallengeAuthCommentResponseDto> updateAuthComment(@PathVariable Long challengeAuthId, @RequestBody ChallengeAuthCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long challengeAuthCommentId) {
        return ResponseEntity.status(HttpStatus.OK).body(challengeAuthCommentService.updateAuthComment(challengeAuthId,requestDto,userDetails,challengeAuthCommentId));
    }

    //삭제
    @DeleteMapping("{challengeAuthId}/challengeAuthComment/{challengeAuthCommentId}")
    public ResponseEntity<String> deleteAuthComment(@PathVariable Long challengeAuthId, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long challengeAuthCommentId) {
        return ResponseEntity.status(HttpStatus.OK).body(challengeAuthCommentService.deleteAuthComment(challengeAuthId,userDetails,challengeAuthCommentId));
    }

    //게시글의 댓글 조회
    @GetMapping("{challengeAuthId}/challengeAuthComments")
    public ResponseEntity<List<ChallengeAuthCommentResponseDto>> getChallengeAuthComment(@PathVariable Long challengeAuthId){
        return ResponseEntity.status(HttpStatus.OK).body(challengeAuthCommentService.getChallengeAuthComment(challengeAuthId));
    }

}
