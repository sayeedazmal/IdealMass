package com.example.idealhome.Pojo;

import android.graphics.Bitmap;
import android.net.Uri;

public class Member {

    private String memberName;
    private String memberImage;

    public Member() {
    }

    public Member(String memberName, String memberImage) {
        this.memberImage = memberImage;
        this.memberName = memberName;
    }

    public String getMemberImage() {
        return memberImage;
    }

    public void setMemberImage(String memberImage) {
        this.memberImage = memberImage;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
