package com.tai.DetectiveConanV1.Vote;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service

public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public List<Vote> allVotes(){
        return voteRepository.findAll();
    }

    public Optional<Vote> singleVote(String topicId){

        return voteRepository.findBytopicId(topicId);

    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public Vote updateVote(int[] beVotedCandidateIndexes, int[] correspondingPoints, String topicId) {
        Query query = new Query(Criteria.where("topicId").is(topicId));
        Vote vote = mongoTemplate.findOne(query, Vote.class);


        if (vote != null) {

            for (int i = 0; i < beVotedCandidateIndexes.length; i++) {

                int index = beVotedCandidateIndexes[i];

                List<Integer> currentPoints = vote.getPoints();

                if (index >= 0 && index < currentPoints.size()) {
                    int points = correspondingPoints[i];

                    currentPoints.set(index, currentPoints.get(index) + points);
                    vote.setPoints(currentPoints);
                }
            }
            mongoTemplate.save(vote);
        }

        return vote;
    }
}
