package br.com.ultraworks.erp.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.ultraworks.erp.core.multitenancy.interceptor.TenantInterceptor;
import jakarta.servlet.MultipartConfigElement;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final TenantInterceptor tenantInterceptor;

    @Autowired
    public WebConfiguration(TenantInterceptor tenantInterceptor) {
        this.tenantInterceptor = tenantInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(tenantInterceptor);
    }

//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        // Configurações para tamanho máximo do arquivo e da requisição
//        factory.setMaxFileSize(DataSize.ofMegabytes(50)); // Tamanho máximo do arquivo: 50MB
//        factory.setMaxRequestSize(DataSize.ofMegabytes(50)); // Tamanho máximo da requisição: 50MB
//        // Configurações adicionais, se necessário
//        // factory.setLocation("caminho/para/salvar/os/arquivos");
//        return factory.createMultipartConfig();
//    }
}
