package com.example.bittask.ui.home.module;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryResponse {

    public static class Category implements Serializable, Parcelable {

        @SerializedName("id")
        @Expose
        private String categoryId;

        @SerializedName("name")
        @Expose
        private String categoryName;

        @SerializedName("category_img")
        @Expose
        private String categoryImage;

        @SerializedName("products")
        @Expose
        private List<Product> products;

        public Category() {
        }

        protected Category(Parcel in) {
            categoryId = in.readString();
            categoryName = in.readString();
            categoryImage = in.readString();
        }

        public static final Creator<Category> CREATOR = new Creator<Category>() {
            @Override
            public Category createFromParcel(Parcel in) {
                return new Category(in);
            }

            @Override
            public Category[] newArray(int size) {
                return new Category[size];
            }
        };

        public String getCategoryId() {
            return categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public String getCategoryImage() {
            return categoryImage;
        }

        public List<Product> getProducts() {
            return products;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(categoryId);
            parcel.writeString(categoryName);
            parcel.writeString(categoryImage);
        }
    }

    public static class Product {

        @SerializedName("id")
        @Expose
        private String productId;

        @SerializedName("name")
        @Expose
        private String productName;

        @SerializedName("weight")
        @Expose
        private String productWeight;

        @SerializedName("price")
        @Expose
        private String productPrice;

        @SerializedName("product_img")
        @Expose
        private String productImage;

        public Product() {

        }

        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public String getProductWeight() {
            return productWeight;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public String getProductImage() {
            return productImage;
        }
    }

}
