package cn.java.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import cn.java.entity.Rule;

public interface RuleMapper {
	
	@Insert("insert into rule(name,idNo,phone,nickName,email,userName,password) values(#{name},#{idNo},#{phone},#{nickName},#{email},#{userName},#{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")/*增加这个注解插入记录后会返回自增长的id*/
	int insert(Rule rule);
	
	@Select("select * from Rule r order by r.id desc limit 1")
	Rule getRule();
}