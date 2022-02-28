package com.example.gitajob.modelos;



    /*
    *  Peculiaridades de getPlayerSummaries,
    *       - dependiendo del las opciones de privacidad devuelverá más o menos datos
    *       - dependiendo de la informacion que ha rellenado el usuario devolverá más o menos datos
    *
    *
    *       --> hacer varios constructores y comprobar en el get el valor de los datos.
    *
    * */


public class PlayerSummaries {
    String steamid;
    String communityvisibilitystate;
    String profilestate;
    String personaname;
    String profileurl;
    String avatar;
    String avatarmedium;
    String avatarfull;
    String avatarhash;
    String personastate;
    String realname;
    String primaryclanid;
    String timecreated;
    String personastateflags;
    String loccountrycode;
    String locstatecode;
    String loccityid;
    String lastlogoff;









    //constructor bacio

    public PlayerSummaries() {

    }


    //Construcutor de usuario basico

    public PlayerSummaries(String steamid, String communityvisibilitystate, String personaname, String avatarmedium, String realname) {
        this.steamid = steamid;
        this.communityvisibilitystate = communityvisibilitystate;
        this.personaname = personaname;
        this.avatarmedium = avatarmedium;
        this.realname = realname;
    }
        //Constructor para usuario como Linkeriyo
    public PlayerSummaries(String steamid, String communityvisibilitystate, String profilestate, String personaname, String profileurl, String avatar, String avatarmedium, String avatarfull, String avatarhash, String personastate, String primaryclanid, String timecreated, String personastateflags, String lastlogoff) {
        this.steamid = steamid;
        this.communityvisibilitystate = communityvisibilitystate;
        this.profilestate = profilestate;
        this.personaname = personaname;
        this.profileurl = profileurl;
        this.avatar = avatar;
        this.avatarmedium = avatarmedium;
        this.avatarfull = avatarfull;
        this.avatarhash = avatarhash;
        this.personastate = personastate;
        this.primaryclanid = primaryclanid;
        this.timecreated = timecreated;
        this.personastateflags = personastateflags;
        this.lastlogoff = lastlogoff;
    }


    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getCommunityvisibilitystate() {
        return communityvisibilitystate;
    }

    public void setCommunityvisibilitystate(String communityvisibilitystate) {
        this.communityvisibilitystate = communityvisibilitystate;
    }

    public String getProfilestate() {
        return profilestate;
    }

    public void setProfilestate(String profilestate) {
        this.profilestate = profilestate;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarmedium() {
        return avatarmedium;
    }

    public void setAvatarmedium(String avatarmedium) {
        this.avatarmedium = avatarmedium;
    }

    public String getAvatarfull() {
        return avatarfull;
    }

    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    public String getAvatarhash() {
        return avatarhash;
    }

    public void setAvatarhash(String avatarhash) {
        this.avatarhash = avatarhash;
    }

    public String getPersonastate() {
        return personastate;
    }

    public void setPersonastate(String personastate) {
        this.personastate = personastate;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPrimaryclanid() {
        return primaryclanid;
    }

    public void setPrimaryclanid(String primaryclanid) {
        this.primaryclanid = primaryclanid;
    }

    public String getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(String timecreated) {
        this.timecreated = timecreated;
    }

    public String getPersonastateflags() {
        return personastateflags;
    }

    public void setPersonastateflags(String personastateflags) {
        this.personastateflags = personastateflags;
    }

    public String getLoccountrycode() {
        return loccountrycode;
    }

    public void setLoccountrycode(String loccountrycode) {
        this.loccountrycode = loccountrycode;
    }

    public String getLocstatecode() {
        return locstatecode;
    }

    public void setLocstatecode(String locstatecode) {
        this.locstatecode = locstatecode;
    }

    public String getLoccityid() {
        return loccityid;
    }

    public void setLoccityid(String loccityid) {
        this.loccityid = loccityid;
    }
}
