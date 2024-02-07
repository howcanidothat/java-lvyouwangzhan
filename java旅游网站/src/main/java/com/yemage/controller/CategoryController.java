package com.yemage.controller;

import com.yemage.domain.Category;
import com.yemage.domain.User;
import com.yemage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author yemage
 */
@Controller
@RequestMapping("/admin/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public String find(Model model) {
        List<Category> list = categoryService.find();
        model.addAttribute("list", list);
        return "category/list";
    }

    @RequestMapping("/toadd")
    public String toAdd() {
        return "category/add";
    }

    @RequestMapping("/doadd")
    public String doAdd(Category category) {
        categoryService.add(category);
        return "redirect:/admin/category/list";
    }

    @RequestMapping("/toupdate")
    public String toUpdate(Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/update";
    }

    @RequestMapping("/doupdate")
    public String doUpdate(Category category) {
        categoryService.update(category);
        return "redirect:/admin/category/list";
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        categoryService.ifdelete(id);
       // categoryService.delete(id);//原先的delete方法会导致外键约束的方法
        return "redirect:/admin/category/list";
    }

}
