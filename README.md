Offisante Java SDK
======================

This is a Java client library for [Offisante API](http://offisante.fr/)
 
It's __not an official library__ developed by [Offisante](http://offisante.fr/), 
It's a library developed by [Le Comptoir des Pharmacies](https://www.lecomptoirdespharmacies.fr/)
for their own use.

__Written in Java 8__

Features
-------------

- __Overstock :__ Get overstock products in pharmacy
- __Stock :__ Get products stock in pharmacy 
- __Unsold :__ Get unsold products in pharmacy 

Contact
-------------

More information,
you can [mail me](mailto:webmaster@lecomptoirdespharmacies.fr). 

Simple usage 
-------------

```
    public static void main(String[] args){
        UserCredentials credentials = new UserCredentials(
                "<user-name>",
                "<password>",
                "<app-key>"
        );

        OffisanteApi api = new OffisanteApi(credentials);

        final Pharmacy p1 = new Pharmacy("2163366");
        final Pharmacy p2 = new Pharmacy("2105791");
        final Product pt1 = new Product("3400935294227");

        OverstockBody overstocks = api.getOverstockApi().getOverstock(Arrays.asList(p1,p2));

        UnsoldBody unsolds = api.getUnsoldApi().getUnsold(Arrays.asList(p1,p2));

        StockBody stocks = api.getStockApi().getStock(Arrays.asList(p1,p2), Arrays.asList(pt1));
    }
```

License 
-----------
Distributes under [MIT license](http://www.opensource.org/licenses/mit-license.php), see LICENSE file.