package com.example.basic.global.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 데이터 변경 알림
 * EntityListener 클래스에 적용한 이벤트는 다음 시점에 호출된다.
 *
 * @PostLoad: 해당 엔티티를 새로 불러오거나 refresh 한 이후.
 * @PrePersist: 해당 엔티티를 저장하기 이전
 * @PostPersist: 해당 엔티티를 저장한 이후
 * @PreUpdate: 해당 엔티티를 업데이트 하기 이전
 * @PostUpdate: 해당 엔티티를 업데이트 한 이후
 * @PreRemove: 해당 엔티티를 삭제하기 이전
 * @PostRemove: 해당 엔티티를 삭제한 이후
 *
 * 날짜 필드 상속 처리
 */

@Getter
@MappedSuperclass //JPA Entity 클래스들이 DateEntity 를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 합니다.
@EntityListeners(AuditingEntityListener.class) //DateEntity 클래스에 Auditing 기능을 포함시킵니다.
public abstract class DateEntity implements Serializable {

    @CreatedDate //Entity 가 생성되어 저장될 때 시간이 자동 저장됩니다.
    private LocalDateTime createDate;

    @LastModifiedDate //조회한 Entity 의 값을 변경할 때 시간이 자동 저장됩니다.
    private LocalDateTime modifiedDate;
}
