package cn.chenxins.authorization.model;

import java.util.List;

public class AuthResJson {
    private Integer id;

    private Integer createTime;

    private String name;

    private Integer resType;

    private String url;

    private String endpoint;

    private String description;

    private String icon;

    private Integer seq;

    private Integer parentId;

    private Integer hidden;

    private List<AuthResJson> children;

    public AuthResJson(){

    }

    public AuthResJson(Integer id, Integer createTime, String name, Integer resType, String url,
                   String endpoint, String description, String icon, Integer seq, Integer parentId, Integer hidden, List<AuthResJson> children) {
        this.id = id;
        this.createTime = createTime;
        this.name = name;
        this.resType = resType;
        this.url = url;
        this.endpoint = endpoint;
        this.description = description;
        this.icon = icon;
        this.seq = seq;
        this.parentId=parentId;
        this.hidden=hidden;
        this.children=children;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public List<AuthResJson> getChildren() {
        return children;
    }

    public void setChildren(List<AuthResJson> children) {
        this.children = children;
    }
}
