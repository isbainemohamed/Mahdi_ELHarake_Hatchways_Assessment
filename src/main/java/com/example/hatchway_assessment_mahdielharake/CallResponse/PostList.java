package com.example.hatchway_assessment_mahdielharake.CallResponse;
import com.example.hatchway_assessment_mahdielharake.Model.Post;
import lombok.*;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostList {
    List<Post> posts;
}