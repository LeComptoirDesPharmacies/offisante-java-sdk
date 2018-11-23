package fr.lecomptoirdespharmacies.core;

public class Constant {

    /**~~~~~~~~~~~~~~~ URL ~~~~~~~~~~~~~~~ **/
    // Main
    public static final String PRODUCTION_BASE_URL = "https://api.offisante.fr";
    public static final String DEV_BASE_URL = "http://api.offisante.fr/apidev";

    /** ~~~~~~~~~~~~~~~ URI ~~~~~~~~~~~~~~~ **/
    // AUTH
    public static final String AUTH_URI = "/1/auth";

    // Api
    public static final String OVERSTOCK_API = "/1/dw/overstock";


    /** ~~~~~~~~~~~~~~~ HEADER ~~~~~~~~~~~~~~~ **/
    public static final String HEADER_AUTHORIZATION = "Authorization";

    /** ~~~~~~~~~~~~~~~ PACKAGE ~~~~~~~~~~~~~~~ **/
    public static final String ENTITY_PACKAGE_NAME = "fr.lecomptoirdespharmacies.entity";

    public static final String DEFAULT_DATE_PATTERN = "EEE, dd MMM yyyy HH:mm:ss Z";

}
