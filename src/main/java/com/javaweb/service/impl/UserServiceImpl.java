package com.javaweb.service.impl;

import com.javaweb.model.UserDTO;
import com.javaweb.converter.UserDTOConverter;
import com.javaweb.model.RoleDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.repository.entity.RoleEntity;
import com.javaweb.repository.RoleRepository;
import com.javaweb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserDTO> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
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
    public UserDTO findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        if (userEntity == null) return null;
        UserDTO result = userDTOConverter.toUserDTO(userEntity);
        return result;
    }

    @Override
    public TourResponse createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userDTOConverter.toUserEntity(userDTO);
        // Xử lý roles nếu có
        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            List<Long> roleIds = userDTO.getRoles().stream()
                    .map(roleDTO -> roleDTO.getId())
                    .toList();
            List<RoleEntity> roles = roleRepository.findAllById(roleIds);
            userEntity.setRoles(roles);
        }
        userRepository.save(userEntity);
        return new TourResponse("success", "Thêm thành công user");
    }

    @Override
    public TourResponse updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId()).get();
        if (userEntity == null) return null;
        // Cập nhật các trường
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFullname(userDTO.getFullname());
        userEntity.setPassword(userDTO.getPassword());
        // Xử lý roles nếu có
        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            List<Long> roleIds = userDTO.getRoles().stream()
                    .map(roleDTO -> roleDTO.getId())
                    .toList();
            List<RoleEntity> roles = roleRepository.findAllById(roleIds);
            userEntity.setRoles(roles);
        } else {
            userEntity.setRoles(null);
        }
        userRepository.save(userEntity);
        return new TourResponse("success", "Cập nhật thành công user");
    }

    @Override
    public TourResponse deleteUser(List<Long> ids) {
        userRepository.deleteUser(ids);
        return new TourResponse("success", "Xóa thành công user");
    }
} 