package domain.dto;

import domain.BaseModel;

import java.util.List;

public class ProductDTO extends BaseModel{

    /**
     * 产品名称
     */
    private String name;
    /**
     * 简介
     */
    private String introduction;
    /**
     * 价格
     */
    private Double price;
    /**
     * 详情
     */
    private String detail;
    /**
     * 图片地址
     */
    private List<String> picurls;

    private Byte series;

    public List<String> getPicurls() {
        return picurls;
    }

    public void setPicurls(List<String> picurls) {
        this.picurls = picurls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Byte getSeries() {
        return series;
    }

    public void setSeries(Byte series) {
        this.series = series;
    }
}
