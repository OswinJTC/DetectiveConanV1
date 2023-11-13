package com.tai.DetectiveConanV1.Vote;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/votes")

public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes(){
        return new ResponseEntity<List<Vote>>(voteService.allVotes(), HttpStatus.OK);
    }

    @GetMapping("/{topicId}")
    public ResponseEntity<Optional<Vote>> getSingleVote(@PathVariable String topicId){

        return new ResponseEntity<Optional<Vote>>(voteService.singleVote(topicId), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Vote> updateVote(@RequestBody Map<String, Object> payload) {
        List<Integer> beVotedCandidateIndexes = (List<Integer>) payload.get("beVotedCandidateIndexes");
        List<Integer> correspondingPoints = (List<Integer>) payload.get("correspondingPoints");
        String topicId = (String) payload.get("topicId");

        int[] beVotedCandidateIndexesArray = beVotedCandidateIndexes.stream().mapToInt(Integer::intValue).toArray();
        int[] correspondingPointsArray = correspondingPoints.stream().mapToInt(Integer::intValue).toArray();

        return new ResponseEntity<>(voteService.updateVote(beVotedCandidateIndexesArray, correspondingPointsArray, topicId), HttpStatus.OK);
    }


}


