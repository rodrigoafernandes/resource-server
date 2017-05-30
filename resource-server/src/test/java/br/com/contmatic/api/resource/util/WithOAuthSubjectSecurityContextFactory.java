package br.com.contmatic.api.resource.util;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import br.com.contmatic.api.resource.test.WithOAuthSubject;

public class WithOAuthSubjectSecurityContextFactory implements WithSecurityContextFactory<WithOAuthSubject> {

	private DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
	
    @Override
    public SecurityContext createSecurityContext(WithOAuthSubject withOAuthSubject) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Map<String, Object> remoteToken = new HashMap<>();
        remoteToken.put("iss", "http://localhost:9999/authorization/oauth/token");
        remoteToken.put("aud", "oauth2-resource");
        remoteToken.put("exp", OffsetDateTime.now().plusDays(1L).toEpochSecond() + "");
        remoteToken.put("nbf", OffsetDateTime.now().plusDays(1L).toEpochSecond() + "");
        remoteToken.put("client_id", "my-client-id");
        remoteToken.put("scope", Arrays.asList(withOAuthSubject.scopes()));
        remoteToken.put("sub", withOAuthSubject.subjectId());
        remoteToken.put("auth_time", OffsetDateTime.now().toEpochSecond() + "");
        remoteToken.put("idp", "idsrv");
        remoteToken.put("amr", "password");
        remoteToken.put("user_name", withOAuthSubject.userName());
        remoteToken.put("authorities", Arrays.asList(withOAuthSubject.authorities()));

        OAuth2Authentication authentication = defaultAccessTokenConverter.extractAuthentication(remoteToken);
        context.setAuthentication(authentication);
        return context;
    }

}
