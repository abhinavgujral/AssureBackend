package com.backend.Assure.dto;

import com.backend.Assure.entity.Hospitals;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalLocationDTO {

    private Long bookingId;
    private String policyName;
    private String Location;
    private String Hospital;

}
