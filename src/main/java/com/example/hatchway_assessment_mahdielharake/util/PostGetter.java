package com.example.hatchway_assessment_mahdielharake.util;

import com.example.hatchway_assessment_mahdielharake.CallResponse.PostList;
import com.example.hatchway_assessment_mahdielharake.Model.Post;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PostGetter {

    @Autowired
    RestTemplate restTemplate;

    @Value("${external.api}")
    String url;


    private PostList makeExternalApiCall(String url) {
        return restTemplate.getForObject(url, PostList.class);
    }

    public  List<Post> getDataFromTheExternalAPI(List<String> tags) {
        Map<Integer,Post> map=new HashMap<>();
        for (String tag:tags) {
            PostList blog=makeExternalApiCall(url+tag);
            List<Post> tempPost = null;
            if(blog!=null) {
                tempPost=blog.getPosts();
            }
            else
                throw new NullPointerException();
            assert tempPost != null;
            tempPost.forEach(p->
                    map.put(p.getId(),p));
        }

        List<Post> posts=new ArrayList<>();
        map.forEach((k,v)->posts.add(v));
        return posts;
    }
}