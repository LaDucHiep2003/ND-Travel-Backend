package com.javaweb.api.admin;

import com.javaweb.model.PermissionDTO;
import com.javaweb.model.RoleDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.service.RoleService;
import com.javaweb.service.impl.PermissionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    List<RoleDTO> findAll(@RequestParam Map<String, Object> params){
        List<RoleDTO> result = roleService.findALL(params);
        return result;
    }

    @GetMapping("/roles/permissions")
    List<PermissionDTO> findAllPermissions(){
        List<PermissionDTO> result = permissionsService.findAll();
        return result;
    }

    @GetMapping("/role/{id}")
    RoleDTO findById(@PathVariable Long id){
        RoleDTO result = roleService.findById(id);
        return result;
    }

    @PostMapping("/role")
    ResponseEntity<TourResponse> createRole(@RequestBody RoleDTO roleDTO){
        TourResponse msg = roleService.createRole(roleDTO);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/role")
    ResponseEntity<TourResponse> updateRole(@RequestBody RoleDTO roleDTO){
        TourResponse msg = roleService.updateRole(roleDTO);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/roles")
    ResponseEntity<TourResponse> deleteRole(@RequestParam List<Long> ids){
        TourResponse msg = roleService.deleteRole(ids);
        return ResponseEntity.ok(msg);
    }
}
