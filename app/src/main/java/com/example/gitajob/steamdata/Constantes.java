package com.example.gitajob.steamdata;


//clase que gestionara las apis y forma de acceder a ellas dependiendo de usuario/videojuego;


public class Constantes {
    private String idpasada;
    private String codigovideojuego;

    private static final String STEAM_KEY = "3A14C27710748C26952CA27A940F45CA";
    private static final String STEAM_PROFILE_URL_START = "https://steamcommunity.com/profiles/";
    private static final String STEAM_PROFILE_URL_END = "/?json=1";
    private static final String STEAM_PROFILE_DEL_RANDOM_CON_DINERO = "";
    //es con la que he estado haciendo pruebas y ya de paso la dejo de inicio para mostrar el uso de la aplicacion


    private final String getNewsForApp = "http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid="+codigovideojuego+"&count=3&maxlength=300&format=json";
    private  final String GetGlobalAchievementPercentagesForApp = "http://api.steampowered.com/ISteamUserStats/GetGlobalAchievementPercentagesForApp/v0002/?gameid="+codigovideojuego+"&format=json";
    private  final String GetFriendList = ": http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key="+STEAM_KEY+"&steamid="+idpasada+"&relationship=friend";
    private  final String GetPlayerSummaries = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+STEAM_KEY+"&steamids="+idpasada+"";
    private  final String GetPlayerAchievements = " http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/?appid="+codigovideojuego+"&key="+STEAM_KEY+"&steamid="+idpasada+"";
    private  final String GetUserStatsForGame = " http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid="+codigovideojuego+"&key="+STEAM_KEY+"&steamid="+idpasada+"";
    private  final String GetOwnedGames = ": http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+STEAM_KEY+"&steamid="+idpasada+"&format=json";
    private  final String GetRecentlyPlayedGames  = " http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key="+STEAM_KEY+"&steamid="+idpasada+"&format=json";


    public Constantes(String idpasada) {
        this.idpasada = idpasada;

    }


    public Constantes(String idpasada,String codigovideojuego) {
        this.idpasada = idpasada;
        this.codigovideojuego = codigovideojuego;

    }


    public String getIdpasada() {
        return idpasada;
    }

    public void setIdpasada(String idpasada) {
        this.idpasada = idpasada;
    }

    public String getCodigovideojuego() {
        return codigovideojuego;
    }

    public void setCodigovideojuego(String codigovideojuego) {
        this.codigovideojuego = codigovideojuego;
    }

    public static String getSteamProfileUrlStart() {
        return STEAM_PROFILE_URL_START;
    }

    public static String getSteamProfileUrlEnd() {
        return STEAM_PROFILE_URL_END;
    }

    public static String getSteamProfileDelRandomConDinero() {
        return STEAM_PROFILE_DEL_RANDOM_CON_DINERO;
    }

    public String getGetNewsForApp() {
        return getNewsForApp;
    }

    public String getGetGlobalAchievementPercentagesForApp() {
        return GetGlobalAchievementPercentagesForApp;
    }

    public String getGetFriendList() {
        return GetFriendList;
    }

    public String getGetPlayerSummaries() {
        return GetPlayerSummaries;
    }

    public String getGetPlayerAchievements() {
        return GetPlayerAchievements;
    }

    public String getGetUserStatsForGame() {
        return GetUserStatsForGame;
    }

    public String getGetOwnedGames() {
        return GetOwnedGames;
    }

    public String getGetRecentlyPlayedGames() {
        return GetRecentlyPlayedGames;
    }
}
