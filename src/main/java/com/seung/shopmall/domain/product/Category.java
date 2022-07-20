package com.seung.shopmall.domain.product;

public enum Category {
    TOP("상의"),
    COAT("아우터"),
    PANTS("바지"),
    SNEAKERS("스니커즈"),
    BAG("가방"),
    HAT("모자"),
    SOCKS("양말"),
    ACCESSORY("액세서리");

    private String koName;
    Category(String koName) {
        this.koName = koName;
    }

    public String getKoName() {
        return koName;
    }

    public static Category findByKoName(String koName){
        for (Category category : values()){
            if (category.koName.equals(koName)){
                return category;
            }
        }
        return null;
    }
}
