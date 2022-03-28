package com.example.hatchway_assessment_mahdielharake.util;

import com.example.hatchway_assessment_mahdielharake.Model.Post;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.hatchway_assessment_mahdielharake.util.ConstMapper.*;

@Component
public class PostsMapper {

    public List<Post> sortAccordingToSortBy(List<Post> posts, String sortBy) {
        if (sortBy==null)
            sortBy=ID;
        switch (sortBy) {
            case READS:
                return posts.stream().sorted((Comparator.comparing(Post::getReads))).collect(Collectors.toList());
            case LIKES:
                return posts.stream().sorted((Comparator.comparing(Post::getLikes))).collect(Collectors.toList());
            case POPULARITY:
                return posts.stream().sorted((Comparator.comparing(Post::getPopularity))).collect(Collectors.toList());
            default:
                return posts.stream().sorted((Comparator.comparing(Post::getId))).collect(Collectors.toList());
        }
    }

    public List<Post> getListAccordingToDirection(List<Post> posts,String direction) {
        if(direction==null)
            direction=ASC;

        if (DESC.equals(direction)) {
            Collections.reverse(posts);
            return posts;
        }
        return posts;
    }
}