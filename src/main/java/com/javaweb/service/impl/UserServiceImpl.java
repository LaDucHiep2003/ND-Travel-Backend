package com.javaweb.service.impl;

import com.javaweb.builder.UserSearchBuilder;
import com.javaweb.converter.UserSearchBuilderConverter;
import com.javaweb.exception.AppException;
import com.javaweb.exception.ErrorCode;
import com.javaweb.converter.UserDTOConverter;
import com.javaweb.model.RoleDTO;
import com.javaweb.model.request.UserRequest;
import com.javaweb.model.response.UserResponse;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.repository.RoleRepository;
import com.javaweb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSearchBuilderConverter userSearchBuilderConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserResponse> findAll(Map<String, Object> params) {
        UserSearchBuilder userSearchBuilder = userSearchBuilderConverter.toUserSearchBuilder(params);
        List<UserEntity> userEntities = userRepository.findAll(userSearchBuilder);
        List<UserResponse> result = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserResponse userDTO = modelMapper.map(userEntity, UserResponse.class);
            // Chuyển roles sang RoleDTO
            if (userEntity.getRoles() != null) {
                List<RoleDTO> roles = userEntity.getRoles().stream()
                        .map(role -> modelMapper.map(role, RoleDTO.class))
                        .collect(Collectors.toList());
                userDTO.setRoles(roles);
            }
            result.add(userDTO);
        }
        return result;
    }

    @Override
    public UserResponse findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        UserResponse result = userDTOConverter.toUserDTO(userEntity);
        return result;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if(userRepository.existsByUsername(userRequest.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        UserEntity user = userDTOConverter.toUserEntity(userRequest);
        if(userRequest.getRoleId() != null){
            var roleId = roleRepository.findAllById(userRequest.getRoleId());
            user.setRoles(new ArrayList<>(roleId));
        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userDTOConverter.toUserDTO(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(userRequest.getId()).get();
        // Cập nhật các trường
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setFullname(userRequest.getFullname());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        // Xử lý roles
        if (userEntity.getRoles() != null) {
            userEntity.getRoles().clear(); // Xóa hết roles cũ trước khi set roles mới
        }
        if (userRequest.getRoleId() != null && !userRequest.getRoleId().isEmpty()) {
            var roleId = roleRepository.findAllById(userRequest.getRoleId());
            userEntity.setRoles(new ArrayList<>(roleId));
        } else {
            userEntity.setRoles(null);
        }
        UserEntity result = userRepository.save(userEntity);
        return userDTOConverter.toUserDTO(result);
    }

    @Override
    @Transactional
    public void deleteUser(List<Long> ids) {
        userRepository.deleteUser(ids);
    }
} 