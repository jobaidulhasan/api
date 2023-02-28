package com.example.api_project;

public class DATA {

    /////String Variable-------------------------->
    String name,typs,price,color,id,code,parentCode,brandName,productBarcode;


    /////Create a Constructor---------------------->
    public DATA(String name, String typs, String price, String color, String id, String code, String parentCode, String brandName, String productBarcode) {
        this.name = name;
        this.typs = typs;
        this.price = price;
        this.color = color;
        this.id = id;
        this.code = code;
        this.parentCode = parentCode;
        this.brandName = brandName;
        this.productBarcode = productBarcode;
    }



    public String getName() {
        return name;
    }

    public String getTyps() {
        return typs;
    }

    public String getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getProductBarcode() {
        return productBarcode;
    }
}
