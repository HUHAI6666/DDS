package cn.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import cn.java.entity.Account;

public interface AccountMapper {
	@Insert({"<script> REPLACE into account(userName,password,name,idNo,nickName,phone,email) values " +
            "<foreach collection=\"list\" item=\"item\" index=\"index\"  separator=\",\"> "+
            "(#{item.userName},#{item.password},#{item.name},#{item.idNo},#{item.nickName},#{item.phone},#{item.email})" +
            "</foreach> </script>"})
//	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void insert(List<Account> list);
//	
//	@Insert("insert into test(id,ct,mn) values (#{arg0},#{arg1},#{arg2})")
//	Integer ineString(String l,String m,String n);
}
