package com.totemcrew.payload.request;

public class CreateStudentResource {

    private String parentFullName;

    private String phoneParent;

    private Long userId;
    private Long sectionId;

    public void setParentFullName(String parentFullName) {
        this.parentFullName = parentFullName;
    }

    public void setPhoneParent(String phoneParent) {
        this.phoneParent = phoneParent;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getParentFullName() {
        return parentFullName;
    }

    public String getPhoneParent() {
        return phoneParent;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSectionId() {
        return sectionId;
    }
}
