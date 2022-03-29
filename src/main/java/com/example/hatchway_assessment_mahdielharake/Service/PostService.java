package com.example.hatchway_assessment_mahdielharake.Service;
import com.example.hatchway_assessment_mahdielharake.Model.Post;
import com.example.hatchway_assessment_mahdielharake.util.ConstMapper;
import com.example.hatchway_assessment_mahdielharake.util.ErrorMessage;
import com.example.hatchway_assessment_mahdielharake.util.PostGetter;
import com.example.hatchway_assessment_mahdielharake.util.PostsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class PostService {

    @Autowired
    PostGetter postGetter;

    @Autowired
    PostsMapper postsMapper;

    public List<Post> getPost(String tags, String sortBy, String direction) throws Exception, ExecutionException, InterruptedException {
        dataValidator(tags,sortBy,direction);
        List<String> listOfTags= Arrays.asList(tags.split(","));
        listDataValidator(listOfTags);
        log.info("Data Successfully validated");
        List<Post> posts= postGetter.getDataFromTheExternalAPI(listOfTags);
        log.info("Data Retrieve from the api");
        posts= postsMapper.sortAccordingToSortBy(posts,sortBy);
        posts= postsMapper.getListAccordingToDirection(posts,direction);
        log.info("Data set according to the sorting method and direction method");
        return  posts;
    }

    private static void dataValidator(String tags,String sortBy,String direction) throws Exception {
        if(tags==null || tags=="" || tags==" ") {
            log.error("tag is null");
            throw new Exception(ErrorMessage.TAG_PARAMETER_REQUIRED);
        }

        if(!ConstMapper.canSortBy.contains(sortBy)){
            log.error(ErrorMessage.SORT_BY_PARAMETER_INVALID);
            throw new Exception(ErrorMessage.SORT_BY_PARAMETER_INVALID);
        }
        if(!ConstMapper.hasDirection.contains(direction)) {
            log.error(ErrorMessage.DIRECTION_PARAMETER_INVALID);
            throw new Exception(ErrorMessage.DIRECTION_PARAMETER_INVALID);
        }

    }
    private static void listDataValidator(List<String> listOfTags ) throws Exception {
        if(listOfTags.size()==1 && listOfTags.get(0).equals("")) {
            log.error(ErrorMessage.EMPTY_TAG_STRING_NOT_ALLOWED);
            throw new Exception(ErrorMessage.EMPTY_TAG_STRING_NOT_ALLOWED);
        }
    }
}