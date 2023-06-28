package com.field.muzi.domain.trip;

import com.field.muzi.domain.base.BaseTimeEntity;
import com.field.muzi.domain.base.SeqManager;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.entity.SecondCourseEntity;
import com.field.muzi.setup.FileManagerEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Table(name = "trip")
@DynamicInsert
@DynamicUpdate
@Entity
public class TripEntity extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_trip")
    @GenericGenerator(name = "seq_manager_trip", strategy = "com.field.muzi.domain.base.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "trip_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String tripSeq;

    @Column
    private String name;

    @Column
    private String tel;

    @Column
    private String email;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne
    @JoinColumn(name = "memberSeq")
    private MemberEntity member;

    @OneToOne
    @JoinColumn(name = "secondCourseSeq")
    private SecondCourseEntity course;

    @Column
    private String state;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean withdrawal;

    public static TripEntity create(String name, String tel, String email, String content, MemberEntity member, SecondCourseEntity course) {
        TripEntity trip = new TripEntity();
        trip.setName(name);
        trip.setTel(tel);
        trip.setEmail(email);
        trip.setContent(content);
        trip.setMember(member);
        trip.setCourse(course);
        trip.setState("신청");
        return trip;
    }

    public void update(String state) {
        this.setState(state);
    }

    public void withdrawal() {
        this.setWithdrawal(true);
    }
}
