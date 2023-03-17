package BusinessLogic;

import java.util.List;

public interface IDeliveryServiceProcessing {

    /**
     * @pre none
     * @post @products.size()>0
     */
    void importProducts();

    /**
     * @pre product is a MenuItem
     * @post @products.contains(product)
     */
    void addProduct(MenuItem product);

    /**
     * @pre products.contains(product)
     * @post !products.contains(product)
     */
    void deleteProduct(MenuItem product);

    /**
     * @pre products.contains(product)
     * @post !products.contains(product) && products.contains(newProduct)
     */
    void updateProduct(MenuItem product, MenuItem newProduct);

    /**
     * @pre products.size()>0 && user!=null
     * @post orders.size() == orders.size()@pre + 1
     */
    void createOrder(User user, List<MenuItem> products);

    /**
     * @pre orders.size()>0 && start >=0l && >=0
     * @post none
     */
    void generateReport1(int start, int finish);

    /**
     * @pre orders.size()>0 && specifiedNr >=0
     * @post none
     */
    void generateReport2(int specifiedNr);

    /**
     * @pre orders.size()>0 && specifiedNrOrderInt >=0 && specifiedPriceInt >=0
     * @post none
     */
    void generateReport3(int specifiedNrOrderInt, int specifiedPriceInt);


    /**
     * @pre orders.size()>0 && yearInt >=0 && monthInt >=0 && dayInt >=0
     * @post none
     */
    void generateReport4(int yearInt, int monthInt, int dayInt);


}
