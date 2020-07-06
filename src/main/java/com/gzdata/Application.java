package com.gzdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 主程序
 *
 *
 * @author 张兵帅
 *
 * @version
 *
 * @since 2019年7月23日
 */
@EnableAsync
@SpringBootApplication(scanBasePackages={"com.gzdata"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
