package com.dept01.bitfleamarket.mapper.product;

import com.dept01.bitfleamarket.pojo.product.ProductImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductImageMapper {
    // 插入产品图片
    @Insert("INSERT INTO product_image(product_id, image_url, create_time) VALUES(#{productId}, #{imageUrl}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "imageId")
    int insertProductImage(ProductImage productImage);

    // 更新产品图片
    @Update("UPDATE product_image SET product_id = #{productId}, image_url = #{imageUrl}, create_time = #{createTime} WHERE image_id = #{imageId}")
    int updateProductImage(ProductImage productImage);

    // 删除产品图片
    @Delete("DELETE FROM product_image WHERE image_id = #{imageId}")
    int deleteProductImage(int imageId);

    // 根据imageId查询产品图片
    @Select("SELECT * FROM product_image WHERE image_id = #{imageId}")
    ProductImage getProductImageById(int imageId);

    // 查询所有产品图片
    @Select("SELECT * FROM product_image")
    List<ProductImage> findAll();
}