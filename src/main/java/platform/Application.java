package platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan(basePackages =
		{
		"platform.zframe.dao","platform.app.*.*.dao"
		})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
