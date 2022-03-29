package com.example.hatchway_assessment_mahdielharake.Controller;


import com.example.hatchway_assessment_mahdielharake.CallResponse.CallError;
import com.example.hatchway_assessment_mahdielharake.CallResponse.CallSuccess;
import com.example.hatchway_assessment_mahdielharake.CallResponse.PostList;
import com.example.hatchway_assessment_mahdielharake.Model.Post;
import com.example.hatchway_assessment_mahdielharake.Service.PostService;
import com.example.hatchway_assessment_mahdielharake.util.ConstMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@RestController
@Slf4j
@RequestMapping("/api")
@CacheConfig(cacheNames = ConstMapper.CACHE_NAME)
public class Controller {


    @Autowired
    PostService service;

    @GetMapping("/ping")
    @ResponseBody
    public ResponseEntity<CallSuccess> getPingResponse(){
        return new ResponseEntity<>(new CallSuccess(true), HttpStatus.OK);
    }

    @GetMapping("/posts")
    @Cacheable
    @Async
    public ResponseEntity getPosts(
            //put tags required field false so we can handle custom exception
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction)
    {
        log.info("Get Post Method Call");
        PostList blog;
        try {
            List<Post> posts= service.getPost(tags,sortBy,direction);
            blog=new PostList(posts);
            System.out.println(blog);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tag parameter is required");
        }
        return ResponseEntity.status(HttpStatus.OK).body(blog);
    }




}