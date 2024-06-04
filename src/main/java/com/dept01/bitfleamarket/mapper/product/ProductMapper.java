package com.dept01.bitfleamarket.mapper.product;

import com.dept01.bitfleamarket.pojo.product.Product;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ProductMapper {

    // 新建产品
    @Insert("INSERT INTO product(name, price, purchase_method, product_category, publisher_id, status, inventory, description, is_anonymous) VALUES(#{name}, #{price}, #{purchaseMethod}, #{productCategory}, #{publisherId}, #{status}, #{inventory}, #{description}, #{isAnonymous})")
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    int insertProduct(Product product);
    // 以后创建和更新User或者Product都不需要管createTime和updateTime，因为数据库的触发器会自动填充这两个字段

    // 查询所有产品
    @Select("SELECT * FROM product ORDER BY update_time DESC")
    List<Product> selectAll();

    // 依据productId查找商品
    @Select("SELECT * FROM product WHERE product_id = #{productId} ORDER BY update_time DESC")
    Product selectByProductId(Integer productId);

    // 依据publisherId查找商品
    @Select("SELECT * FROM product WHERE publisher_id = #{publisherId} ORDER BY update_time DESC")
    List<Product> selectByPublisherId(Integer publisherId);

    // 复合条件查找
    List<Product> selectByCondition(Integer productId, String name, BigDecimal price, String purchaseMethod, String productCategory, Integer publisherId, String status, Integer inventory, String description, Integer isAnonymous, LocalDateTime beginCreateTime, LocalDateTime endCreateTime, LocalDateTime beginUpdateTime, LocalDateTime endUpdateTime);
    // 调用这个方法时，你必须提供所有的参数，否则编译器会报错。这意味着你不能省略任何参数。  然而，你可以传入null作为参数值，这在某些情况下可以被视为"不传"参数
    // 应用示例：List<User> products = ProductMapper.selectByCondition("null", "pen", null, null, null, null, null, null, null, null, null, null, null, null)
    // 可以参照Test中的UserMapperTest

    // 更新product，只有传入的非null属性才会被更新
    int update(Product product);

    // 删除产品
    @Delete("DELETE FROM product WHERE product_id = #{productId}")
    int deleteProduct(Integer productId);

    @Select("SELECT COUNT(*) FROM product WHERE publisher_id = #{publisherId}")
    int countByPublisherId(Integer publisherId);

    @Select({
            "SET @rownum := 0;",
            "SET @last_rank := 0;",
            "SELECT @last_rank := rank",
            "FROM (",
            "    SELECT *, @rownum := @rownum + 1 AS rank",
            "    FROM product",
            "    WHERE publisher_id = #{userId}",
            "    ORDER BY update_time DESC, product_id DESC",
            ") t",
            "WHERE product_id = #{lastProductId};",
            "SELECT *",
            "FROM (",
            "    SELECT *, @rownum := @rownum + 1 AS rank",
            "    FROM product",
            "    WHERE publisher_id = #{userId}",
            "    ORDER BY update_time DESC, product_id DESC",
            ") t",
            "WHERE rank > @last_rank",
            "LIMIT #{num}"
    })
    List<Product> ShowProductsByNum(@Param("userId") Integer userId, @Param("lastProductId") Integer lastProductId, @Param("num") Integer num);
}