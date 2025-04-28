package com.ongi.ongi_back.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TossCancel {
  private int cancelAmount;
  private String cancelReason;
  private int taxFreeAmount;
  private int taxExemptionAmount;
  private int refundableAmount;
  private int transferDiscountAmount;
  private int easyPayDiscountAmount;
  private String canceledAt;
  private String transactionKey;
  private String receiptKey;
  private String cancelStatus;
  private String cancelRequestId;
}
