package com.xdu.xducoder.controller;

import com.raincur.exception.UserNotFoundException;
import com.xdu.xducoder.Dao.NotebookMapper;
import com.xdu.xducoder.Entity.Notebook;
import com.xdu.xducoder.Entity.NotebookExample;
import com.xdu.xducoder.service.Operator;
import com.xdu.xducoder.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(allowCredentials="true",origins = "*",maxAge = 3600)
@RestController
public class notebookController {
    @Autowired
    public Operator notebook;

    @Autowired
    public UserManager userManager;

    @Autowired
    public NotebookMapper notebookMapper;

    @PostMapping("/copyNbToUserBySrc")
    public boolean copyNbToUserByNbId(@RequestBody HashMap map){
        System.out.println(new Date() + ":接口/copyNbToUserBySrc调用一次");

        return notebook.copyNbToUser(
                map.get("nbID").toString(),
                map.get("tarUserID").toString()
                );
    }

    @PostMapping("/copyNbToUserByM")
    public boolean copyNbToUserByM(@RequestBody HashMap map){
        System.out.println(new Date() + ":接口/copyNbToUserByM调用一次");

        return notebook.copyNbToUser(
                map.get("UserID").toString(),
                map.get("path").toString(),
                map.get("name").toString(),
                map.get("tarUserId").toString()
                );
    }

    @PostMapping("/deleteNbBynbID")
    public boolean deleteNb(@RequestBody HashMap map){
        System.out.println(new Date() + ":接口/deleteNbBynbID调用一次");

        return notebook.deleteNb(
                map.get("nbId").toString()
        );
    }

    @PostMapping("/selectNbIDByUserID")
    public List<Notebook> selectNbIDByUserID(@RequestBody HashMap map){
        System.out.println(new Date() + ":接口/selectNbIDByUserID调用一次");

        NotebookExample example = new NotebookExample();
        example.createCriteria().andUserIDEqualTo(map.get("UserID").toString());
        return notebookMapper.selectByExample(example);
    }

    @PostMapping("/createUserDirectory")
    public boolean createUserDirectory(@RequestBody HashMap map){
        System.out.println(new Date() + ":接口/createUserDirectory调用一次");

        return userManager.createUser(map.get("UserId").toString(),map.get("name").toString());
    }

    @PostMapping("/deleteUserDirectory")
    public boolean deleteUserDirectory(@RequestBody HashMap map){
        System.out.println(new Date() + ":接口/deleteUserDirectory调用一次");

        try {
            return userManager.deleteUser(map.get("UserID").toString());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/resetNoteBookByNbID")
    public boolean resetNoteBookByNbID(@RequestBody HashMap map){
        System.out.println(new Date() + ":接口/resetNoteBookByNbID调用一次");

        return notebook.resetNb(map.get("nbId").toString());
    }
}
