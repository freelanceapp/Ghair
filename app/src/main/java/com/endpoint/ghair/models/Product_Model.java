package com.endpoint.ghair.models;

import java.io.Serializable;
import java.util.List;

public class Product_Model implements Serializable {
    private int current_page;
private List<Data> data;

    public int getCurrent_page() {
        return current_page;
    }

    public List<Data> getData() {
        return data;
    }

    public class Data implements Serializable {
        private int id;
                private String slug;
                private double price;
                private String ar_title;
                private String en_title;
                private String ar_desc;
                private String en_desc;
                private String model_title;
                private int market_id;
                private int cat_id;
                private int brand_id;
                private int have_offer;
                private int offer_value;
                private String offer_end_date;
                private String offer_start_date;
                private String offer_image;
                private String offer_active;
                private float rating;
                private String main_image;
                private int amount;

                private String title;
                private String desc;
        private String created_at;

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
        }

        public String getSlug() {
            return slug;
        }

        public double getPrice() {
            return price;
        }

        public String getAr_title() {
            return ar_title;
        }

        public String getEn_title() {
            return en_title;
        }

        public String getAr_desc() {
            return ar_desc;
        }

        public String getEn_desc() {
            return en_desc;
        }

        public String getModel_title() {
            return model_title;
        }

        public int getMarket_id() {
            return market_id;
        }

        public int getCat_id() {
            return cat_id;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public int getHave_offer() {
            return have_offer;
        }

        public int getOffer_value() {
            return offer_value;
        }

        public String getOffer_end_date() {
            return offer_end_date;
        }

        public String getOffer_start_date() {
            return offer_start_date;
        }

        public String getOffer_image() {
            return offer_image;
        }

        public String getOffer_active() {
            return offer_active;
        }

        public float getRating() {
            return rating;
        }

        public String getMain_image() {
            return main_image;
        }

        public int getAmount() {
            return amount;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }
    }
}