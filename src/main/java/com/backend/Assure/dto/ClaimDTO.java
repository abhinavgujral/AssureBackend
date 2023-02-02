package com.backend.Assure.dto;

import com.backend.Assure.entity.Document;
import com.backend.Assure.entity.Members;
import com.backend.Assure.entity.PolicyBookings;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDTO {

    private Long id;
    private Long memberId;
    private String userName;
    private Date dateOfClaim;
    private Float amountToClaim;
    private Date submissionDate;
    private String nameOfMember;
    private String status;
    private String claimItem;
    private String policyBookingName;
}
