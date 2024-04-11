package fr.lecomptoirdespharmacies.offisante.core;

public class Constant {

    /**~~~~~~~~~~~~~~~ URL ~~~~~~~~~~~~~~~ **/
    // Main
    public static final String PRODUCTION_BASE_URL = "https://api.offisante.fr";
    public static final String DEV_BASE_URL = "https://api.offisante.fr/apidev";

    /** ~~~~~~~~~~~~~~~ URI ~~~~~~~~~~~~~~~ **/
    // AUTH
    public static final String AUTH_URI = "/1/auth";

    // Api
    public static final String OVERSTOCK_API = "/2/overstock";
    public static final String STOCK_API = "/2/stock";
    public static final String PHARMACY_API = "/1/dw/pharmacies";

    /** ~~~~~~~~~~~~~~~ HEADER ~~~~~~~~~~~~~~~ **/
    public static final String HEADER_AUTHORIZATION = "Authorization";

    /** ~~~~~~~~~~~~~~~ DATE PATTERNS ~~~~~~~~~~~~~~~ **/
    public static final String DEFAULT_DATE_TIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss Z";
    public static final String DEFAULT_PRODUCT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


    /** ~~~~~~~~~~~~~~~ ERRORS ~~~~~~~~~~~~~~~ **/
    public static final int UNKNOWN_TOKEN = -5;
    public static final int TOKEN_RATE_LIMIT_REACHED = -6;
    public static final int MALFORMED_TOKEN = -7;


    public final static int MAX_RETRY = 8;
    public final static int START_RETRY = 1;
    public static final String JSON_PRODUCTS_KEY = "products";

}
