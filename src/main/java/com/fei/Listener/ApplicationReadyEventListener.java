package com.fei.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.fei.service.UserService;

/**
 * 监听spring boot的启动
 * @author Jfei
 *
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		initCreateDbTable();
		
	}
	/**
	 * 如果数据库表不存在，则创建表
	 */
	private void initCreateDbTable(){
		userService.createTableIfNotExist();
	}

}
