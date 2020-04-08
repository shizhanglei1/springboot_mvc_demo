import com.mybatis.dao.IUserMapper;
import com.mybatis.domain.User;
import com.mybatis.mybatis.io.Resources;
import com.mybatis.mybatis.session.SqlSession;
import com.mybatis.mybatis.session.SqlSessionFactory;
import com.mybatis.mybatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试用例，将整个工程串联起来
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件,将配置文件转换为输入流
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //返回DefaultSqlSessionFactory对象，利用XMLConfigBuilder类从输入流中读取配置信息。
        // 封装成Configuration对象传入DefaultSqlSessionFactory的构造方法
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂(DefaultSqlSessionFactory)生产SqlSession对象,
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserMapper mapper = session.getMapper(IUserMapper.class);
        //5.使用代理对象执行方法
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
