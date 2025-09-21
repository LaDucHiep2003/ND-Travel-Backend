package com.javaweb.api.admin;

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
@RequestMapping("/api/admin")
public class RoleAPI {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionsService permissionsService;

    @GetMapping("/roles")
    public List<RoleResponse> findAll(@RequestParam Map<String, Object> params){
        List<RoleResponse> result = roleService.findALL(params);
        return result;
    }

    @GetMapping("/roles/permissions")
    public List<PermissionDTO> findAllPermissions(){
        List<PermissionDTO> result = permissionsService.findAll();
        return result;
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
    public String deleteRole(@RequestParam List<Long> ids){
        roleService.deleteRole(ids);
        return "Role has been deleted";
    }
}
