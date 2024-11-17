package com.example.booker.restController;

import com.example.booker.dao.ViDao;
import com.example.booker.entity.Vi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi")
@CrossOrigin("*")
public class ViRestController {

    @Autowired
    private ViDao viDao;

    @PostMapping()
    public Vi create(@RequestBody Vi vi) {
        return viDao.save(vi);
    }

}
