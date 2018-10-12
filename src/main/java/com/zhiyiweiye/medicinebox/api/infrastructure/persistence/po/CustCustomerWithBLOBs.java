package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

public class CustCustomerWithBLOBs extends CustCustomer {
    private String cancelDesc;

    private String cusDesc;

    private String manaDesc;

    private String cardId;

    private String faceId;

    public String getCancelDesc() {
        return cancelDesc;
    }

    public void setCancelDesc(String cancelDesc) {
        this.cancelDesc = cancelDesc == null ? null : cancelDesc.trim();
    }

    public String getCusDesc() {
        return cusDesc;
    }

    public void setCusDesc(String cusDesc) {
        this.cusDesc = cusDesc == null ? null : cusDesc.trim();
    }

    public String getManaDesc() {
        return manaDesc;
    }

    public void setManaDesc(String manaDesc) {
        this.manaDesc = manaDesc == null ? null : manaDesc.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId == null ? null : faceId.trim();
    }
}