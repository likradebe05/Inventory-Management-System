package za.ac.cput.Factory;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import za.ac.cput.Domain.Product;
import za.ac.cput.Util.Helper;

public class ProductFactory {
    public static Product createProduct(MysqlxDatatypes.Scalar.String productId, String name, double price, int quantity,
                                        String categoryId, String supplierId) {

        if (Helper.isNullOrEmpty(productId.toString()) || Helper.isNullOrEmpty(name)
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
    
    public static Product createProductWithImage(String productId, String name, double price, int quantity,
                                               String categoryId, String supplierId, 
                                               byte[] imageData, String imageContentType, String originalImageName) {

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
                .setImageData(imageData)
                .setImageContentType(imageContentType)
                .setOriginalImageName(originalImageName)
                .build();
    }
}

