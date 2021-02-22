package com.example.basic.global.config;

import net.rakugakibox.util.YamlResourceBundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

    /*
     * 세션 지역 설정
     * default - KOREAN = 'ko'
     */
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.KOREAN);
        return sessionLocaleResolver;
    }

    /*
     * 지역 설정을 변경하는 interceptor
     * 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /*
     * interceptor 를 registry 에 등록
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /*
     * yml 파일을 참조하는 MessageSource 선언
     */
    @Bean
    public MessageSource messageSource(
            @Value("${spring.messages.basename}") String basename,
            @Value("${spring.messages.encoding}") String encoding
    ){
        YamlMessageSource yamlMessageSource = new YamlMessageSource();
        yamlMessageSource.setBasename(basename);
        yamlMessageSource.setDefaultEncoding(encoding);
        yamlMessageSource.setAlwaysUseMessageFormat(true);
        yamlMessageSource.setUseCodeAsDefaultMessage(true);
        yamlMessageSource.setFallbackToSystemLocale(true);
        return yamlMessageSource;
    }

    /*
     * locale 정보에 따라 다른 yaml 파일을 읽도록 처리
     */
    private static class YamlMessageSource extends ResourceBundleMessageSource{
        @Override
        protected ResourceBundle getResourceBundle(String basename, Locale locale) {
//            return super.getResourceBundle(basename, locale);
            return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
        }
    }
}
