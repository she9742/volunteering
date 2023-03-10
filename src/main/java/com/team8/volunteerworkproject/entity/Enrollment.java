package com.team8.volunteerworkproject.entity;

import com.team8.volunteerworkproject.dto.request.EnrollmentRequestDto;
import com.team8.volunteerworkproject.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Enrollment extends  Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.TRUE;

    private String userId;

    @Column(nullable = false)
    private String username;

    @Column
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private VolunteerWorkPost post;

    @Column(name = "post_id", insertable=false, updatable=false)
    private Long postId;

    @Column
    private int maxEnrollmentNum;
    @Column
    private LocalDateTime endTime;


    public Enrollment(Long postId, EnrollmentRequestDto requestDto, String userId, VolunteerWorkPost post) {
        this.userId = userId;
        this.username = requestDto.getUsername();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.post = post;
        this.maxEnrollmentNum = post.getMaxEnrollmentNum();
        this.endTime = post.getEndTime();
    }
    public void updateStatus(EnrollmentStatus newStatus) {
        this.enrollmentStatus = newStatus;
    }
}