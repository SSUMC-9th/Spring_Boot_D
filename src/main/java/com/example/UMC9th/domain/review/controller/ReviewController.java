package com.example.UMC9th.domain.review.controller;
import com.example.UMC9th.domain.review.dto.ReviewReqDTO;
import com.example.UMC9th.domain.review.dto.ReviewResDTO;
import com.example.UMC9th.domain.review.service.ReviewCommandService;
import com.example.UMC9th.global.apiPayload.ApiResponse;
import com.example.UMC9th.domain.review.exception.code.ReviewSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @RequestBody ReviewReqDTO.CreateDTO request
    ) {
        ReviewResDTO.CreateDTO result = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, result);
    }

}
