package cn.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import cn.java.entity.Test;

public interface TestMapper {
	@Insert({"<script> REPLACE into test(id,ct,mn) values " +
            "<foreach collection=\"list\" item=\"item\" index=\"index\"  separator=\",\"> "+
            "(#{item.id},#{item.ct},#{item.mn})" +
            "</foreach> </script>"})
	void insert(List<Test> list);
	
	@Insert("insert into test(id,ct,mn) values (#{arg0},#{arg1},#{arg2})")
	Integer ineString(String l,String m,String n);
}
