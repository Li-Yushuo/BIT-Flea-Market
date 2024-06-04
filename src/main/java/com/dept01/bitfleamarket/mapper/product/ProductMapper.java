package com.dept01.bitfleamarket.mapper.product;

import com.dept01.bitfleamarket.pojo.product.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    // 插入产品
    @Insert("INSERT INTO product(name, price, purchase_method, product_category, publisher_id, status, inventory, description, is_anonymous, create_time, update_time) VALUES(#{name}, #{price}, #{purchaseMethod}, #{productCategory}, #{publisherId}, #{status}, #{inventory}, #{description}, #{isAnonymous}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    int insertProduct(Product product);

    // 更新产品
    @Update("UPDATE product SET name = #{name}, price = #{price}, purchase_method = #{purchaseMethod}, product_category = #{productCategory}, publisher_id = #{publisherId}, status = #{status}, inventory = #{inventory}, description = #{description}, is_anonymous = #{isAnonymous}, create_time = #{createTime}, update_time = #{updateTime} WHERE product_id = #{productId}")
    int updateProduct(Product product);

    // 删除产品
    @Delete("DELETE FROM product WHERE product_id = #{productId}")
    int deleteProduct(int productId);

    // 根据productId查询产品
    @Select("SELECT * FROM product WHERE product_id = #{productId}")
    Product getProductById(int productId);

    // 查询所有产品
    @Select("SELECT * FROM product")
    List<Product> SelectAll();

    @Select("SELECT * FROM product WHERE publisher_id = #{userId} AND product_id > #{lastProductId} LIMIT #{num}")
    List<Product> findUserProducts(@Param("userId") int userId, @Param("lastProductId") int lastProductId, @Param("num") int num);

    @Select("SELECT COUNT(*) FROM product WHERE publisher_id = #{userId}")
    int countUserProducts(@Param("userId") int userId);
}