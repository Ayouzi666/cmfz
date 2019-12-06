package com.yyn.util;
/*
   
   @author yyn
   @version 1.8
   @create 2019-09-10-16:17
*/


import java.text.SimpleDateFormat;
import java.util.Date;

public class utils {
    /*private static ThreadLocal<SqlSession> tol = new ThreadLocal<>();
    private static SqlSessionFactory factory;

    static {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("初始化工厂失败");
        } finally {
            try {
                resourceAsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SqlSession openSession() {
        SqlSession sqlSession = tol.get();
        if (sqlSession == null) {
            sqlSession = factory.openSession();
            tol.set(sqlSession);
        }
        return sqlSession;
    }

    public static  <T> T getMapper(Class<T> val) {
        SqlSession sqlSession = tol.get();
        T mapper = null;
        if (sqlSession == null) {
            sqlSession = factory.openSession();
            tol.set(sqlSession);
            mapper = sqlSession.getMapper(val);
        } else {
            mapper = sqlSession.getMapper(val);
        }
        return mapper;
    }

    public static void close() {
        SqlSession sqlSession = tol.get();
        if (sqlSession != null) {
            sqlSession.close();
            tol.remove();
        }
    }

    public static void commit() {
        SqlSession sqlSession = tol.get();
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }

    public static void rollback() {
        SqlSession sqlSession = tol.get();
        if (sqlSession != null) {
            sqlSession.rollback();
        }
    }*/

    //将utilDate装换成指定格式的字符串
    public static String formatDate(Date utilDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(utilDate);
        return strDate;
    }

    //将字符串转换成utilDate
    public static Date conversStrDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = null;
        try {
            utilDate = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilDate;
    }

    //utilDate转换成sqlDate
    public static Date conversSqlDate(Date date) {
        long time = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(time);
        return sqlDate;
    }
}
