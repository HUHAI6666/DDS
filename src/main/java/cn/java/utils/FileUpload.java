package cn.java.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.java.entity.Test;
import cn.java.mapper.TestMapper;

public class FileUpload {
	@Autowired
	private TestMapper testMapper;
	
	//读取文件到list中
	public List<String> readFile(String path){
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader fos = new BufferedReader (new FileReader(path));
			String line = fos.readLine();
			while(line != null){
				list.add(line);
				line = fos.readLine();
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//list转成对象列表
	public List<Test> listToObject(List<String> list){
		int length = list.size();
		List<Test> listTests = new ArrayList<Test>();
		for(int i = 0 ; i < length ; i = i + 3){
			Test test = new Test();
			test.setId(list.get(i).substring(list.get(i).indexOf("/") + 1));
			test.setCt(list.get(i + 1).substring(list.get(i + 1).indexOf("-") + 1));
			test.setMn(list.get(i + 2).substring(list.get(i + 2).indexOf("mn") + 2));
			listTests.add(test);
		}
		return listTests;
	}
	
}
