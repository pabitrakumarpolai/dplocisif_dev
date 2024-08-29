package com.dplocisif.DPLOCISIF.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dcpy_email_details")
public class EmailDetailsModule {
    @Id
    @Column(name = "ACTION_TYPE")
    private String actionType;

    @Column(name = "RECIPIENT_GROUP_ID")
    private Long recipientGroupId;

    @Column(name = "EMAIL_SENDER")
    private String emailSender;

    @Column(name = "EMAIL_SUBJECT")
    private String emailSubject;

    @Column(name = "EMAIL_BODY")
    private String emailBody;

    @Column(name = "EMAIL_ACKNOWLEDGE")
    private String emailAcknowledge;
}
