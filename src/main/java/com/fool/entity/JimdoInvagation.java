package com.fool.entity;


/**
 * 前台 导航栏 对象
 */
public class JimdoInvagation {
    // 导航Key
    private String indexKey;
    // 导航ID
    private String indexID;
    private double score;
    // 导航名称
    private String name;
    // 导航地址
    private String url;
    // 导航级别
    private String level;
    // 父级导航
    private String parent;

    public JimdoInvagation() {
    }

    /**
     * Constructor
     *
     * @param indexKey
     * @param indexID
     * @param name
     * @param url
     * @param level
     * @param parent
     */
    public JimdoInvagation(String indexKey, String indexID, String name, String url, String level, String parent, double score) {
        this.indexKey = indexKey;
        this.indexID = indexID;
        this.name = name;
        this.url = url;
        this.level = level;
        this.parent = parent;
        this.score = score;
    }

    /**
     * Getter and Setter
     */
    public String getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(String indexKey) {
        this.indexKey = indexKey;
    }

    public String getIndexID() {
        return indexID;
    }

    public void setIndexID(String indexID) {
        this.indexID = indexID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "JimdoInvagation{" +
                "indexKey='" + indexKey + '\'' +
                ", indexID='" + indexID + '\'' +
                ", score=" + score +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", level='" + level + '\'' +
                ", parent='" + parent + '\'' +
                '}';
    }
}
