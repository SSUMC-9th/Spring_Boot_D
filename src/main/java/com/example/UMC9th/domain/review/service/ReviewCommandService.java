package com.example.UMC9th.domain.review.service;

import com.example.UMC9th.domain.review.dto.ReviewReqDTO;
import com.example.UMC9th.domain.review.dto.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.CreateDTO createReview(
            ReviewReqDTO.CreateDTO dto
    );
}
