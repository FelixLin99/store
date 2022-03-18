package com.cy.store.controller;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.controller.ex.FileUploadIOException;
import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/9 16:19
 */
// @Controller
@RestController // 等于Controller+ResonseBody的组合
@RequestMapping("address") //表示users类型的请求会被拦截，由这个Controller处理
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> createAddress(HttpSession session, Address address){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.createAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getAddresses(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> addresses = addressService.getAddressesByUid(uid);
        return new JsonResult<>(OK, addresses);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<Void>(OK);
    }




}
