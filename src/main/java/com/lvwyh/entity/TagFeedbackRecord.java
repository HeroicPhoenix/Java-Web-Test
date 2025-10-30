package com.lvwyh.entity;

import java.util.Date;

public class TagFeedbackRecord {
    private Long id;
    private String tagName;
    private Double feedbackScore;
    private Integer positiveFeedback;
    private Integer negativeFeedback;
    private String feedbackPeriod;
    private Date collectedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Double getFeedbackScore() {
        return feedbackScore;
    }

    public void setFeedbackScore(Double feedbackScore) {
        this.feedbackScore = feedbackScore;
    }

    public Integer getPositiveFeedback() {
        return positiveFeedback;
    }

    public void setPositiveFeedback(Integer positiveFeedback) {
        this.positiveFeedback = positiveFeedback;
    }

    public Integer getNegativeFeedback() {
        return negativeFeedback;
    }

    public void setNegativeFeedback(Integer negativeFeedback) {
        this.negativeFeedback = negativeFeedback;
    }

    public String getFeedbackPeriod() {
        return feedbackPeriod;
    }

    public void setFeedbackPeriod(String feedbackPeriod) {
        this.feedbackPeriod = feedbackPeriod;
    }

    public Date getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(Date collectedAt) {
        this.collectedAt = collectedAt;
    }
}
