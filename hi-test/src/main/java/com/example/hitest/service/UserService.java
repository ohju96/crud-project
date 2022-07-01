package com.example.hitest.service;

import com.example.hitest.dto.RedirectDto;
import com.example.hitest.dto.UpdateUserDto;
import com.example.hitest.dto.UserDto;

import java.util.List;

public interface UserService {

    // 유저 등록하기
    RedirectDto insertUser(UserDto userDto) throws Exception;

    // 유저 아이디 불러오기
    List<UserDto> readUser() throws Exception;

    // 유저 아이디, 비밀번호 불러오기
    List<UserDto> readAllUser() throws Exception;

    // 유저 비밀번호 변경
    RedirectDto updateUser(UpdateUserDto updateUserDto) throws Exception;

    RedirectDto deleteUser(String userId) throws Exception;
}
