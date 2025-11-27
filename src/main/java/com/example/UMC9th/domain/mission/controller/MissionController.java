package com.example.UMC9th.domain.mission.controller;

import com.example.UMC9th.domain.mission.dto.MissionReqDTO;
import com.example.UMC9th.domain.mission.dto.MissionResDTO;
import com.example.UMC9th.domain.mission.service.MissionCommandService;
import com.example.UMC9th.global.apiPayload.ApiResponse;
import com.example.UMC9th.domain.mission.exception.code.MissionSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    //물어보기

}
