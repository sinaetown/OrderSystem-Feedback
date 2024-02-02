package com.encore.ordering.member.controller;

import com.encore.ordering.common.CommonResponse;
import com.encore.ordering.member.domain.Member;
import com.encore.ordering.member.dto.LoginReqDto;
import com.encore.ordering.member.dto.MemberCreateReqDto;
import com.encore.ordering.member.dto.MemberResponseDto;
import com.encore.ordering.member.service.MemberService;
import com.encore.ordering.securities.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/members")
    public List<MemberResponseDto> members() {
        return memberService.findAll();
    }

//    @GetMapping("/member/{id}/orders")

//    @GetMapping("/member/myorders")


    @GetMapping("/member/myInfo") //Authentication 객체에서 바로 회원 관련 정보를 빼내겠다는
    public MemberResponseDto findMyInfo() {
        return memberService.findMyInfo();
    }

    @PostMapping("/member/create")
    public ResponseEntity<CommonResponse> memberCreate(@Valid @RequestBody MemberCreateReqDto memberCreateReqDto) {
        Member member = memberService.create(memberCreateReqDto);
        return new ResponseEntity<>(new CommonResponse(HttpStatus.CREATED,
                "member successfully created!",
                member.getId()), HttpStatus.CREATED);
    }

    @PostMapping("/doLogin") //token을 return 해야함!
    public ResponseEntity<CommonResponse> memberLogin(@Valid @RequestBody LoginReqDto loginReqDto) {
        Member member = memberService.login(loginReqDto);
//        토큰 생성 로직
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
        Map<String, Object> member_info = new HashMap<>();
        member_info.put("id", member.getId());
        member_info.put("token", jwtToken);
        return new ResponseEntity<>(new CommonResponse(HttpStatus.OK,
                "login success!",
                member_info), HttpStatus.OK);
    }


}
