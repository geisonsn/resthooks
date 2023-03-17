package bosh.resthooks;


public class Hook {

    private Integer id;

    private String url;

    private String topicId;

    private String hookUri;

    private String topicName;

    private String topicDescription;

    public Hook(){}

    public Hook(Integer id,  String url, String topicId, String hookUri, String topicName, String topicDescription) {
        this.setId(id);
        this.setUrl(url);
        this.setTopicId(topicId);
        this.setHookUri(hookUri);
        this.setTopicName(topicName);
        this.setTopicDescription(topicDescription);
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

    private static Hook builder;

    public static Hook builder() {
        builder = new Hook();
        return builder;
    }

    public Hook id(Integer id) {
        this.builder.setId(id);
        return this.builder;
    }

    public Hook url(String url) {
        this.builder.setUrl(url);
        return this.builder;
    }

    public Hook topicId(String topicId) {
        this.builder.setTopicName(topicId);
        return this.builder;
    }

    public Hook hookUri(String hookUri) {
        this.builder.setHookUri(hookUri);
        return this.builder;
    }

    public Hook topicName(String topicName) {
        this.builder.setTopicName(topicName);
        return this.builder;
    }

    public Hook topicDescription(String topicDescription) {
        this.builder.setTopicDescription(topicDescription);
        return this.builder;
    }
}