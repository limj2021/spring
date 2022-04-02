package com.example.spring_client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
//国际化
@Configuration
public class I18nLocaleResolver implements LocaleResolver {


    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求中的语言参数
        String language = request.getParameter("l");
        //如果没有获取到，就使用默认的
        Locale localedata = Locale.getDefault();
        //判断请求带有国际化参数
        if(StringUtils.hasText(language)){
            //分割国家和地区
            String[] splitLanguage=language.split("_");
            //取到自己的国家，地区
            localedata=new Locale(splitLanguage[0],splitLanguage[1]);
        }
        return localedata;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
