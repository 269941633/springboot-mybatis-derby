package com.fei.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import com.fei.dao.UserMapper;
import com.fei.domain.User;

@Service
public class UserService{

	private static Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 如果表不存在，则创建表
	 */
	public void createTableIfNotExist(){
		try {
			userMapper.count();
		} catch (BadSqlGrammarException  e) {
			//42X05 表示表或试图不存在
			if(e.getSQLException().getSQLState().equals("42X05")){
				try {
					userMapper.createTable();
				} catch (Exception e2) {
					LOG.error("创建表异常",e2);
				}
				
			}else{
				LOG.error("创建表未知异常",e);
			}
			
		}catch(Exception e){
			LOG.error("创建表未知异常",e);
		}
	}
	
	public void insert(User u){
		this.userMapper.insert(u);
	}
	
	public List<User> queryByPage(String userName,int pageNum,int pageSize){
		int startIndex = (pageNum - 1)*pageSize;
		return userMapper.queryByPage(userName,startIndex, pageSize);
	}
	
	public List<User> query(String userName){
		return userMapper.query(userName);
	}
	
}
