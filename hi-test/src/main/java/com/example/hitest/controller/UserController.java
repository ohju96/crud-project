package com.example.hitest.controller;

import com.example.hitest.dto.RedirectDto;
import com.example.hitest.dto.UserDto;
import com.example.hitest.dto.UpdateUserDto;
import com.example.hitest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/crud")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // create(insert) : 유저 등록하기 페이지
    @GetMapping("/create")
    public String insertUserPage() {
        return "/crud/create";
    }

    // create(insert) : 유저 등록하기 로직
    @PostMapping("/create")
    public String insertUser(HttpServletRequest request, Model model) throws Exception {
        log.info("### insertUser.Controller start");

        UserDto userDto = new UserDto();
        userDto.setUserId(request.getParameter("userId"));
        userDto.setUserPw(request.getParameter("userPw"));
        log.info("### userDto : {}", userDto);

        RedirectDto redirectDto = userService.insertUser(userDto);

        model.addAttribute("msg", redirectDto.getMsg());
        model.addAttribute("url", redirectDto.getUrl());

        log.info("### insertUser.Controller end");
        return "/redirect";
    }

    // read(select) : 유저 조회하기 페이지
    @GetMapping("/read")
    public String readUserPage(Model model){
        log.info("### readUserPage Start");

        try {
            List<UserDto> userDtoList = userService.readUser();
            log.info("userDtoList : {}", userDtoList);
            log.info("userDtoList.index : {}", userDtoList.get(0));

            model.addAttribute("userDtoList", userDtoList);
            return "/crud/read";

        } catch (IndexOutOfBoundsException e) {
            model.addAttribute("msg", "등록된 유저가 없습니다. ");
            model.addAttribute("url", "/crud/create");
            return "/redirect";

        } catch (Exception e) {
            model.addAttribute("msg", "오류가 발생했습니다. 다시 시도해주세요");
            model.addAttribute("url", "/crud/read");
            return "/redirect";
        }
    }

    // update(update) : 유저 수정하기 페이지
    @GetMapping("/update")
    public String updateUserPage(Model model) throws Exception {
        log.info("### updateUserPage Start");

        // 모둔 유저 정보 조회
        List<UserDto> userDtoList = userService.readAllUser();
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setUpdateUserList(userDtoList);

        model.addAttribute("updateUserDto", updateUserDto);

        log.info("### updateUserPage end");
        return "/crud/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute UpdateUserDto userDtoList, Model model) throws Exception {
        log.info("### updateUser Start");
        log.info("### userDtoList : {}", userDtoList);
        log.info("### userDtoList.index : {}", userDtoList.getUpdateUserList().get(0).getUserId());
        log.info("### userDtoList.index : {}", userDtoList.getUpdateUserList().get(0).getUserPw());

        RedirectDto redirectDto = userService.updateUser(userDtoList);
        log.info("### redirectDto : {}", redirectDto);

        model.addAttribute("msg", redirectDto.getMsg());
        model.addAttribute("url", redirectDto.getUrl());

        log.info("### updateUser End");
        return "/redirect";
    }

    // delete(delete) : 유저 삭제하기 페이지
    @GetMapping("/delete")
    public String deleteUserPage() {
        log.info("### deleteUserPage pass");
        return "/crud/delete";
    }

    @PostMapping("/delete")
    public String deleteUser(HttpServletRequest request, Model model) throws Exception {
        log.info("### deleteUser start");

        String userId = request.getParameter("userId");
        log.info("### userDto");

        RedirectDto redirectDto = userService.deleteUser(userId);
        log.info("### redirfectDto : {}", redirectDto);

        model.addAttribute("msg", redirectDto.getMsg());
        model.addAttribute("url", redirectDto.getUrl());

        log.info("### deleteUser end");
        return "/redirect";
    }

}
