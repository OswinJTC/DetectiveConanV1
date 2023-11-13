package com.tai.DetectiveConanV1.Vote;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


import java.util.List;

@Document(collection = "vote")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vote {

    @Id
    private ObjectId id;
    private String topicId;
    private String title;

    private List<Integer> points;

}
