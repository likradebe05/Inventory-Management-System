package za.ac.cput.Factory;
import za.ac.cput.Domain.Product;
import za.ac.cput.Util.Helper;

public class ProductFactory {
    public static Product createProduct(String productId, String name, double price, int quantity,
                                        String categoryId, String supplierId) {

        if (Helper.isNullOrEmpty(productId) || Helper.isNullOrEmpty(name)
                || Helper.isNullOrEmpty(categoryId) || Helper.isNullOrEmpty(supplierId)) {
            return null;
        }

        if (price < 0 || quantity < 0) {
            return null;
        }

        return new Product.Builder()
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .setCategoryId(categoryId)
                .setSupplierId(supplierId)
                .build();
    }
}

