package com.ahsiu.studytest.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * Created by hsiunien on 2014/7/12.
 */
public class Lang {
    /**
     * 根据格式化字符串，生成运行时异常
     *
     * @return 运行时异常
     */
    public static RuntimeException makeThrow(String format, Object... args) {
        return new RuntimeException(String.format(format, args));
    }

    /**
     * 根据格式化字符串，生成一个指定的异常。
     *
     * @param classOfT 异常类型， 需要有一个字符串为参数的构造函数
     * @return 异常对象
     */
    public static <T extends Throwable> T makeThrow(Class<T> classOfT,
                                                    String format,
                                                    Object... args) {
//              return Mirror.me(classOfT).born(String.format(format, args));
        return null;
    }

    /**
     * 将抛出对象包裹成运行时异常，并增加自己的描述
     *
     * @param e    抛出对象
     * @param fmt  格式
     * @param args 参数
     * @return 运行时异常
     */
    public static RuntimeException wrapThrow(Throwable e, String fmt, Object... args) {
        return new RuntimeException(String.format(fmt, args), e);
    }

    /**
     * 用运行时异常包裹抛出对象，如果抛出对象本身就是运行时异常，则直接返回。
     * <p/>
     * 如果是 InvocationTargetException，那么将其剥离，只包裹其 TargetException
     *
     * @param e 抛出对象
     * @return 运行时异常
     */
    public static RuntimeException wrapThrow(Throwable e) {
        if (e instanceof RuntimeException)
            return (RuntimeException) e;
        if (e instanceof InvocationTargetException)
            return wrapThrow(((InvocationTargetException) e).getTargetException());
        return new RuntimeException(e);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个Type类型实际对应的Class
     */
    @SuppressWarnings("rawtypes")
    public static Class<?> getTypeClass(Type type) {
        Class<?> clazz = null;
        if (type instanceof Class<?>) {
            clazz = (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            clazz = (Class<?>) pt.getRawType();
        } else if (type instanceof GenericArrayType) {
            GenericArrayType gat = (GenericArrayType) type;
            Class<?> typeClass = getTypeClass(gat.getGenericComponentType());
            return Array.newInstance(typeClass, 0).getClass();
        } else if (type instanceof TypeVariable) {
            TypeVariable tv = (TypeVariable) type;
            Type[] ts = tv.getBounds();
            if (ts != null && ts.length > 0)
                return getTypeClass(ts[0]);
        } else if (type instanceof WildcardType) {
            WildcardType wt = (WildcardType) type;
            Type[] t_low = wt.getLowerBounds();// 取其下界
            if (t_low.length > 0)
                return getTypeClass(t_low[0]);
            Type[] t_up = wt.getUpperBounds(); // 没有下界?取其上界
            return getTypeClass(t_up[0]);// 最起码有Object作为上界
        }
        return clazz;
    }

    /**
     * @return 一个不可能的运行时异常
     */
    public static RuntimeException impossible() {
        return new RuntimeException("一个不可能的运行时异常！");
    }

    /**
     * 将字符串解析成 boolean 值，支持更多的字符串
     * <ul>
     * <li>1 | 0
     * <li>yes | no
     * <li>on | off
     * <li>true | false
     * </ul>
     *
     * @param s
     * @return 布尔值
     */
    public static boolean parseBoolean(String s) {
        if (null == s || s.length() == 0)
            return false;
        if (s.length() > 5)
            return true;
        if ("0".equals(s))
            return false;
        s = s.toLowerCase();
        return !"false".equals(s) && !"off".equals(s) && !"no".equals(s);
    }
}