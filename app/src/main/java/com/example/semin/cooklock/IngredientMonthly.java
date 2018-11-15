package com.example.semin.cooklock;

public class IngredientMonthly {
  // 사이트로부터 응답 받은 값 저장할 틀
    int igrdNo; //- 식재료 컨텐츠 번호 : cntntsNo

    String igrdName; //- 재료명 : fdmtNm
    String imgSrc; //"http://www.nongsaro.go.kr/" + rtnFileCours + rtnStreFileNm
    String toBuy; //- 품종 특성 구입 요령 :prchCheatDtl
    String toMaintain; //- 보관법 및 손질법 : cstdyMthDtl
    String toAccept; //- 섭취 방법 : ntkMthDtl

    public int getIgrdNo() {
        return igrdNo;
    }

    public void setIgrdNo(int igrdNo) {
        this.igrdNo = igrdNo;
    }

    public String getIgrdName() {
        return igrdName;
    }

    public void setIgrdName(String igrdName) {
        this.igrdName = igrdName;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getToBuy() {
        return toBuy;
    }

    public void setToBuy(String toBuy) {
        this.toBuy = toBuy;
    }

    public String getToMaintain() {
        return toMaintain;
    }

    public void setToMaintain(String toMaintain) {
        this.toMaintain = toMaintain;
    }

    public String getToAccept() {
        return toAccept;
    }

    public void setToAccept(String toAccept) {
        this.toAccept = toAccept;
    }

    @Override
    public String toString() {
        return "IngredientMonthly{}" + getIgrdNo() + " : \n" +
                getIgrdName() + getImgSrc() + getToAccept() +
                getToBuy() + getToMaintain();
    }
}
