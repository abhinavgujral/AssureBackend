package com.backend.Assure.dto;

import lombok.Data;

@Data
public class PolicyDTO {

    private Long policyId;  //"policyId"                 : 123,
    private String policyName; //  "policyName"               : "Bajaj",
    private String roomRentLimit;//"roomRentLimit"            : "500000",
    private Float claimBonus; //   "claimBonus"               : 120.0,
    private String pedWaitingPeriod; //     "pedWaitingPeriod"         : "1 year"   waiting period of pre-existing-disease cover
    private Float copayPercent;//  "copayPercent"             : 60.0,
    private Boolean isCriticalIllnessCovered; // "isCriticalIllnessCovered" : true,
    private Boolean isMaternityCovered; //    "isMaternityCovered"       : true,
    private Boolean isRestorationBenefitsCovered; //    "isRestorationBenefitsCovered"  :true,
    private Float premiumUpto18; //  "premiumUpto18"                 : 10000.0,
    private Float premiumUpto45;//    "premiumUpto45"                 :15000.0,
    private Float premiumUpto60;//  "premiumUpto60"                 :20000.0,
    //   "premiumBeyond60"               :25000.0,
    private Float coverAmount1;// "coverAmount1"                  :500000.0,
    private Float coverAmount2;// "coverAmount1"                  :500000.0,
    private Float coverAmount3; // "coverAmount1"                  :500000.0,
    private Integer tenure1; //    "tenure1"                       : 5,
    private Integer tenure2; //   "tenure1"                       : 5,
    private Integer tenure3; ///   "tenure1"                       : 5,

}