package com.ivan.nutriapp.infrastructure.spring;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public class RestTemplateWithRetry implements RestOperations {

    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    public RestTemplateWithRetry(RestTemplate restTemplate, RetryTemplate retryTemplate) {

        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.getForObject(url, responseType, uriVariables));
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.getForObject(url, responseType, uriVariables));
    }

    @Override
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.getForObject(url, responseType));
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.getForEntity(url, responseType, uriVariables));
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.getForEntity(url, responseType, uriVariables));
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.getForEntity(url, responseType));
    }

    @Override
    public HttpHeaders headForHeaders(String url, Object... uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.headForHeaders(url, uriVariables));
    }

    @Override
    public HttpHeaders headForHeaders(String url, Map<String, ?> uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.headForHeaders(url, uriVariables));
    }

    @Override
    public HttpHeaders headForHeaders(URI url) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.headForHeaders(url));
    }

    @Override
    public URI postForLocation(String url, Object request, Object... uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.postForLocation(url, request, uriVariables));
    }

    @Override
    public URI postForLocation(String url, Object request, Map<String, ?> uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.postForLocation(url, request, uriVariables));
    }

    @Override
    public URI postForLocation(URI url, Object request) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.postForLocation(url, request));
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.postForObject(url, request, responseType, uriVariables));
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.postForObject(url, request, responseType, uriVariables));
    }

    @Override
    public <T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.postForObject(url, request, responseType));
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.postForEntity(url, request, responseType, uriVariables));
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.postForEntity(url, request, responseType, uriVariables));
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(URI url, Object request, Class<T> responseType) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.postForEntity(url, request, responseType));
    }

    @Override
    public void put(String url, Object request, Object... uriVariables) throws RestClientException {
        retryTemplate.execute( retryContext -> { restTemplate.put(url, request, uriVariables); return null;});
    }

    @Override
    public void put(String url, Object request, Map<String, ?> uriVariables) throws RestClientException {
        retryTemplate.execute( retryContext -> { restTemplate.put(url, request, uriVariables); return null;});
    }

    @Override
    public void put(URI url, Object request) throws RestClientException {
        retryTemplate.execute( retryContext -> {restTemplate.put(url, request);return null;});
    }

    @Override
    public <T> T patchForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.patchForObject(url, request, responseType, uriVariables));
    }

    @Override
    public <T> T patchForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.patchForObject(url, request, responseType, uriVariables));
    }

    @Override
    public <T> T patchForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.patchForObject(url, request, responseType));
    }

    @Override
    public void delete(String url, Object... uriVariables) throws RestClientException {
        retryTemplate.execute( retryContext -> { restTemplate.delete(url, uriVariables); return null;});
    }

    @Override
    public void delete(String url, Map<String, ?> uriVariables) throws RestClientException {
        retryTemplate.execute( retryContext -> { restTemplate.delete(url, uriVariables); return null;});
    }

    @Override
    public void delete(URI url) throws RestClientException {
        retryTemplate.execute( retryContext -> { restTemplate.delete(url); return null; });
    }

    @Override
    public Set<HttpMethod> optionsForAllow(String url, Object... uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.optionsForAllow(url, uriVariables));
    }

    @Override
    public Set<HttpMethod> optionsForAllow(String url, Map<String, ?> uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.optionsForAllow(url, uriVariables));
    }

    @Override
    public Set<HttpMethod> optionsForAllow(URI url) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.optionsForAllow(url));
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) throws RestClientException {

        return retryTemplate.execute( retryContext -> restTemplate.exchange(url, method, requestEntity, responseType, uriVariables));
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.exchange(url, method, requestEntity, responseType, uriVariables));
    }

    @Override
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.exchange(url, method, requestEntity, responseType));
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.exchange(url, method, requestEntity, responseType, uriVariables));
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate. exchange(url, method, requestEntity, responseType, uriVariables));
    }

    @Override
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate. exchange(url, method, requestEntity, responseType));
    }

    @Override
    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.exchange(requestEntity, responseType));
    }

    @Override
    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.exchange(requestEntity, responseType));
    }

    @Override
    public <T> T execute(String uriTemplate, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Object... uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.execute(uriTemplate, method, requestCallback, responseExtractor, uriVariables));
    }

    @Override
    public <T> T execute(String uriTemplate, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.execute(uriTemplate, method, requestCallback, responseExtractor, uriVariables));
    }

    @Override
    public <T> T execute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        return retryTemplate.execute( retryContext -> restTemplate.execute(url, method, requestCallback, responseExtractor));
    }
}
