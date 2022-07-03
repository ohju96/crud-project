package com.example.hitest.service;

import com.example.hitest.dto.RedirectDto;
import com.example.hitest.dto.UpdateUserDto;
import com.example.hitest.dto.UserDto;
import com.example.hitest.persistance.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    // 유저 등록하기
    @Override
    public RedirectDto insertUser(UserDto userDto) throws Exception {
        log.info("### insertUser.Service start");
        log.info("### userDto 값이 잘 넘어왔나 체크 : {}", userDto);

        RedirectDto redirectDto = new RedirectDto();

        // 아이디 중복 체크 로직 : 존재하면 1, 아니면 0
        if (userMapper.checkUserId(userDto) == 1) {
            log.info("### 중복 체크에 걸림 : 중복된 아이디 : {}", userDto.getUserId());

            redirectDto.setMsg("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
            redirectDto.setUrl("/crud/create");

            log.info("### insertUser.Service end");
            return redirectDto;
        }

        // insert, update, delete : 정상 등록 1 , 업데이트가 없거나 삭제가 없을 때 0
        int ss = userMapper.insertUser(userDto);
        log.info("### ss : {}", ss);

        // 정상 등록 : 1
        if (ss == 1) {
            redirectDto.setMsg("정상 등록 되었습니다.");
            redirectDto.setUrl("/index");
        } else {
            redirectDto.setMsg("등록에 문제가 생겼습니다.");
            redirectDto.setUrl("/crud/create");
        }

        log.info("### insertUser.Service end");
        return redirectDto;
    }

    // 유저 아이디 불러오기
    @Override
    public List<UserDto> readUser() throws Exception {
        log.info("### readUser.Service start");

        List<UserDto> userDtoList = userMapper.readUser();
        log.info("### userDtoList : {}", userDtoList);

        log.info("### readUser.Service end");
        return userDtoList;
    }

    // 유저 아이디, 비밀번호 불러오기
    @Override
    public List<UserDto> readAllUser() throws Exception {
        log.info("### readAllUser.Service start");

        List<UserDto> userDtoList = userMapper.readAllUser();
        log.info("### userDtoList : {}", userDtoList);

        log.info("### readAllUser.Service end");
        return userDtoList;
    }

    // 유저 비밀번호 변경
    @Override
    public RedirectDto updateUser(UpdateUserDto updateUserDto) throws Exception {
        log.info("### updateUser.service start");

        // 리턴 타입 RedirectDto를 맞추기 위해 전역 변수로 인스턴스 생성성
       RedirectDto redirectDto = new RedirectDto();

       // 받아온 리스트 객체 updateUserDto를 List<UserDto>에 옮겨 담아준다.
        List<UserDto> userDtoList = updateUserDto.getUpdateUserList();
        if (userDtoList == null) {
            redirectDto.setMsg("등록된 유저가 없습니다. 유저 등록 후 이용해주세요.");
            redirectDto.setUrl("/crud/create");
            return redirectDto;
        }
        log.info("### userDtoList : {}", userDtoList);

        // 향상된 for문, userDtoList 수 만큼 userDto 값을 뺀다.
        for (UserDto userDto : userDtoList) {
            log.info("### for문 진입");
            log.info("### userDto : {}", userDto);

            // 정상 = 1, 그 외 비정상
            // userDto를 매퍼에 보내 수정된 값을 저장하고, 비정상인 경우 메시지와 함께 리턴해 버린다.
            if (userMapper.updateUser(userDto) != 1) {
                redirectDto.setMsg("아이디 [ " + userDto.getUserId() + " ] 의 비밀번호를 다시 입력해주세요.");
                redirectDto.setUrl("/crud/update");
                return redirectDto;
            }
            log.info("? : {}",userMapper.updateUser(userDto));
        }

        // 정상적으로 로직을 마무리하고(수정을 완료) 정상 완료 메시지를 셋팅한다.
        redirectDto.setMsg("정상적으로 비밀번호가 변경되었습니다.");
        redirectDto.setUrl("/index");

        log.info("### updateUser.service end");
        return redirectDto;
    }

    @Override
    public RedirectDto deleteUser(String userId) throws Exception {
        log.info("### deleteUser start");

        RedirectDto redirectDto = new RedirectDto();

        UserDto userDto = new UserDto(userId);

        // 존재하는 아이디인지 확인하는 로직 1 = 존재, 그 외 = 존재 하지 않거나 다수 존재 (오류)
        // 아이디가 존재하지 않거나 다수 존재하면
        if (userMapper.checkUserId(userDto) != 1) {
            log.info("### 존재하지 않는 아이디 : {}", userId);
            redirectDto.setMsg("존재하지 않는 아이디입니다. 아이디를 확인 후 이용해주세요.");
            redirectDto.setUrl("/crud/read");
            return redirectDto;
        }

        if (userMapper.deleteUser(userDto) != 1) {
            redirectDto.setMsg("삭제 실패 다시 시도해주세요.");
            redirectDto.setUrl("/crud/delete");
            return redirectDto;
        }

        redirectDto.setMsg("삭제 성공");
        redirectDto.setUrl("/index");

        log.info("### deleteUser end");

        return redirectDto;
    }
}
