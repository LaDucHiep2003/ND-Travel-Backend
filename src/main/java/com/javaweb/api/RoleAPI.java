package com.javaweb.api;

import com.javaweb.model.ApiResponse;
import com.javaweb.model.PermissionDTO;
import com.javaweb.model.request.RoleRequest;
import com.javaweb.model.response.RoleResponse;
import com.javaweb.service.RoleService;
import com.javaweb.service.impl.PermissionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class RoleAPI {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionsService permissionsService;

    @GetMapping("/roles")
    public ApiResponse<List<RoleResponse>> findAll(@RequestParam Map<String, Object> params){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.findALL(params))
                .build();
    }

    @GetMapping("/roles/permissions")
    public ApiResponse<List<PermissionDTO>> findAllPermissions(){
        return ApiResponse.<List<PermissionDTO>>builder()
                .result(permissionsService.findAll())
                .build();
    }

    @GetMapping("/roles/{id}")
    public RoleResponse findById(@PathVariable Long id){
        RoleResponse result = roleService.findById(id);
        return result;
    }

    @PostMapping("/roles")
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleDTO){
        ApiResponse<RoleResponse> apiResponse = new ApiResponse<>();
        RoleResponse msg = roleService.createRole(roleDTO);
        apiResponse.setResult(msg);
        return apiResponse;
    }

    @PutMapping("/roles")
    public ApiResponse<RoleResponse> updateRole(@RequestBody RoleRequest roleDTO){
        ApiResponse<RoleResponse> apiResponse = new ApiResponse<>();
        RoleResponse msg = roleService.updateRole(roleDTO);
        apiResponse.setResult(msg);
        return apiResponse;
    }

    @DeleteMapping("/roles")
    public ApiResponse<String> deleteRole(@RequestParam List<Long> ids){
        roleService.deleteRole(ids);
        return ApiResponse.<String>builder()
                .result("Role has been deleted")
                .build();
    }
}
