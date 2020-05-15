//package com.votesystem.graduation.model;
//
//import com.votesystem.graduation.util.VoteAuditListener;
//import lombok.Data;
//import org.hibernate.envers.RevisionEntity;
//import org.hibernate.envers.RevisionTimestamp;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import java.util.Date;
//
//@Entity
//@Table(name = "revinfo")
//@Data()
//@RevisionEntity(VoteAuditListener.class)
//public class VoteAudit extends AbstractBaseIdEntity {
//
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @RevisionTimestamp
//    private Date timestamp;
//
//
//}
