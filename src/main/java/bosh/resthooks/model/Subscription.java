package bosh.resthooks.model;

import java.util.Objects;

public class Subscription {

    private Integer id;

    private String url;

    private String topicId;

    private String hookUri;

    private String topicName;

    private String topicDescription;

    private boolean status;

    public Subscription() {}

    public Subscription(Integer id, String url, String topicId, String hookUri, String topicName, String topicDescription, boolean status) {
        this.id = id;
        this.url = url;
        this.topicId = topicId;
        this.hookUri = hookUri;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getHookUri() {
        return hookUri;
    }

    public void setHookUri(String hookUri) {
        this.hookUri = hookUri;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return status == that.status && id.equals(that.id) && url.equals(that.url) && topicId.equals(that.topicId) && hookUri.equals(that.hookUri) && topicName.equals(that.topicName) && topicDescription.equals(that.topicDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", hookUri='" + hookUri + '\'' +
                ", status=" + status +
                '}';
    }

    private static Subscription builder;

    public static Subscription builder() {
        builder = new Subscription();
        return builder;
    }

    public Subscription id(Integer id) {
        this.builder.setId(id);
        return this.builder;
    }

    public Subscription url(String url) {
        this.builder.setUrl(url);
        return this.builder;
    }

    public Subscription topicId(String topicId) {
        this.builder.setTopicId(topicId);
        return this.builder;
    }

    public Subscription hookUri(String hookUri) {
        this.builder.setHookUri(hookUri);
        return this.builder;
    }

    public Subscription topicName(String topicName) {
        this.builder.setTopicName(topicName);
        return this.builder;
    }

    public Subscription topicDescription(String topicDescription) {
        this.builder.setTopicDescription(topicDescription);
        return this.builder;
    }

    public Subscription status(boolean status) {
        this.builder.status = status;
        return this.builder;
    }
}
