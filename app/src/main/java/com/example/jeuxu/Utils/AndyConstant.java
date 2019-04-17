package com.example.jeuxu.Utils;

import android.util.Log;

public class AndyConstant {

    public class ServiceCode {
        public static final int HOME = 1;
    }

    // web service url constants
    public class ServiceType {
        public static final String BASEURL = "http://jeuxuniversitaires.online/";
        public static final String BASEURLe = "http://192.168.8.105/";
        public static final String STATISTIQUEALL="http://192.168.8.105/ju/statis.json";
        public static final String URL = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
        public static final String FOOT_TAG = BASEURL + "api/user/getMatch/?id_discipline=15&genre=M&min_id=0";
        public static final String VOLLEY_TAG = BASEURL + "api/user/getMatch/?id_discipline=23&genre=M&min_id=0";
        public static final String TENNIS_TAG = BASEURL + "api/user/getMatch/?id_discipline=12&genre=M&min_id=0";
        public static final String TENNIS_TABLE_TAG = BASEURL + "api/user/getMatch/?id_discipline=15&genre=M&min_id=0";
        public static final String PARA_TENIS_TAG = BASEURL + "api/user/getMatch/?id_discipline=20&genre=M&min_id=0";
        public static final String PARA_ATHE_TAG = BASEURL + "api/user/getMatch/?id_discipline=19&genre=M&min_id=0";
        public static final String LUTTE_TAG = BASEURL + "api/user/getMatch/?id_discipline=23&genre=M&min_id=0";
        public static final String JUDO_TAG = BASEURL + "api/user/getMatch/?id_discipline=12&genre=M&min_id=0";
        public static final String HANDBALL_TAG = BASEURL + "api/user/getMatch/?id_discipline=16&genre=M&min_id=0";
        public static final String FAN_CLUB_TAG = BASEURL + "api/user/getMatch/?id_discipline=23&genre=M&min_id=0";
        public static final String BASKETBALL_TAG = BASEURL + "api/user/getMatch/?id_discipline=16&genre=M&min_id=0";
        public static final String ATHE_TAG = BASEURL + "api/user/getMatch/?id_discipline=11&genre=M&min_id=0";
        public static final String REFERENCE = BASEURL + "api/admin/Discipline";
        public static final String LOGIN = BASEURL + "api/user/connect?";
        public static final String REGISTER = BASEURL + "api/user/createAccount?";
        public static final String CHANGE_PROFILS = BASEURL + "api/user/createAccount?";
        public static final String FOOT = BASEURL + "api/user/getMatch/";
        public static final String ARTICLE_REVUE = BASEURL + "api/user/";
        public static final String SECURITER_NUMERO = BASEURL + "api/user/";


        //Actu
        public static final String ACTUS = BASEURLe + "ju/actualite.json";
        public static final String FOOT_ACC = BASEURLe + "ju/actualite.json";
        public static final String HANDBALL_ACC = BASEURLe + "ju/actualite.json";
        public static final String JUDO_ACC = BASEURLe + "ju/actualite.json";
        public static final String LUTTES_ACC = BASEURLe + "ju/actualite.json";
        public static final String TENNIS_ACC = BASEURLe + "actualite.json";
        public static final String TENNIS_TABLE_ACC = BASEURLe + "ju/actualite.json";
        public static final String VOLLEYBALL_ACC = BASEURLe + "ju/actualite.json";
        public static final String FANCLUB_ACC = BASEURLe + "ju/actualite.json";
        public static final String ATHLETISME_ACC = BASEURLe + "ju/actualite.json";
        public static final String BASKET_ACC = BASEURLe + "ju/actualite.json";
        public static final String HANDISPORT_ACC = BASEURLe + "ju/actualite.json";

    }

    // webservice key constants
    public class Params {
        public static final String KEY_SUCCESS = "status";
        public static final String KEY_MSG = "errorCode";

        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String SEXE = "sexe";
        public static final String DATE_NAISSANCE = "date_naissance";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String NOM_LIEU = "nom_lieux";
        public static final String lat = "lat";
        public static final String lng = "lng";
        public static final String equipes = "equipes";
        public static final String nomEquipe = "nomEquipe";
        public static final String ID_UNIVERCITE = "univercite";
        public static final String icon = "icon";
        public static final String TOKEN = "token";
        public static final String joueurs = "joueurs";
        public static final String nom_joueur = "nom";
        public static final String prenom = "prenom";
        public static final String sexe = "sexe";
        public static final String poste = "poste";
        public static final String but = "but";
        public static final String statistics = "statistics";
        public static final String nbr_point = "nbr_point";
        public static final String unite = "unite";
        public static final String nom_stat = "nom";
        public static final String valeur = "valeur";
    }
}
