package com.dept01.bitfleamarket.mapper.product;

import com.dept01.bitfleamarket.pojo.product.ProductImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductImageMapper {
    // 插入产品图片
    @Insert("INSERT INTO product_image(product_id, image_url) VALUES(#{productId}, #{imageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "imageId")
    int insert(ProductImage productImage);

    // 删除产品图片
    @Delete("DELETE FROM product_image WHERE image_id = #{imageId}")
    int delete(int imageId);

    // 根据imageId查询产品图片
    @Select("SELECT * FROM product_image WHERE image_id = #{imageId}")
    ProductImage selectById(int imageId);

    // 查询所有产品图片
    @Select("SELECT * FROM product_image ORDER BY create_time DESC")
    List<ProductImage> selectAll();

    // 根据商品Id查询图片
    @Select("SELECT * FROM product_image WHERE product_id = #{productId} ORDER BY create_time DESC")
    List<ProductImage> selectByProductId(int productId);
}