package com.gk.springtest.beans;

/**
 * Created by padmaka on 8/2/16.
 */

public class Profile {

    private long id;
    private String email;
    private String name;
    private String first_name;
    private String last_name;
    private AgeRange age_range;
    private String link;
    private String gender;
    private String locale;
    private Picture picture;
    private int timezone;
    private String updated_time;
    private boolean verified;

    public Profile(){

    }

    public Profile(int id, String email, String name, String first_name, String last_name, AgeRange age_range, String link, String gender, String locale, Picture picture, int timezone, String updated_time, boolean verified) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age_range = age_range;
        this.link = link;
        this.gender = gender;
        this.locale = locale;
        this.picture = picture;
        this.timezone = timezone;
        this.updated_time = updated_time;
        this.verified = verified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public AgeRange getAge_range() {
        return age_range;
    }

    public void setAge_range(AgeRange age_range) {
        this.age_range = age_range;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString(){

        return "{\n" +
                "    \"id\": \"" + this.id + "\",\n" +
                "    \"email\": \"" + this.email + "\",\n" +
                "    \"name\": \"" + this.name + "\",\n" +
                "    \"first_name\": \"" + this.first_name + "\",\n" +
                "    \"last_name\": \"" + this.last_name + "\",\n" +
                "    \"age_range\": {\n" +
                "        \"min\": " + this.age_range.getMin() + "\n" +
                "    },\n" +
                "    \"link\": \"" + this.link + "\",\n" +
                "    \"gender\": \"" + this.gender + "\",\n" +
                "    \"locale\": \"" + this.locale + "\",\n" +
                "    \"picture\": {\n" +
                "        \"data\": {\n" +
                "            \"is_silhouette\": " + this.picture.getData().is_silhouette() + ",\n" +
                "            \"url\": \"" + this.picture.getData().getUrl() + "\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"timezone\": " + this.timezone + ",\n" +
                "    \"updated_time\": \"" + this.updated_time + "\",\n" +
                "    \"verified\": " + this.verified + "\n" +
                "}";
    }
}
