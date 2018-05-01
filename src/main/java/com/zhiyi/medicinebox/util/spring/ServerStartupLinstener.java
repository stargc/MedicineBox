package com.zhiyi.medicinebox.util.spring;


import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * @author guanchen
 * @creation 2016年8月18日 下午1:08:55
 */
public class ServerStartupLinstener implements
		ApplicationListener<ContextRefreshedEvent> {

	/***
	 * 当server启动完成后 回调此方法
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					onStartup();
				}
			}, 100);
	}
	private void onStartup(){
		
	}
}
