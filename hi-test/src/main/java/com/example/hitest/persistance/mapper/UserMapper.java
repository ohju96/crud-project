package com.example.hitest.persistance.mapper;

import com.example.hitest.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    // 유저 등록하기 : create 로직에 사용
    int insertUser(UserDto userDto) throws Exception;

    // 유저 아이디 등록되어 있나 체크하기 : create 로직에 사용
    int checkUserId(UserDto userDto) throws Exception;

    // 모든 유저 ID 조회하기 : read 로직에 사용
    List<UserDto> readUser() throws Exception;

    // User ID , PW 조회하기 : update 로직에 사용
    List<UserDto> readAllUser() throws Exception;

    // User 삭제 : 아이디 기준
    int deleteUser(UserDto userDto) throws Exception;

    // User 수정 : 아이디 기준 비밀번호 변경
    int updateUser(UserDto userDto) throws Exception;

    // 존재하는 아이디 인지 체크 : delete 로직에 사용
}