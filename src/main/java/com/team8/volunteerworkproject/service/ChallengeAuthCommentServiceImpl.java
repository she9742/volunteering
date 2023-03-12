package com.team8.volunteerworkproject.service;


import com.team8.volunteerworkproject.dto.request.ChallengeAuthCommentRequestDto;
import com.team8.volunteerworkproject.dto.response.ChallengeAuthCommentResponseDto;
import com.team8.volunteerworkproject.entity.ChallengeAuth;
import com.team8.volunteerworkproject.entity.ChallengeAuthComment;
import com.team8.volunteerworkproject.repository.ChallengeAuthCommentRepository;
import com.team8.volunteerworkproject.repository.ChallengeAuthRepository;
import com.team8.volunteerworkproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeAuthCommentServiceImpl implements ChallengeAuthCommentService{

    private final ChallengeAuthCommentRepository challengeAuthCommentRepository;
    private final ChallengeAuthRepository challengeAuthRepository;


    //작성
    @Override
    public ChallengeAuthCommentResponseDto createAuthComment(Long challengeAuthId, ChallengeAuthCommentRequestDto requestDto, UserDetailsImpl userDetails) {

        ChallengeAuth challengeAuth = challengeAuthRepository.findById(challengeAuthId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다.")
        );

        ChallengeAuthComment challengeAuthComment = new ChallengeAuthComment(requestDto.getComment(),userDetails.getUserId(),userDetails.getUser().getNickname(),challengeAuthId);
        challengeAuthCommentRepository.save(challengeAuthComment);
        return new ChallengeAuthCommentResponseDto(challengeAuthComment);
    }

    //수정
    @Override
    public ChallengeAuthCommentResponseDto updateAuthComment(Long challengeAuthId, ChallengeAuthCommentRequestDto requestDto, UserDetailsImpl userDetails, Long challengeAuthCommentId) {

        challengeAuthRepository.findByChallengeAuthId(challengeAuthId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.")
        );

        ChallengeAuthComment challengeAuthComment = challengeAuthCommentRepository.findByChallengeAuthCommentId(challengeAuthId).orElseThrow(
                () -> new IllegalArgumentException("수정할 댓글이 없습니다.")
        );

        if (!userDetails.getUser().isValidId(challengeAuthComment.getUserId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        challengeAuthComment.updateChallengeAuthComment(requestDto);
        return new ChallengeAuthCommentResponseDto(challengeAuthComment);
    }

    //삭제
    @Override
    public String deleteAuthComment(Long challengeAuthId, UserDetailsImpl userDetails, Long challengeAuthCommentId) {

        challengeAuthRepository.findByChallengeAuthId(challengeAuthId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        ChallengeAuthComment challengeAuthComment = challengeAuthCommentRepository.findByChallengeAuthCommentId(challengeAuthCommentId).orElseThrow(
                () -> new IllegalArgumentException("삭제할 댓글이 없습니다.")
        );
        if (!userDetails.getUser().isValidId(challengeAuthComment.getUserId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        challengeAuthCommentRepository.delete(challengeAuthComment);
        return "삭제되었습니다.";
    }

    //조회
    @Override
    @Transactional(readOnly = true)
    public List<ChallengeAuthCommentResponseDto> getChallengeAuthComment(Long challengeAuthId) {
        List<ChallengeAuthComment> challengeAuthComments = challengeAuthCommentRepository.findByChallengeAuthId(challengeAuthId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글에 댓글이 없습니다.")
        );
        List<ChallengeAuthCommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (ChallengeAuthComment challengeAuthComment : challengeAuthComments){
            commentResponseDtoList.add(new ChallengeAuthCommentResponseDto(challengeAuthComment));
        }
        return commentResponseDtoList;
    }
}

