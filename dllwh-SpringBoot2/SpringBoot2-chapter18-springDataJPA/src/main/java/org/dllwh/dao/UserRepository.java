package org.dllwh.dao;

import org.dllwh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 1.Repository是一个空接口，即是一个标记接口
 * 2.若我们定义的接口继承了Repository，则该接口会被IOC容器识别为一个Repository Bean
 * 注入到IOC容器中，进而可以在该接口中定义满足一定规则的接口
 * 
 * 3.它继承Repository接口；继承Repository接口的时候那两个泛型需要指定具体的java类型。第一个泛型是写实体类的类型,
 * 第二个泛型是主键的类型
 */

public interface UserRepository extends JpaRepository<User, Integer> {

}