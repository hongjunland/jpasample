package com.example.jpasample;

import com.example.dto.CommentReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class RecordTest {
    private RestTemplate restTemplate = new RestTemplate();
    @Test
    public void createTest(){
        String apiUrl = "http://localhost:8788/api/v1/posts";
        String requestJson = """
                {
                     "title": "title2",
                     "content": "content2"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        int threadPoolSize = 10; // 병렬 요청을 위한 스레드 수 (적절한 값 설정 필요)
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        Instant start = Instant.now();
        for (int i = 0 ; i < 1; i++) {
            restTemplate.postForEntity(apiUrl, request, String.class);
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Duration: " + timeElapsed.toMillis() + " milliseconds");
    }
    @Test
    public void getListTest(){
        String apiUrl = "http://localhost:8788/api/v1/posts/count";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<?> response = restTemplate.getForEntity(apiUrl, Long.class);
        System.out.println(response.getBody());

    }
    @Test
    public void getPostTest(){
        String apiUrl = "http://localhost:8788/api/v1/posts/1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<?> response = restTemplate.getForEntity(apiUrl, Long.class);
        System.out.println(response.getBody());
        List<CommentReadResponse> items = ((ArrayList<CommentReadResponse>) response.getBody());
        for(Object item: items){
            System.out.println(item);
        }
    }
    @Test
    public void deleteAllTest(){
        String apiUrl = "http://localhost:8788/api/v1/posts";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.delete(apiUrl);
    }
    @Test
    public void createComment(){
        String apiUrl = "http://localhost:8788/api/v1/posts/2/comments";
        String requestJson = """
                {
                     "content": "content2"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        Instant start = Instant.now();
        for (int i = 0 ; i < 5; i++) {
            restTemplate.postForEntity(apiUrl, request, String.class);
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Duration: " + timeElapsed.toMillis() + " milliseconds");
    }
    @Test
    public void getCommentList(){
        String apiUrl = "http://localhost:8788/api/v1/posts/1/comments";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<?> response = restTemplate.getForEntity(apiUrl, List.class);
//        System.out.println(((List)response.getBody()).get(0));
        List<CommentReadResponse> items = ((ArrayList<CommentReadResponse>) response.getBody());
        for(Object item: items){
            System.out.println(item);
        }
    }
}
