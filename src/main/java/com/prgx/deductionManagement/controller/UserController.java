package com.prgx.deductionManagement.controller;

import com.prgx.deductionManagement.model.Meta;
import com.prgx.deductionManagement.model.UserResource;
import com.prgx.deductionManagement.model.UserResourceRef;
import com.prgx.deductionManagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final String SCIM_MEDIA_TYPE = "application/scim+json";
    @Autowired
    private UserService userService;
    @GetMapping(value="/", produces = SCIM_MEDIA_TYPE)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get a list of all users in the system", description = "List users")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResourceRef> listUsers(
            @RequestParam(required = false) String[] attributes,
            @RequestParam(required = false) String[] excludedAttributes,
            @RequestParam(required = false, defaultValue = "userName") String sortBy,
            @RequestParam(required = false, defaultValue = "descending") String sortOrder,
            @RequestParam(required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(required = false, defaultValue = "20") Integer count
    ){
        Sort sort = (sortOrder.equalsIgnoreCase("descending"))
                ?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(startIndex, count, sort);
        return userService.listUsers(pageable);
    }

    @GetMapping(value = "/{id}", produces = SCIM_MEDIA_TYPE)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get a specific user in the system", description = "Get user by userId")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserResource> getUser(@PathVariable String id){
        Optional<UserResource> user = userService.getUser(id);
        return user.isPresent()
                ? new ResponseEntity<>(user.get(), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/", consumes = SCIM_MEDIA_TYPE, produces = SCIM_MEDIA_TYPE)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create a new user in the system", description = "Create new user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserResource> createUser (
            @Valid @RequestBody UserResource userResource
    ){
        if(userResource.getId()!=null){
            userResource.setExternalId(userResource.getId().toString());
        }
        if(userResource.getActive()==null){
            userResource.setActive(Boolean.FALSE);
        }
        Meta meta = new Meta();
        meta.setResourceType("User");
        userResource.setMeta(meta);
        return new ResponseEntity<>(userService.createUser(userResource), HttpStatus.CREATED);
    }

    /*@PutMapping(value = "/{id}", consumes = SCIM_MEDIA_TYPE, produces = SCIM_MEDIA_TYPE)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update an existing user in the system", description = "Update existing user")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserResource> updateUser (
            @PathVariable String id,
            @Valid @RequestBody UserResource userResource
    ){
        if(userResource.getId()!=null){
            userResource.setExternalId(userResource.getId().toString());
        }
        userResource.setId(id);
        if(userResource.getActive()==null){
            userResource.setActive(Boolean.FALSE);
        }
        return new ResponseEntity<>(userService.updateUser(userResource), HttpStatus.OK);
    }*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}