package com.volkov.demoproducer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkov.demoproducer.entity.dto.DemoUserDTO;
import com.volkov.demoproducer.service.DemoUserService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo-user", produces = {
        MediaType.APPLICATION_JSON_VALUE
})
public class DemoUserController {

    private final DemoUserService demoUserService;

    public DemoUserController(DemoUserService demoUserService) {
        this.demoUserService = demoUserService;
    }

    @PostMapping(value = "/save", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public DemoUserDTO saveDemoUser(@RequestBody DemoUserDTO demoUserDTO) throws JsonProcessingException {
        return demoUserService.saveDemoUser(demoUserDTO);
    }

    @DeleteMapping(value = "/delete")
    public Boolean deleteDemoUser(@RequestParam String username) {
        return demoUserService.deleteDemoUserByUsername(username);
    }

    @GetMapping(value = "/all")
    public List<DemoUserDTO> getDemoUsers() {
        return demoUserService.getDemoUsers();
    }

}
