package com.btan.freeopenshopping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.btan.freeopenshopping.entities.Category;
import com.btan.freeopenshopping.entities.User;
import com.btan.freeopenshopping.services.*;


@Controller
@RequestMapping(value = "admin")
public class AdminController {

	@Autowired  
	UserService userService;  
	
	@Autowired
	AdminService adminService;
	
	/**
	 * Admin Login Page
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login(ModelMap modelMap) {
		modelMap.put("objUser", new User());
		modelMap.put("title", "FOS - Admin Login");
		return "adminlogin";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login(@ModelAttribute(value = "objUser") User objUser, 
			ModelMap modelMap) {
		boolean result = userService.login(objUser.getEmail(), objUser.getPassword(), 1);
		if (result) {
			modelMap.put("cateList", adminService.getAll());
			modelMap.put("objCategory", new Category());
			modelMap.put("title", "FOS - Category Management");
			return "admincategory";
		} else {
			modelMap.put("title", "FOS - Admin Login");
			return "adminlogin";
		}
	}
	
	/**
	 * Category Management page
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "categorymgr", method = RequestMethod.GET)
	public String categoryMgr(ModelMap modelMap) {
		modelMap.put("title", "FreeOpenShopping Admin - Category Management");
		modelMap.put("cateList", adminService.getAll());
		modelMap.put("objCategory", new Category());
		return "admincategory";
	}
	
	/**
	 * Product Management page
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "productmgr", method = RequestMethod.GET)
	public String productMgr(ModelMap modelMap) {
		modelMap.put("title", "FreeOpenShopping Admin - Product Management");
		return "adminproduct";
	}
	
	/**
	 * Order Management page
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "ordermgr", method = RequestMethod.GET)
	public String orderMgr(ModelMap modelMap) {
		modelMap.put("title", "FreeOpenShopping Admin - Order Management");
		return "adminorder";
	}
	
	/**
	 * User Management page
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "usermgr", method = RequestMethod.GET)
	public String userMgr(ModelMap modelMap) {
		modelMap.put("title", "FreeOpenShopping Admin - User Management");
		return "adminuser";
	}
}
