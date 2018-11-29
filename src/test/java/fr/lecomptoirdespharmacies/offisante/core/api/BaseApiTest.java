package fr.lecomptoirdespharmacies.offisante.core.api;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.core.manager.TokenManager;
import fr.lecomptoirdespharmacies.offisante.core.util.TimeUtil;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;
import fr.lecomptoirdespharmacies.offisante.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateUri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BaseApiTest {

    private BaseApi baseApi;
    private Body body;
    private OffisanteApi api;
    private TokenManager tokenManager;

    private TimeUtil timeUtil;
    private RequestBody requestBody;
    private Class responseCls;
    private final Uri uri = new CreateUri().build();

    @BeforeEach
    void setUp() {
        body = mock(Body.class);

        baseApi = mock(BaseApi.class);

        timeUtil = mock(TimeUtil.class);

        api = mock(OffisanteApi.class);

        tokenManager = mock(TokenManager.class);

        when(api.getTokenManager()).thenReturn(tokenManager);

        doNothing().when(tokenManager).generateToken();

        doNothing().when(timeUtil).resetMultiplier();

        doNothing().when(timeUtil).sleep();

        when(baseApi.executePost(eq(uri), eq(requestBody), eq(responseCls), any(Integer.class)))
                .thenCallRealMethod();

        when(baseApi.manageError(eq(body),eq(uri), eq(requestBody), eq(responseCls), any(Integer.class)))
                .thenCallRealMethod();

        when(baseApi.securePost(eq(api), eq(uri), eq(requestBody), eq(responseCls)))
                .thenReturn(body);

        when(baseApi.getTimeUtil()).thenReturn(timeUtil);

        when(baseApi.getApi()).thenReturn(api);

    }

    @Test
    void response_is_return_if_no_error_code() {

        when(body.getCode()).thenReturn(null);

        final Body response = baseApi.executePost(uri, requestBody, responseCls, START_RETRY);

        verify(baseApi, times(0)).manageError(
                eq(body), eq(uri), eq(requestBody), eq(responseCls), any(Integer.class)
        );

        assertEquals(body, response);
    }

    @Test
    void do_retry_when_token_rate_limit_reach_error_occur() {
        when(body.getCode()).thenReturn(TOKEN_RATE_LIMIT_REACHED);

        final Body response = baseApi.executePost(uri, requestBody, responseCls, START_RETRY);

        verify(baseApi, times(MAX_RETRY)).manageError(
                eq(body), eq(uri), eq(requestBody), eq(responseCls), any(Integer.class)
        );

        verify(timeUtil,times(MAX_RETRY)).sleep();

        assertEquals(body, response);
    }

    @Test
    void do_retry_when_unknown_token_error_occur() {
        when(body.getCode()).thenReturn(UNKNOWN_TOKEN);

        final Body response = baseApi.executePost(uri, requestBody, responseCls, START_RETRY);

        verify(baseApi, times(MAX_RETRY)).manageError(
                eq(body), eq(uri), eq(requestBody), eq(responseCls), any(Integer.class)
        );

        verify(tokenManager, times(MAX_RETRY)).generateToken();

        assertEquals(body, response);
    }

    @Test
    void do_retry_when_malformed_token_error_occur() {
        when(body.getCode()).thenReturn(MALFORMED_TOKEN);

        final Body response = baseApi.executePost(uri, requestBody, responseCls, START_RETRY);

        verify(baseApi, times(MAX_RETRY)).manageError(
                eq(body), eq(uri), eq(requestBody), eq(responseCls), any(Integer.class)
        );

        verify(tokenManager, times(MAX_RETRY)).generateToken();

        assertEquals(body, response);
    }
}