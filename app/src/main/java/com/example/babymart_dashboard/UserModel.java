package com.example.babymart_dashboard;

public class UserModel {
        String Name;
        String rating,id;
        String image;


    public UserModel(String name, String rating, String id, String image) {
        Name = name;
        this.rating = rating;
        this.id = id;
        this.image = image;
    }

    public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getRating() {
            return rating;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setRating(String rating) {
            rating = rating;
        }

        public String  getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }}


