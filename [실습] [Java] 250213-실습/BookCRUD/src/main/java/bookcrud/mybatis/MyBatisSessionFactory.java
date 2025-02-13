package bookcrud.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

// 이 Java Class 가 하는 일은 SqlSession 이라는 객체가 우리 DAO 에 필요
// 이 객체를 통해 SqlSession 객체를 뽑아낼 수 있음
// 이 SqlSession 객체를 이용해야지만 SQL 문 실행 가능
public class MyBatisSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "./SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);

            if (sqlSessionFactory == null) {
                sqlSessionFactory =
                        new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
