package com.gk.springtest.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ClientCredentialsGrant;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by buddhikak on 1/24/16.
 */

@Configuration
@EnableSwagger2
@ComponentScan("com.gk.springtest")
public class Swagger2Config {

    @Value("${api.swagger.base.url}")
    private String baseURL;

    @Value("${docket.host.url}")
    private String docketHost;

    @Value("${oauth.token.url}")
    private String tokenURL;

    @Value("${api.title}")
    private String title;

    @Value("${api.description}")
    private String description;

    @Value("${api.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host(docketHost)
                .pathProvider(new CustomPathProvider())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(getSecuritySchemaList())
                .securityContexts(getSecurityContextList());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }

    public static final String authorizationScopeGlobal = "global";
    public static final String securitySchemaOAuth2ClientCredentials = "oauthschema";
    public static final String authorizationScopeGlobalDesc ="accessEverything";


    private OAuth securitySchema() {
        AuthorizationScope authorizationScope = new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobal);
        GrantType grantType = new ClientCredentialsGrant(tokenURL);
        List<AuthorizationScope> authorizationScopeList = new ArrayList<AuthorizationScope>();
        authorizationScopeList.add(authorizationScope);
        List<GrantType> grantTypeList = new ArrayList<GrantType>();
        grantTypeList.add(grantType);

        return new OAuth(securitySchemaOAuth2ClientCredentials, authorizationScopeList, grantTypeList);
    }

    private List<? extends SecurityScheme> getSecuritySchemaList(){
        List<SecurityScheme> securitySchemeList = new ArrayList<SecurityScheme>();
        securitySchemeList.add(securitySchema());
        return securitySchemeList;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityContext> getSecurityContextList(){
        List<SecurityContext> securityContextList = new ArrayList<SecurityContext>();
        securityContextList.add(securityContext());
        return securityContextList;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobalDesc);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferenceList = new ArrayList<SecurityReference>();
        securityReferenceList.add(new SecurityReference(securitySchemaOAuth2ClientCredentials, authorizationScopes));

        return securityReferenceList;
    }

    private class CustomPathProvider extends AbstractPathProvider {

        @Override
        protected String applicationPath() {
            return baseURL;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }
    }

}
