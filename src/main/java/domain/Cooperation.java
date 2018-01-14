package domain;

public class Cooperation extends BaseModel{
    /**
     *姓名
     */
    private String name;
    /**
     * 类型
     */
    private Byte type;
    /**
     * 简介
     */
    private String profile;

    private String content;

    private Byte lang;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getLang() {
        return lang;
    }

    public void setLang(Byte lang) {
        this.lang = lang;
    }
}
