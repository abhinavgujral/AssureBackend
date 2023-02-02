package com.backend.Assure.dto;

import com.backend.Assure.entity.*;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToClaimDTO {
    private String type;
    private Long aadharNumber;
    private String nameOfMember;

    private String hospitalName;

    private Date dateOfTreatment;
    private Date submissionDate;
    private String status;
    private String claimItem;
    private Float amountToClaim;
    private Boolean preauthorizedConfirmation;
    private Boolean followUpVisits;


}
