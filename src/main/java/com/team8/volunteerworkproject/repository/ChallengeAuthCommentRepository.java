package com.team8.volunteerworkproject.repository;

import com.team8.volunteerworkproject.entity.ChallengeAuth;
import com.team8.volunteerworkproject.entity.ChallengeAuthComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeAuthCommentRepository extends JpaRepository<ChallengeAuthComment, Long> {

    Optional<ChallengeAuthComment> findByChallengeAuthCommentId(Long challengeAuthCommentId);
    Optional<List<ChallengeAuthComment>> findByChallengeAuthId(Long challengeAuthId);

//    List<ChallengeAuthComment> findAllByChallengeAuthId(ChallengeAuth challengeAuthId);
}
