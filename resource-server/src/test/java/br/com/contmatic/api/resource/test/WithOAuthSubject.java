package br.com.contmatic.api.resource.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.test.context.support.WithSecurityContext;

import br.com.contmatic.api.resource.util.WithOAuthSubjectSecurityContextFactory;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithOAuthSubjectSecurityContextFactory.class)
public @interface WithOAuthSubject {
	String[] scopes() default {"myapi:write", "myapi:read"};

    String subjectId() default "a1de7cc9-1b3a-4ecd-96fa-dab6059ccf6f";
    
    String userName() default "test";
    
    String[] authorities() default {"ROLE_ADMIN"};
    
}
