package me.robert;

import me.robert.Curs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    private StudentRepository repository;
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
//    public List<Curs> createPet(@Valid @RequestBody Curs pets) {
//        return pets;
//    }

}
