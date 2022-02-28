package com.example.gitajob.modelos;

public class Game {

    String appid;
    String name;
    String playtime_forever;
    String img_icon_url;
    String img_logo_url;
    String playtime_2weeks;
    String playtime_windows_forever;
    String playtime_mac_forever;
    String playtime_linux_forever;



    public Game(String appid,
                String name,
                String playtime_forever,
              String img_icon_url, String img_logo_url)
     {
        this.appid = appid;
        this.img_icon_url = img_icon_url;
        this.name = name;
        this.playtime_forever = playtime_forever;
        this.img_logo_url = img_logo_url;
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaytime_forever() {
        return playtime_forever;
    }

    public void setPlaytime_forever(String playtime_forever) {
        this.playtime_forever = playtime_forever;
    }

    public String getImg_icon_url() {
        return img_icon_url;
    }

    public void setImg_icon_url(String img_icon_url) {
        this.img_icon_url = img_icon_url;
    }

    public String getImg_logo_url() {
        return img_logo_url;
    }

    public void setImg_logo_url(String img_logo_url) {
        this.img_logo_url = img_logo_url;
    }

    public String getPlaytime_windows_forever() {
        return playtime_windows_forever;
    }

    public void setPlaytime_windows_forever(String playtime_windows_forever) {
        this.playtime_windows_forever = playtime_windows_forever;
    }

    public String getPlaytime_mac_forever() {
        return playtime_mac_forever;
    }

    public void setPlaytime_mac_forever(String playtime_mac_forever) {
        this.playtime_mac_forever = playtime_mac_forever;
    }

    public String getPlaytime_linux_forever() {
        return playtime_linux_forever;
    }

    public void setPlaytime_linux_forever(String playtime_linux_forever) {
        this.playtime_linux_forever = playtime_linux_forever;
    }

    public String getPlaytime_2weeks() {
        return playtime_2weeks;
    }

    public void setPlaytime_2weeks(String playtime_2weeks) {
        this.playtime_2weeks = playtime_2weeks;
    }
}
