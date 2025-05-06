package com.opom.jobfinder.feature.company.review.controller;

import com.opom.jobfinder.feature.company.review.dtos.ReviewByCompanyDTO;
import com.opom.jobfinder.feature.company.review.dtos.ReviewDTO;
import com.opom.jobfinder.feature.company.review.mapper.ReviewManager;
import com.opom.jobfinder.feature.company.review.service.ReviewService;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/recruiters")
public class ReviewController {

    // CONSTANT VALUES
    private final ReviewService reviewService;
    private final ReviewManager reviewManager;

    @PostMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> addReview(@RequestBody ReviewDTO reviewDTO, @PathVariable String id) {
       Review review = reviewManager.toReview(reviewDTO);
       Review response = reviewService.save(review,id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> getReviewsByCompany(@PathVariable String id) {
        List<ReviewByCompanyDTO> response = reviewService.getByCompany(id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @PutMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<BaseResponse> updateReview(@RequestBody ReviewDTO reviewDTO,@PathVariable String companyId,@PathVariable String reviewId) {
        Review review = reviewManager.toReview(reviewDTO);
        Review response = reviewService.update(review, companyId,reviewId);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @DeleteMapping("/reviews")
    public ResponseEntity<BaseResponse> deleteReview(@RequestParam("id") String id) {
        reviewService.delete(id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, "Delete Review Successfully!", Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @GetMapping("/{id}/reviews/avg")
    public ResponseEntity<BaseResponse> getAvgReviewOfCompany(@PathVariable String id) {
        Double response = reviewService.getAvgFromCompany(id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));
    }
}
